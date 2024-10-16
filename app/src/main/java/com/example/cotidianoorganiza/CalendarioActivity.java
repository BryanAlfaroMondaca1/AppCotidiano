package com.example.cotidianoorganiza;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class CalendarioActivity extends AppCompatActivity {

    private CalendarView calendarView;
    private Button btnOpenGoogleCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendario);

        // CalendarView que muestra el calendario
        calendarView = findViewById(R.id.calendarView);

        // Botón para abrir Google Calendar
        btnOpenGoogleCalendar = findViewById(R.id.btnOpenGoogleCalendar);
        btnOpenGoogleCalendar.setOnClickListener(v -> openGoogleCalendar());

        // Listener para mostrar la fecha seleccionada
        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
            Toast.makeText(CalendarioActivity.this, "Fecha seleccionada: " + selectedDate, Toast.LENGTH_SHORT).show();
        });
    }

    // Método para abrir Google Calendar
    private void openGoogleCalendar() {
        try {
            // Intent para abrir Google Calendar si está instalado
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("content://com.android.calendar/time/"));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            // Si Google Calendar no está instalado, abrir la versión web
            Intent intentWeb = new Intent(Intent.ACTION_VIEW, Uri.parse("https://calendar.google.com/"));
            startActivity(intentWeb);
        }
    }
}
