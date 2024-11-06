package com.hubees.hubeesnotification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.Intent;
import android.os.Build;
import android.widget.RemoteViews;
import androidx.core.app.NotificationCompat;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

@CapacitorPlugin(name = "HubeesNotification")
public class HubeesNotificationPlugin extends Plugin {
    private static final String CHANNEL_ID = "custom_notification_channel";

    @Override
    public void load() {
        super.load();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                CHANNEL_ID,
                "Custom Notifications",
                NotificationManager.IMPORTANCE_DEFAULT
            );
            NotificationManager manager = getContext().getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
    }

    @PluginMethod
    public void isNotificationClosed(PluginCall call) {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("NotificationPrefs", Context.MODE_PRIVATE);
        boolean isClosed = sharedPreferences.getBoolean("notificationClosed", false);

        JSObject ret = new JSObject();
        ret.put("notificationClosed", isClosed);
        call.resolve(ret);
    }

    @PluginMethod
    public void sendNotification(PluginCall call) {
        String remainingTime = call.getString("remainingTime");
        String details = call.getString("details");
        String arrivalTime = call.getString("arrivalTime");
        int progress = call.getInt("progress", 0);

        // Redefine o valor de notificationClosed para false
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("NotificationPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("notificationClosed", false); // Redefine para false ao enviar a notificação
        editor.apply();

        RemoteViews notificationLayoutSmall = new RemoteViews(getContext().getPackageName(), R.layout.custom_notification_layout_small);

        RemoteViews notificationLayoutLarge = new RemoteViews(getContext().getPackageName(), R.layout.custom_notification_layout_large);

        // Define os valores dinamicamente no layout
        notificationLayoutSmall.setProgressBar(R.id.progressBar, 100, progress, false);

        notificationLayoutLarge.setTextViewText(R.id.notification_remaining_time, remainingTime);
        notificationLayoutLarge.setTextViewText(R.id.notification_details, details);
        notificationLayoutLarge.setTextViewText(R.id.arrival_time_label, arrivalTime);
        notificationLayoutLarge.setProgressBar(R.id.progressBar, 100, progress, false);

        // Cria um Intent para abrir uma Activity quando o usuário tocar na notificação
        Intent notificationIntent = getContext().getPackageManager().getLaunchIntentForPackage(getContext().getPackageName());
        notificationIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(
            getContext(),
            0,
            notificationIntent,
            PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
        );

        // Cria um Intent para fechar a notificação
        Intent closeIntent = new Intent(getContext(), NotificationReceiver.class);
        closeIntent.setAction("CLOSE_NOTIFICATION");
        PendingIntent closePendingIntent = PendingIntent.getBroadcast(
            getContext(),
            0,
            closeIntent,
            PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
        );

        notificationLayoutLarge.setOnClickPendingIntent(R.id.button_close, closePendingIntent);

        // Cria a notificação
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getContext(), CHANNEL_ID)
                .setSmallIcon(R.drawable.hubees)  // Ícone da notificação
                .setStyle(new NotificationCompat.DecoratedCustomViewStyle()) // Necessário para usar o estilo customizado
                .setContentIntent(pendingIntent)
                .setCustomContentView(notificationLayoutSmall)  // Define o layout compacto
                .setCustomBigContentView(notificationLayoutLarge)  // Define o layout expandido
                .setPriority(NotificationCompat.PRIORITY_HIGH) // Prioridade alta
                .setShowWhen(false)
                .setOngoing(true);

        NotificationManager manager = (NotificationManager) getContext().getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(1, builder.build());

        JSObject response = new JSObject();
        response.put("success", true);
        call.resolve(response);
    }

    @PluginMethod
    public void updateNotification(PluginCall call) {
        // Recupera os dados que você deseja atualizar
        String remainingTime = call.getString("remainingTime");
        String details = call.getString("details");
        String arrivalTime = call.getString("arrivalTime");
        int progress = call.getInt("progress", 0);

        RemoteViews notificationLayoutSmall = new RemoteViews(getContext().getPackageName(), R.layout.custom_notification_layout_small);

        RemoteViews notificationLayoutLarge = new RemoteViews(getContext().getPackageName(), R.layout.custom_notification_layout_large);

        // Atualiza os valores da notificação
        notificationLayoutSmall.setProgressBar(R.id.progressBar, 100, progress, false);

        notificationLayoutLarge.setTextViewText(R.id.notification_remaining_time, remainingTime);
        notificationLayoutLarge.setTextViewText(R.id.notification_details, details);
        notificationLayoutLarge.setTextViewText(R.id.arrival_time_label, arrivalTime);
        notificationLayoutLarge.setProgressBar(R.id.progressBar, 100, progress, false);

        // Cria um Intent para abrir a Activity novamente
        Intent notificationIntent = getContext().getPackageManager().getLaunchIntentForPackage(getContext().getPackageName());
        notificationIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(
            getContext(),
            0,
            notificationIntent,
            PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
        );

        // Cria um Intent para fechar a notificação
        Intent closeIntent = new Intent(getContext(), NotificationReceiver.class);
        closeIntent.setAction("CLOSE_NOTIFICATION");
        PendingIntent closePendingIntent = PendingIntent.getBroadcast(
            getContext(),
            0,
            closeIntent,
            PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
        );

        notificationLayoutLarge.setOnClickPendingIntent(R.id.button_close, closePendingIntent);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getContext(), CHANNEL_ID)
                .setSmallIcon(R.drawable.hubees)
                .setStyle(new NotificationCompat.DecoratedCustomViewStyle())
                .setCustomContentView(notificationLayoutSmall)  // Define o layout compacto
                .setCustomBigContentView(notificationLayoutLarge)  // Define o layout expandido
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setShowWhen(false)
                .setOngoing(true)
                .setContentIntent(pendingIntent); // Define o PendingIntent para a ação de clique

        NotificationManager manager = (NotificationManager) getContext().getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(1, builder.build()); // Utiliza o mesmo ID para atualizar a notificação

        JSObject response = new JSObject();
        response.put("success", true);
        call.resolve(response);
    }
}
