package com.example.appcotidiano;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.core.app.NotificationCompat;

public class ReminderReceiver extends BroadcastReceiver {
    private static final String CHANNEL_ID = "REMINDER_CHANNEL";

    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        // Crear el canal de notificación para versiones Android 8.0 y superiores
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "Recordatorio",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            notificationManager.createNotificationChannel(channel);
        }

        // Construir la notificación
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Recordatorio de Actividad")
                .setContentText("¡Es hora de realizar tu actividad programada!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        // Mostrar la notificación
        notificationManager.notify(1, builder.build());
    }
}
