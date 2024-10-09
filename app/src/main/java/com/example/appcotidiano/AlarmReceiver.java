package com.example.cotidiano;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // Mostrar un mensaje cuando se active la alarma
        Toast.makeText(context, "¡Recordatorio Activado!", Toast.LENGTH_LONG).show();
    }
}
