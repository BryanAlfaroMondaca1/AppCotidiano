package com.example.appcotidiano;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText username = findViewById(R.id.username);
        EditText password = findViewById(R.id.password);
        Button loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(v -> {
            if (username.getText().toString().equals("admin") && password.getText().toString().equals("admin")) {
                Intent intent = new Intent(LoginActivity.this, MenuPrincipalActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(LoginActivity.this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
