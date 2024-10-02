package com.example.appcotidiano;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MenuPrincipalActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        Button registroButton = findViewById(R.id.registroButton);
        Button alertasButton = findViewById(R.id.alertasButton);
        Button analisisButton = findViewById(R.id.analisisButton);
        Button gamificacionButton = findViewById(R.id.gamificacionButton);
        Button calendarioButton = findViewById(R.id.calendarioButton);

        registroButton.setOnClickListener(v -> startActivity(new Intent(MenuPrincipalActivity.this, RegistroActividadesActivity.class)));
        alertasButton.setOnClickListener(v -> startActivity(new Intent(MenuPrincipalActivity.this, AlertaManagerActivity.class)));
        analisisButton.setOnClickListener(v -> startActivity(new Intent(MenuPrincipalActivity.this, AnalisisActivity.class)));
        gamificacionButton.setOnClickListener(v -> startActivity(new Intent(MenuPrincipalActivity.this, GamificacionActivity.class)));
        calendarioButton.setOnClickListener(v -> startActivity(new Intent(MenuPrincipalActivity.this, IntegracionCalendariosActivity.class)));
    }
}
