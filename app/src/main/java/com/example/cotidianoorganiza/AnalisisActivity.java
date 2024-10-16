package com.example.cotidianoorganiza;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class AnalisisActivity extends AppCompatActivity {

    Button btnTrabajador, btnEstudiante, btnDeportista;
    TextView planOptimizacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analisis);

        // Inicializar botones y el TextView
        btnTrabajador = findViewById(R.id.btnTrabajador);
        btnEstudiante = findViewById(R.id.btnEstudiante);
        btnDeportista = findViewById(R.id.btnDeportista);
        planOptimizacion = findViewById(R.id.planOptimizacion);

        // Configurar eventos de clic para cada botón
        btnTrabajador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarPlanOptimizacion("trabajador");
            }
        });

        btnEstudiante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarPlanOptimizacion("estudiante");
            }
        });

        btnDeportista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarPlanOptimizacion("deportista");
            }
        });
    }

    // Método para mostrar el plan de optimización dependiendo del perfil seleccionado
    private void mostrarPlanOptimizacion(String perfil) {
        String plan = "";
        switch (perfil) {
            case "trabajador":
                plan = "Plan de Optimización para Trabajadores:\n" +
                        "- Dedica las primeras horas del día a tareas importantes.\n" +
                        "- Usa la técnica Pomodoro para mantenerte enfocado.\n" +
                        "- Tómate descansos cortos cada 2 horas.";
                break;
            case "estudiante":
                plan = "Plan de Optimización para Estudiantes:\n" +
                        "- Dedica bloques de 90 minutos para estudiar intensamente.\n" +
                        "- Usa descansos activos (caminar, estirarse) entre sesiones de estudio.\n" +
                        "- Prioriza asignaturas más difíciles durante las mañanas.";
                break;
            case "deportista":
                plan = "Plan de Optimización para Deportistas:\n" +
                        "- Planifica entrenamientos intensos en la mañana.\n" +
                        "- Usa la tarde para recuperación activa y estiramientos.\n" +
                        "- Asegúrate de descansar adecuadamente y dormir al menos 8 horas.";
                break;
        }

        planOptimizacion.setText(plan);
    }
}
