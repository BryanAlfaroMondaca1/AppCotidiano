package com.example.cotidianoorganiza;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Calendar;

public class RegistroActividadesActivity extends AppCompatActivity {

    EditText nombreActividadInput, descripcionInput, fechaInput, horaInicioInput, duracionInput;
    Spinner categoriaSpinner, prioridadSpinner, repeticionSpinner;
    Button guardarButton, cancelarButton;
    ListView actividadesListView;
    ArrayList<String> actividades;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_actividades);

        nombreActividadInput = findViewById(R.id.nombreActividadInput);
        descripcionInput = findViewById(R.id.descripcionInput);
        fechaInput = findViewById(R.id.fechaInput);
        horaInicioInput = findViewById(R.id.horaInicioInput);
        duracionInput = findViewById(R.id.duracionInput);
        categoriaSpinner = findViewById(R.id.categoriaSpinner);
        prioridadSpinner = findViewById(R.id.prioridadSpinner);
        repeticionSpinner = findViewById(R.id.repeticionSpinner);
        guardarButton = findViewById(R.id.guardarButton);
        cancelarButton = findViewById(R.id.cancelarButton);
        actividadesListView = findViewById(R.id.actividadesListView);

        actividades = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, actividades);
        actividadesListView.setAdapter(adapter);

        // Configurar los spinners de categorías y prioridades
        ArrayAdapter<CharSequence> categoriaAdapter = ArrayAdapter.createFromResource(this, R.array.categorias_array, android.R.layout.simple_spinner_item);
        categoriaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categoriaSpinner.setAdapter(categoriaAdapter);

        ArrayAdapter<CharSequence> prioridadAdapter = ArrayAdapter.createFromResource(this, R.array.prioridades_array, android.R.layout.simple_spinner_item);
        prioridadAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        prioridadSpinner.setAdapter(prioridadAdapter);

        ArrayAdapter<CharSequence> repeticionAdapter = ArrayAdapter.createFromResource(this, R.array.repeticion_array, android.R.layout.simple_spinner_item);
        repeticionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        repeticionSpinner.setAdapter(repeticionAdapter);

        // Configurar los diálogos para seleccionar la fecha y hora
        fechaInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(RegistroActividadesActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        fechaInput.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        horaInicioInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(RegistroActividadesActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        horaInicioInput.setText(hourOfDay + ":" + (minute < 10 ? "0" + minute : minute));
                    }
                }, hour, minute, true);
                timePickerDialog.show();
            }
        });

        // Botón para guardar la actividad
        guardarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String actividad = "Nombre: " + nombreActividadInput.getText().toString() +
                        "\nDescripción: " + descripcionInput.getText().toString() +
                        "\nCategoría: " + categoriaSpinner.getSelectedItem().toString() +
                        "\nFecha: " + fechaInput.getText().toString() +
                        "\nHora de inicio: " + horaInicioInput.getText().toString() +
                        "\nDuración: " + duracionInput.getText().toString() +
                        "\nRepetición: " + repeticionSpinner.getSelectedItem().toString() +
                        "\nPrioridad: " + prioridadSpinner.getSelectedItem().toString();
                actividades.add(actividad);
                adapter.notifyDataSetChanged();  // Actualizar la lista con la nueva actividad
                Toast.makeText(RegistroActividadesActivity.this, "Actividad guardada", Toast.LENGTH_SHORT).show();
                limpiarCampos();
            }
        });

        // Botón para cancelar
        cancelarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limpiarCampos();
            }
        });
    }

    // Método para limpiar los campos después de guardar o cancelar
    private void limpiarCampos() {
        nombreActividadInput.setText("");
        descripcionInput.setText("");
        fechaInput.setText("");
        horaInicioInput.setText("");
        duracionInput.setText("");
        categoriaSpinner.setSelection(0);
        prioridadSpinner.setSelection(0);
        repeticionSpinner.setSelection(0);
    }
}
