package com.example.appcotidiano;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;

public class IntegracionCalendariosActivity extends AppCompatActivity {
    private int year, month, day, hour, minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_integracion_calendarios);

        Button selectDateButton = findViewById(R.id.selectDateButton);
        Button selectTimeButton = findViewById(R.id.selectTimeButton);
        Button addToCalendarButton = findViewById(R.id.addToCalendarButton);

        Calendar calendar = Calendar.getInstance();

        selectDateButton.setOnClickListener(v -> {
            year = calendar.get(Calendar.YEAR);
            month = calendar.get(Calendar.MONTH);
            day = calendar.get(Calendar.DAY_OF_MONTH);

            new DatePickerDialog(this, (view, year, monthOfYear, dayOfMonth) -> {
                this.year = year;
                this.month = monthOfYear;
                this.day = dayOfMonth;
                Toast.makeText(this, "Fecha seleccionada: " + day + "/" + (month + 1) + "/" + year, Toast.LENGTH_SHORT).show();
            }, year, month, day).show();
        });

        selectTimeButton.setOnClickListener(v -> {
            hour = calendar.get(Calendar.HOUR_OF_DAY);
            minute = calendar.get(Calendar.MINUTE);

            new TimePickerDialog(this, (view, hourOfDay, minute) -> {
                this.hour = hourOfDay;
                this.minute = minute;
                Toast.makeText(this, "Hora seleccionada: " + hour + ":" + minute, Toast.LENGTH_SHORT).show();
            }, hour, minute, true).show();
        });

        addToCalendarButton.setOnClickListener(v -> {
            long startMillis = Calendar.getInstance().getTimeInMillis();

            ContentValues event = new ContentValues();
            event.put(CalendarContract.Events.CALENDAR_ID, 1);
            event.put(CalendarContract.Events.TITLE, "Actividad Programada");
            event.put(CalendarContract.Events.DESCRIPTION, "Actividad registrada desde la app.");
            event.put(CalendarContract.Events.DTSTART, startMillis);
            event.put(CalendarContract.Events.DTEND, startMillis + 60 * 60 * 1000);
            event.put(CalendarContract.Events.EVENT_TIMEZONE, "America/Santiago");

            getContentResolver().insert(CalendarContract.Events.CONTENT_URI, event);

            Toast.makeText(this, "Evento añadido al calendario", Toast.LENGTH_SHORT).show();
        });
    }
}
