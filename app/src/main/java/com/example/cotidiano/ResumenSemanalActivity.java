package com.example.cotidiano;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ResumenSemanalActivity extends AppCompatActivity {

    private ListView lvResumenSemanal;
    private ArrayList<String> listaActividades;
    private ArrayAdapter<String> adapter;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumen_semanal);

        // Inicializar componentes
        lvResumenSemanal = findViewById(R.id.lvResumenSemanal);

        // Crear una lista de actividades predeterminadas
        listaActividades = new ArrayList<>();
        listaActividades.add("Trabajo: 120 minutos");
        listaActividades.add("Estudio: 90 minutos");
        listaActividades.add("Ejercicio: 60 minutos");
        listaActividades.add("Descanso: 30 minutos");

        // Inicializar SharedPreferences para almacenar y cargar actividades
        sharedPreferences = getSharedPreferences("ResumenSemanal", MODE_PRIVATE);

        // Cargar las actividades guardadas (si existen)
        cargarActividadesGuardadas();

        // Configurar el adaptador de la lista
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaActividades);
        lvResumenSemanal.setAdapter(adapter);
    }

    // Método para cargar las actividades guardadas en SharedPreferences
    private void cargarActividadesGuardadas() {
        Set<String> actividadesGuardadas = sharedPreferences.getStringSet("actividades", new HashSet<>());

        if (actividadesGuardadas != null && !actividadesGuardadas.isEmpty()) {
            listaActividades.clear();
            listaActividades.addAll(actividadesGuardadas);
        }
    }

    // Método para guardar las actividades cada vez que se modifiquen
    public void guardarActividades() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Set<String> setActividades = new HashSet<>(listaActividades);
        editor.putStringSet("actividades", setActividades);
        editor.apply();
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Guardar la lista de actividades cuando la actividad se detiene
        guardarActividades();
    }
}
