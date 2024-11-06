package com.hubees.hubeesnotification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.app.NotificationManager;
import android.content.SharedPreferences;

public class NotificationReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if ("CLOSE_NOTIFICATION".equals(intent.getAction())) {
            NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            manager.cancel(1); // O mesmo ID usado para mostrar a notificação

            // Salva o estado de fechamento da notificação no SharedPreferences
            SharedPreferences sharedPreferences = context.getSharedPreferences("NotificationPrefs", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("notificationClosed", true); // Define que a notificação foi fechada
            editor.apply();
        }
    }
}

