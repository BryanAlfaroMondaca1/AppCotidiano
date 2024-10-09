package com.example.cotidiano;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class GamificacionActivity extends AppCompatActivity {

    private TextView tvTiempoRestante;
    private Button btnIniciarPomodoro, btnDetenerPomodoro;
    private CountDownTimer pomodoroTimer;
    private boolean isRunning = false;
    private long tiempoRestanteEnMilisegundos = 1500000; // 25 minutos en milisegundos

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamificacion);

        // Enlazar componentes del layout
        tvTiempoRestante = findViewById(R.id.tvTiempoRestante);
        btnIniciarPomodoro = findViewById(R.id.btnIniciarPomodoro);
        btnDetenerPomodoro = findViewById(R.id.btnDetenerPomodoro);

        // Configurar botón de iniciar
        btnIniciarPomodoro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isRunning) {
                    iniciarPomodoro();
                }
            }
        });

        // Configurar botón de detener
        btnDetenerPomodoro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detenerPomodoro();
            }
        });
    }

    // Método para iniciar el Pomodoro
    private void iniciarPomodoro() {
        pomodoroTimer = new CountDownTimer(tiempoRestanteEnMilisegundos, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tiempoRestanteEnMilisegundos = millisUntilFinished;
                actualizarTemporizador();
            }

            @Override
            public void onFinish() {
                isRunning = false;
                Toast.makeText(GamificacionActivity.this, "¡Pomodoro Completado! ¡Toma un descanso!", Toast.LENGTH_SHORT).show();
                tvTiempoRestante.setText("00:00");
            }
        }.start();

        isRunning = true;
        btnIniciarPomodoro.setEnabled(false);
        btnDetenerPomodoro.setEnabled(true);
    }

    // Método para detener el Pomodoro
    private void detenerPomodoro() {
        if (pomodoroTimer != null) {
            pomodoroTimer.cancel();
            isRunning = false;
            btnIniciarPomodoro.setEnabled(true);
            btnDetenerPomodoro.setEnabled(false);
            Toast.makeText(GamificacionActivity.this, "Pomodoro detenido.", Toast.LENGTH_SHORT).show();
        }
    }

    // Actualizar el temporizador en el TextView
    private void actualizarTemporizador() {
        int minutos = (int) (tiempoRestanteEnMilisegundos / 1000) / 60;
        int segundos = (int) (tiempoRestanteEnMilisegundos / 1000) % 60;

        String tiempoFormateado = String.format("%02d:%02d", minutos, segundos);
        tvTiempoRestante.setText(tiempoFormateado);
    }
}
