package com.example.cotidiano;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class RegistroActividadesActivity extends AppCompatActivity {

    private EditText etNombreActividad, etDuracion, etCategoria;
    private Button btnAgregarActividad;
    private ListView lvActividades;
    private ArrayList<String> listaActividades;
    private ArrayAdapter<String> adapter;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_actividades);

        // Enlazar componentes de la interfaz
        etNombreActividad = findViewById(R.id.etNombreActividad);
        etDuracion = findViewById(R.id.etDuracion);
        etCategoria = findViewById(R.id.etCategoria);
        btnAgregarActividad = findViewById(R.id.btnAgregarActividad);
        lvActividades = findViewById(R.id.lvActividades);

        // Inicializar SharedPreferences
        sharedPreferences = getSharedPreferences("ResumenSemanal", MODE_PRIVATE);

        // Inicializar la lista de actividades y el adaptador
        listaActividades = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaActividades);
        lvActividades.setAdapter(adapter);

        // Cargar actividades almacenadas (si existen)
        cargarActividadesGuardadas();

        // Agregar actividad al hacer clic en el botón
        btnAgregarActividad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = etNombreActividad.getText().toString();
                String duracion = etDuracion.getText().toString();
                String categoria = etCategoria.getText().toString();

                if (!nombre.isEmpty() && !duracion.isEmpty() && !categoria.isEmpty()) {
                    String actividad = nombre + " - " + duracion + " min - " + categoria;
                    listaActividades.add(actividad);
                    adapter.notifyDataSetChanged();

                    // Guardar la nueva actividad en Resumen Semanal
                    guardarNuevaActividad(actividad);

                    // Limpiar campos de texto después de agregar
                    etNombreActividad.setText("");
                    etDuracion.setText("");
                    etCategoria.setText("");
                } else {
                    Toast.makeText(RegistroActividadesActivity.this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Eliminar actividad al hacer clic prolongado en la lista
        lvActividades.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                listaActividades.remove(position);
                adapter.notifyDataSetChanged();
                Toast.makeText(RegistroActividadesActivity.this, "Actividad Eliminada", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    // Método para cargar las actividades guardadas en SharedPreferences
    private void cargarActividadesGuardadas() {
        Set<String> actividadesGuardadas = sharedPreferences.getStringSet("actividades", new HashSet<>());

        if (actividadesGuardadas != null) {
            listaActividades.addAll(actividadesGuardadas);
        }
    }

    // Guardar la nueva actividad en SharedPreferences para Resumen Semanal
    private void guardarNuevaActividad(String nuevaActividad) {
        Set<String> actividadesGuardadas = sharedPreferences.getStringSet("actividades", new HashSet<>());
        actividadesGuardadas.add(nuevaActividad);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putStringSet("actividades", actividadesGuardadas);
        editor.apply();
    }
}
