package com.example.pruebafinal;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;
import android.os.CountDownTimer;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pruebafinal.modelos.Entrenamiento;
import com.example.pruebafinal.modelos.Ejercicio;

import java.util.List;


public class TrainingExecutionActivity extends AppCompatActivity {

    private List<Ejercicio> ejercicios;
    private int ejercicioActualIndex = 0;

    private TextView nombreEjercicio;
    private TextView repeticionesTiempo;
    private Button siguienteBtn;
    private Button finalizarBtn;

    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training_execution);

        nombreEjercicio = findViewById(R.id.nombreEjercicio);
        repeticionesTiempo = findViewById(R.id.repeticionesTiempo);
        siguienteBtn = findViewById(R.id.siguienteBtn);
        finalizarBtn = findViewById(R.id.finalizarBtn);

        Entrenamiento entrenamiento = (Entrenamiento) getIntent().getSerializableExtra("entrenamiento");
        if (entrenamiento != null) {
            ejercicios = entrenamiento.getEjercicios();
            mostrarEjercicioActual();
        }

        siguienteBtn.setOnClickListener(v -> avanzarAEjercicioSiguiente());

    }

    private void mostrarEjercicioActual() {
        // Cancelar temporizador anterior si hay uno
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }

        Ejercicio e = ejercicios.get(ejercicioActualIndex);
        nombreEjercicio.setText(e.getNombre());

        if (e.getTiempo() > 0) {
            repeticionesTiempo.setText("Duración: " + e.getTiempo() + "s");

            countDownTimer = new CountDownTimer(e.getTiempo() * 1000L, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    repeticionesTiempo.setText("Duración restante: " + millisUntilFinished / 1000 + "s");
                }

                @Override
                public void onFinish() {
                    avanzarAEjercicioSiguiente();
                }
            }.start();

            // Ocultar botón de siguiente, se avanza automáticamente
            siguienteBtn.setEnabled(false);
        } else {
            // Mostrar repeticiones
            repeticionesTiempo.setText("Repeticiones: " + e.getNumRepeticiones());

            // Activar botón de siguiente
            siguienteBtn.setEnabled(true);
        }
    }

    private void avanzarAEjercicioSiguiente() {
        if (ejercicioActualIndex < ejercicios.size() - 1) {
            ejercicioActualIndex++;
            mostrarEjercicioActual();
        } else {
            Toast.makeText(this, "¡Entrenamiento finalizado!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, ResumenActivity.class);
            intent.putExtra("puntuacionTotal", calcularPuntuacionTotal());
            startActivity(intent);
            finish();

        }
    }

    private int calcularPuntuacionTotal() {
        int total = 0;
        for (Ejercicio e : ejercicios) {
            total += e.getPuntuacion();
        }
        return total;
    }


}
