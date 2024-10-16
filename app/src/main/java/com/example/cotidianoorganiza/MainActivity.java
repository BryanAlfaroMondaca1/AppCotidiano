package com.example.cotidianoorganiza;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button registroActividades, alertas, analisis, gamificacion, calendario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registroActividades = findViewById(R.id.registroActividades);
        alertas = findViewById(R.id.alertas);
        analisis = findViewById(R.id.analisis);
        gamificacion = findViewById(R.id.gamificacion);
        calendario = findViewById(R.id.calendario);

        registroActividades.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, RegistroActividadesActivity.class)));
        alertas.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, AlertasActivity.class)));
        analisis.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, AnalisisActivity.class)));
        gamificacion.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, GamificacionActivity.class)));
        calendario.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, CalendarioActivity.class)));
    }
}
