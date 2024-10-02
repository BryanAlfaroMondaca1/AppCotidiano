package com.example.appcotidiano;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class GamificacionActivity extends AppCompatActivity {
    private int totalPoints = 0;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamificacion);

        TextView totalPointsTextView = findViewById(R.id.totalPoints);
        TextView motivationMessage = findViewById(R.id.motivationMessage);
        Button incrementPointsButton = findViewById(R.id.incrementPointsButton);

        // Obtener los puntos almacenados en SharedPreferences
        preferences = getSharedPreferences("Gamification", MODE_PRIVATE);
        totalPoints = preferences.getInt("totalPoints", 0);
        totalPointsTextView.setText("Puntos Totales: " + totalPoints);

        // Incrementar puntos al presionar el botón
        incrementPointsButton.setOnClickListener(v -> {
            totalPoints += 10; // Incrementar por cada acción
            preferences.edit().putInt("totalPoints", totalPoints).apply();
            totalPointsTextView.setText("Puntos Totales: " + totalPoints);

            // Mostrar mensaje motivacional si se alcanza cierta meta
            if (totalPoints >= 100) {
                motivationMessage.setText("¡Felicidades! Has alcanzado 100 puntos.");
            } else if (totalPoints >= 50) {
                motivationMessage.setText("¡Muy bien! Ya tienes 50 puntos.");
            } else {
                motivationMessage.setText("");
            }
        });
    }
}
