package com.example.appcotidiano;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class AlertaManagerActivity extends AppCompatActivity {
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alerta_manager);

        EditText frequencyInput = findViewById(R.id.frequencyInput);
        Button activateButton = findViewById(R.id.activateReminderButton);
        Button deactivateButton = findViewById(R.id.deactivateReminderButton);
        TextView reminderStatus = findViewById(R.id.reminderStatus);

        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(AlertaManagerActivity.this, ReminderReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        activateButton.setOnClickListener(v -> {
            String frequencyStr = frequencyInput.getText().toString();
            if (!frequencyStr.isEmpty()) {
                int frequency = Integer.parseInt(frequencyStr) * 60 * 1000; // Convertir minutos a milisegundos
                activarRecordatorio(frequency);
                reminderStatus.setText("Estado del recordatorio: Activado cada " + frequencyStr + " minutos.");
            }
        });

        deactivateButton.setOnClickListener(v -> {
            desactivarRecordatorio();
            reminderStatus.setText("Estado del recordatorio: No activado.");
        });
    }

    private void activarRecordatorio(int frequency) {
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), frequency, pendingIntent);
    }

    private void desactivarRecordatorio() {
        alarmManager.cancel(pendingIntent);
    }
}
