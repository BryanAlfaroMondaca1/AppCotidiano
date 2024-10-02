package com.example.appcotidiano;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class AnalisisActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analisis);

        // Referencias a los elementos del diseño
        TextView predeterminedMessage = findViewById(R.id.predeterminedMessage);
        Button deporteButton = findViewById(R.id.deporteButton);
        Button trabajoButton = findViewById(R.id.trabajoButton);
        Button estudioButton = findViewById(R.id.estudioButton);
        TextView focusedRecommendation = findViewById(R.id.focusedRecommendation);

        // Mensaje predeterminado
        predeterminedMessage.setText("Te recomendamos dedicar más tiempo a actividades que equilibren tu jornada laboral con un descanso saludable y momentos de ejercicio.");

        // Configuración de las recomendaciones específicas
        deporteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                focusedRecommendation.setText("Para mejorar tu salud y productividad, te sugerimos incorporar 30 minutos de ejercicio al día. ¡Tu cuerpo y mente te lo agradecerán!");
            }
        });

        trabajoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                focusedRecommendation.setText("Para un mejor rendimiento laboral, organiza tus tareas en bloques de 90 minutos y toma descansos breves entre cada bloque para mantener la concentración.");
            }
        });

        estudioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                focusedRecommendation.setText("Si deseas mejorar en tus estudios, te sugerimos dividir las sesiones de estudio en periodos de 50 minutos y tomar descansos de 10 minutos para evitar la fatiga mental.");
            }
        });
    }
}
