package com.example.cotidianoorganiza;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;

public class AlertasActivity extends AppCompatActivity {

    EditText mensajeAlertaInput, fechaInput, horaInput;
    Spinner tipoAlertaSpinner, frecuenciaAlertaSpinner;
    Button guardarAlertaButton, cancelarAlertaButton;
    Switch activarAlertaSwitch;
    ListView listaAlertas;
    ArrayList<String> alertas;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alertas);

        mensajeAlertaInput = findViewById(R.id.mensajeAlertaInput);
        fechaInput = findViewById(R.id.fechaInput);
        horaInput = findViewById(R.id.horaInput);
        tipoAlertaSpinner = findViewById(R.id.tipoAlertaSpinner);
        frecuenciaAlertaSpinner = findViewById(R.id.frecuenciaAlertaSpinner);
        guardarAlertaButton = findViewById(R.id.guardarAlertaButton);
        cancelarAlertaButton = findViewById(R.id.cancelarAlertaButton);
        activarAlertaSwitch = findViewById(R.id.activarAlertaSwitch);
        listaAlertas = findViewById(R.id.listaAlertas);

        alertas = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alertas);
        listaAlertas.setAdapter(adapter);

        // Configurar los spinners para tipo y frecuencia de alerta
        ArrayAdapter<CharSequence> tipoAdapter = ArrayAdapter.createFromResource(this, R.array.tipos_alerta_array, android.R.layout.simple_spinner_item);
        tipoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tipoAlertaSpinner.setAdapter(tipoAdapter);

        ArrayAdapter<CharSequence> frecuenciaAdapter = ArrayAdapter.createFromResource(this, R.array.frecuencia_alerta_array, android.R.layout.simple_spinner_item);
        frecuenciaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        frecuenciaAlertaSpinner.setAdapter(frecuenciaAdapter);

        // Configuración de fecha y hora para la alerta
        fechaInput.setOnClickListener(v -> mostrarSelectorFecha());
        horaInput.setOnClickListener(v -> mostrarSelectorHora());

        // Botón para guardar alerta
        guardarAlertaButton.setOnClickListener(v -> guardarAlerta());

        // Botón para cancelar la creación de alerta
        cancelarAlertaButton.setOnClickListener(v -> limpiarCampos());
    }

    // Mostrar selector de fecha
    private void mostrarSelectorFecha() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(AlertasActivity.this, (view, year1, month1, dayOfMonth) -> {
            fechaInput.setText(dayOfMonth + "/" + (month1 + 1) + "/" + year1);
        }, year, month, day);
        datePickerDialog.show();
    }

    // Mostrar selector de hora
    private void mostrarSelectorHora() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(AlertasActivity.this, (view, hourOfDay, minute1) -> {
            horaInput.setText(hourOfDay + ":" + (minute1 < 10 ? "0" + minute1 : minute1));
        }, hour, minute, true);
        timePickerDialog.show();
    }

    // Guardar alerta
    private void guardarAlerta() {
        String tipo = tipoAlertaSpinner.getSelectedItem().toString();
        String frecuencia = frecuenciaAlertaSpinner.getSelectedItem().toString();
        String mensaje = mensajeAlertaInput.getText().toString();
        String fecha = fechaInput.getText().toString();
        String hora = horaInput.getText().toString();

        if (mensaje.isEmpty() || fecha.isEmpty() || hora.isEmpty()) {
            Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
        } else {
            // Crear la descripción de la alerta
            String alerta = "Tipo: " + tipo +
                    "\nMensaje: " + mensaje +
                    "\nFecha: " + fecha +
                    "\nHora: " + hora +
                    "\nFrecuencia: " + frecuencia;
            alertas.add(alerta);
            adapter.notifyDataSetChanged();
            limpiarCampos();

            // Configurar la alerta si está activada
            if (activarAlertaSwitch.isChecked()) {
                configurarAlarma(fecha, hora);
            }
        }
    }

    // Configurar la alarma para la alerta
    private void configurarAlarma(String fecha, String hora) {
        // Ejemplo básico de cómo configurar una alarma. Esto se puede mejorar según la frecuencia seleccionada.
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertaReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        // Configurar la fecha y hora de la alarma a partir de los datos ingresados
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);

        Toast.makeText(this, "Alerta programada para " + fecha + " a las " + hora, Toast.LENGTH_SHORT).show();
    }

    // Limpiar campos después de guardar o cancelar
    private void limpiarCampos() {
        mensajeAlertaInput.setText("");
        fechaInput.setText("");
        horaInput.setText("");
        tipoAlertaSpinner.setSelection(0);
        frecuenciaAlertaSpinner.setSelection(0);
        activarAlertaSwitch.setChecked(false);
    }
}
