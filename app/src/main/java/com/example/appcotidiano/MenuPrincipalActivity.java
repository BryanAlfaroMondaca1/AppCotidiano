package com.example.cotidiano;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MenuPrincipalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        Button btnRegistro = findViewById(R.id.btnRegistroActividades);
        Button btnRecordatorios = findViewById(R.id.btnRecordatorios);
        Button btnAnalisis = findViewById(R.id.btnAnalisis);
        Button btnGamificacion = findViewById(R.id.btnGamificacion);
        Button btnCalendario = findViewById(R.id.btnCalendario);
        Button btnResumenSemanal = findViewById(R.id.btnResumenSemanal);

        // Configurar acciones para cada botón
        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuPrincipalActivity.this, RegistroActividadesActivity.class);
                startActivity(intent);
            }
        });

        btnRecordatorios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuPrincipalActivity.this, RecordatoriosActivity.class);
                startActivity(intent);
            }
        });
/*
        btnAnalisis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuPrincipalActivity.this, AnalisisActivity.class);
                startActivity(intent);
            }
        });*/

        btnGamificacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuPrincipalActivity.this, GamificacionActivity.class);
                startActivity(intent);
            }
        });

        btnCalendario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuPrincipalActivity.this, CalendarioActivity.class);
                startActivity(intent);
            }
        });

        btnResumenSemanal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuPrincipalActivity.this, ResumenSemanalActivity.class);
                startActivity(intent);
            }
        });
    }
}
