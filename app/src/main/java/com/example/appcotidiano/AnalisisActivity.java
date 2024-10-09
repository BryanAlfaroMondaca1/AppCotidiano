package com.example.cotidiano;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

public class AnalisisActivity extends AppCompatActivity {

    private TextView tvMensajeMotivacion;
    private TextView tvAnalisisTrabajo, tvAnalisisEstudio, tvAnalisisEjercicio, tvAnalisisDescanso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analisis);

        tvMensajeMotivacion = findViewById(R.id.tvMensajeMotivacion);
        tvAnalisisTrabajo = findViewById(R.id.tvAnalisisTrabajo);
        tvAnalisisEstudio = findViewById(R.id.tvAnalisisEstudio);
        tvAnalisisEjercicio = findViewById(R.id.tvAnalisisEjercicio);
        tvAnalisisDescanso = findViewById(R.id.tvAnalisisDescanso);

        // Mostrar un mensaje de motivación
        tvMensajeMotivacion.setText("¡Genial! Estás organizando bien tus actividades.");

        // Generar datos de ejemplo para el análisis
        HashMap<String, Integer> datosActividades = obtenerDatosEjemplo();
        actualizarEstadisticas(datosActividades);
    }

    private HashMap<String, Integer> obtenerDatosEjemplo() {
        // Datos de ejemplo: Actividad y la cantidad de tiempo en minutos
        HashMap<String, Integer> datos = new HashMap<>();
        datos.put("Trabajo", 120);     // 120 minutos en Trabajo
        datos.put("Estudio", 90);      // 90 minutos en Estudio
        datos.put("Ejercicio", 60);    // 60 minutos en Ejercicio
        datos.put("Descanso", 30);     // 30 minutos en Descanso
        return datos;
    }

    private void actualizarEstadisticas(HashMap<String, Integer> datos) {
        // Actualizar cada TextView con los datos de las actividades
        tvAnalisisTrabajo.setText("Tiempo en Trabajo: " + datos.get("Trabajo") + " min");
        tvAnalisisEstudio.setText("Tiempo en Estudio: " + datos.get("Estudio") + " min");
        tvAnalisisEjercicio.setText("Tiempo en Ejercicio: " + datos.get("Ejercicio") + " min");
        tvAnalisisDescanso.setText("Tiempo en Descanso: " + datos.get("Descanso") + " min");
    }
}
