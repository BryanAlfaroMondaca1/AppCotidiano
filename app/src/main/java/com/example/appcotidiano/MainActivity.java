package com.example.cotidiano;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText username = findViewById(R.id.username);
        EditText password = findViewById(R.id.password);
        Button loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();

                // Verificar credenciales (usuario: admin, contraseña: admin)
                if (user.equals("admin") && pass.equals("admin")) {
                    // Si son correctas, pasar al menú principal
                    Intent intent = new Intent(MainActivity.this, MenuPrincipalActivity.class);
                    startActivity(intent);
                } else {
                    // Si son incorrectas, mostrar mensaje de error
                    Toast.makeText(MainActivity.this, "Credenciales Incorrectas", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
