package com.example.appcotidiano;

import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AlertaManagerActivity extends AppCompatActivity {

    private TextView clockView;
    private TextView timerView;
    private Handler handler;
    private Runnable runnable;
    private int countdownMinutes = 15; // Temporizador de ejemplo para 15 minutos

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Asegúrate de usar el layout correcto aquí
        setContentView(R.layout.activity_alerta_manager); // Referencia al archivo XML correcto

        // Referencias a los elementos del diseño
        clockView = findViewById(R.id.clockView);
        timerView = findViewById(R.id.timerView);
        TextView alertMessage = findViewById(R.id.alertMessage);

        // Configurar el mensaje de alerta
        alertMessage.setText("¡Mantente enfocado en tu próxima tarea!");

        // Actualizar la hora actual y el temporizador
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                // Actualizar la hora actual en el reloj digital
                updateClock();

                // Actualizar el temporizador
                updateTimer();

                // Ejecutar el runnable cada segundo
                handler.postDelayed(this, 1000);
            }
        };

        // Iniciar la actualización del reloj y temporizador
        handler.post(runnable);
    }

    private void updateClock() {
        // Obtener la hora actual y mostrarla en el formato HH:mm:ss
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        String currentTime = format.format(calendar.getTime());
        clockView.setText(currentTime);
    }

    private void updateTimer() {
        // Calcular el tiempo restante en minutos y segundos
        int minutesLeft = countdownMinutes - Calendar.getInstance().get(Calendar.MINUTE) % countdownMinutes;
        int secondsLeft = 60 - Calendar.getInstance().get(Calendar.SECOND);

        // Formatear el tiempo restante como MM:SS
        String timeRemaining = String.format(Locale.getDefault(), "%02d:%02d", minutesLeft, secondsLeft);
        timerView.setText("Tiempo Restante: " + timeRemaining);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Detener el handler al destruir la actividad
        handler.removeCallbacks(runnable);
    }
}
