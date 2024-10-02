package com.example.appcotidiano;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Map;

public class RegistroActividadesActivity extends AppCompatActivity {
    ArrayList<String> activitiesList = new ArrayList<>();
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_actividades);

        EditText activityName = findViewById(R.id.activityName);
        EditText duration = findViewById(R.id.duration);
        EditText category = findViewById(R.id.category);
        Button saveButton = findViewById(R.id.saveButton);
        ListView activitiesListView = findViewById(R.id.activitiesListView);

        SharedPreferences preferences = getSharedPreferences("Activities", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        // Inicializar el adaptador y conectarlo con el ListView
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, activitiesList);
        activitiesListView.setAdapter(adapter);

        // Cargar actividades guardadas
        loadActivities(preferences);

        saveButton.setOnClickListener(v -> {
            String name = activityName.getText().toString();
            String time = duration.getText().toString();
            String cat = category.getText().toString();

            if (!name.isEmpty() && !time.isEmpty() && !cat.isEmpty()) {
                // Guardar la actividad en SharedPreferences
                String activityData = "Nombre: " + name + ", Duración: " + time + " min, Categoría: " + cat;
                editor.putString("Activity_" + name, activityData);
                editor.apply();

                // Añadir la actividad a la lista y actualizar el ListView
                activitiesList.add(activityData);
                adapter.notifyDataSetChanged();

                Toast.makeText(RegistroActividadesActivity.this, "Actividad guardada", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(RegistroActividadesActivity.this, "Complete todos los campos", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Método para cargar actividades de SharedPreferences y mostrarlas en el ListView
    private void loadActivities(SharedPreferences preferences) {
        Map<String, ?> allEntries = preferences.getAll();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            activitiesList.add(entry.getValue().toString());
        }
        adapter.notifyDataSetChanged();
    }
}
