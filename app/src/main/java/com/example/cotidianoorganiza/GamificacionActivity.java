package com.example.cotidianoorganiza;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class GamificacionActivity extends AppCompatActivity {

    private TextView temporizadorText, frasesMotivacionalesText, tableroRecompensasText;
    private Button iniciarPomodoroButton;
    private ProgressBar barraProgreso;
    private int pomodorosCompletados = 0;  // Contador de pomodoros completados
    private CountDownTimer countDownTimer;
    private boolean pomodoroEnCurso = false;

    // Duración del Pomodoro y del Descanso (modificados para pruebas)
    private static final long POMODORO_TIEMPO = 10 * 1000; // 10 segundos en milisegundos
    private static final long DESCANSO_TIEMPO = 5 * 1000;  // 5 segundos en milisegundos
    private long tiempoRestante = POMODORO_TIEMPO;  // Tiempo restante en el temporizador
    private ArrayList<String> recompensas;  // Lista para guardar las recompensas obtenidas

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamificacion);

        temporizadorText = findViewById(R.id.temporizadorText);
        iniciarPomodoroButton = findViewById(R.id.iniciarPomodoroButton);
        frasesMotivacionalesText = findViewById(R.id.frasesMotivacionalesText);
        barraProgreso = findViewById(R.id.barraProgreso);
        tableroRecompensasText = findViewById(R.id.tableroRecompensasText);
        recompensas = new ArrayList<>();

        // Mostrar la barra de progreso aunque no haya datos
        barraProgreso.setMax(4);  // 4 Pomodoros completan la barra
        actualizarBarraProgreso();  // Siempre mostrar la barra, aunque esté en 0

        iniciarPomodoroButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!pomodoroEnCurso) {
                    iniciarPomodoro();
                } else {
                    Toast.makeText(GamificacionActivity.this, "Pomodoro ya en curso", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // Método para iniciar el temporizador Pomodoro
    private void iniciarPomodoro() {
        pomodoroEnCurso = true;
        iniciarPomodoroButton.setText("Pomodoro en curso...");

        countDownTimer = new CountDownTimer(tiempoRestante, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tiempoRestante = millisUntilFinished;
                actualizarTemporizadorText();
            }

            @Override
            public void onFinish() {
                pomodoroEnCurso = false;
                pomodorosCompletados++;
                actualizarBarraProgreso();
                otorgarRecompensa();
                mostrarFraseMotivacional();
                mostrarEvaluacionRendimiento();
                tiempoRestante = DESCANSO_TIEMPO;
                iniciarPomodoroButton.setText("Iniciar Descanso");

                // Después del descanso, podemos reiniciar el Pomodoro si así lo deseas.
                countDownTimer = new CountDownTimer(DESCANSO_TIEMPO, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        tiempoRestante = millisUntilFinished;
                        actualizarTemporizadorText();
                    }

                    @Override
                    public void onFinish() {
                        tiempoRestante = POMODORO_TIEMPO;
                        iniciarPomodoroButton.setText("Iniciar nuevo Pomodoro");
                    }
                }.start();
            }
        }.start();
    }

    // Método para actualizar el texto del temporizador
    private void actualizarTemporizadorText() {
        int segundos = (int) (tiempoRestante / 1000);
        String tiempoFormateado = String.format("%02d", segundos);
        temporizadorText.setText(tiempoFormateado);
    }

    // Método para actualizar la barra de progreso, incluso si el progreso es 0
    private void actualizarBarraProgreso() {
        barraProgreso.setProgress(pomodorosCompletados);
    }

    // Método para otorgar una recompensa
    private void otorgarRecompensa() {
        if (pomodorosCompletados % 4 == 0) {
            recompensas.add("Gran recompensa por completar 4 Pomodoros");
            Toast.makeText(this, "¡Gran Recompensa! Has completado 4 Pomodoros", Toast.LENGTH_LONG).show();
        } else {
            recompensas.add("Recompensa por completar un Pomodoro");
            Toast.makeText(this, "¡Recompensa! Has completado un Pomodoro", Toast.LENGTH_SHORT).show();
        }
        actualizarTableroRecompensas();
    }

    // Método para mostrar frases motivacionales personalizadas
    private void mostrarFraseMotivacional() {
        String[] frases = {
                "¡Bien hecho! Has dado un paso más hacia tu objetivo.",
                "Sigue así, cada Pomodoro te acerca al éxito.",
                "¡Fantástico trabajo! Estás progresando de manera increíble.",
                "Tu esfuerzo está dando frutos, no te detengas.",
                "¡Increíble! Estás construyendo un hábito positivo."
        };
        int indiceAleatorio = (int) (Math.random() * frases.length);
        frasesMotivacionalesText.setText(frases[indiceAleatorio]);
    }

    // Método para mostrar la evaluación de rendimiento
    private void mostrarEvaluacionRendimiento() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Evaluación de Rendimiento");
        builder.setMessage("¿Te sentiste productivo durante este Pomodoro?");
        builder.setPositiveButton("Sí", (dialog, which) -> {
            Toast.makeText(this, "¡Genial! Sigue trabajando duro.", Toast.LENGTH_SHORT).show();
        });
        builder.setNegativeButton("No", (dialog, which) -> {
            Toast.makeText(this, "¡No te preocupes! Sigue intentándolo, lo lograrás.", Toast.LENGTH_SHORT).show();
        });
        builder.show();
    }

    // Método para actualizar el tablero de recompensas
    private void actualizarTableroRecompensas() {
        StringBuilder recompensasTexto = new StringBuilder();
        for (String recompensa : recompensas) {
            recompensasTexto.append(recompensa).append("\n");
        }
        tableroRecompensasText.setText(recompensasTexto.toString());
    }
}
