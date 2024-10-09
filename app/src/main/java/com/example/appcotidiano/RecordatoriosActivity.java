package com.example.cotidiano;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;

public class RecordatoriosActivity extends AppCompatActivity {

    private TimePicker timePicker;
    private Button btnAgregarAlarma;
    private TextView tvEstadoTareas;
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recordatorios);

        timePicker = findViewById(R.id.timePicker);
        btnAgregarAlarma = findViewById(R.id.btnAgregarAlarma);
        tvEstadoTareas = findViewById(R.id.tvEstadoTareas);

        // Configurar TimePicker para el modo 24 horas
        timePicker.setIs24HourView(true);

        // Verificar si ya hay una alarma configurada
        verificarEstadoTareas();

        // Configurar el botón para agregar una alarma
        btnAgregarAlarma.setOnClickListener(v -> {
            // Obtener la hora y los minutos seleccionados del TimePicker
            int hour = timePicker.getHour();
            int minute = timePicker.getMinute();

            // Configurar la alarma con la hora seleccionada
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, hour);
            calendar.set(Calendar.MINUTE, minute);
            calendar.set(Calendar.SECOND, 0);

            setAlarma(calendar);
            Toast.makeText(RecordatoriosActivity.this, "Alarma configurada a las " + hour + ":" + minute, Toast.LENGTH_SHORT).show();

            // Guardar el estado de la alarma en SharedPreferences
            getSharedPreferences("Tareas", MODE_PRIVATE)
                    .edit()
                    .putBoolean("alarmaActiva", true)
                    .apply();

            // Actualizar el estado del mensaje
            verificarEstadoTareas();
        });
    }

    private void setAlarma(Calendar calendar) {
        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        if (alarmManager != null) {
            // Establecer la alarma exacta
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
            } else {
                alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
            }
        }
    }

    private void verificarEstadoTareas() {
        // Verificar si hay alarmas activas en SharedPreferences
        boolean alarmaActiva = getSharedPreferences("Tareas", MODE_PRIVATE).getBoolean("alarmaActiva", false);

        if (alarmaActiva) {
            tvEstadoTareas.setText("Tienes una alarma programada.");
        } else {
            tvEstadoTareas.setText("No tienes tareas para hoy");
        }
    }
}
