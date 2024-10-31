package com.hubees.hubeesnotification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.app.NotificationManager;

public class NotificationReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if ("CLOSE_NOTIFICATION".equals(intent.getAction())) {
            NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            manager.cancel(1); // O mesmo ID usado para mostrar a notificação
        }
    }
}

