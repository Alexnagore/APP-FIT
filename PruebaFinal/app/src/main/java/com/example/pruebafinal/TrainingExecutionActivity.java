package com.example.pruebafinal;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;
import android.os.CountDownTimer;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pruebafinal.modelos.Entrenamiento;
import com.example.pruebafinal.modelos.Ejercicio;
import com.example.pruebafinal.modelos.SesionUsuario;

import java.util.List;

public class TrainingExecutionActivity extends AppCompatActivity implements SensorEventListener {

    private List<Ejercicio> ejercicios;
    private int ejercicioActualIndex = 0;

    private TextView nombreEjercicio;
    private TextView repeticionesTiempo;
    private Button siguienteBtn;
    private Button finalizarBtn;

    private SensorManager sensorManager;
    private Sensor accelerometer;

    private static final float STEP_THRESHOLD = 15f; // Umbral para detectar paso
    private int pasosDetectados = 0;
    private float distanciaObjetivo = 100.0f; // En metros
    private float distanciaRecorrida = 0.0f;
    private boolean carreraActiva = false;

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

        finalizarBtn.setOnClickListener(v -> {
            Toast.makeText(this, "Entrenamiento finalizado manualmente", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, MenuInicial.class); // Asegúrate de que MenuInicial esté bien escrito
            intent.putExtra("usuario", SesionUsuario.getUsuario());
            startActivity(intent);
            finish();
        });
    }

    private void mostrarEjercicioActual() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }

        detenerAcelerometro(); // Por si viene de una carrera anterior

        pasosDetectados = 0;
        distanciaRecorrida = 0;

        Ejercicio e = ejercicios.get(ejercicioActualIndex);
        nombreEjercicio.setText(e.getNombre());

        if (e.getNombre().equalsIgnoreCase("Carrera")) {
            carreraActiva = true;
            distanciaObjetivo = 100.0f; // Puedes personalizarlo con datos del ejercicio
            iniciarAcelerometro();
            repeticionesTiempo.setText("Distancia: 0m / " + (int) distanciaObjetivo + "m");

            siguienteBtn.setEnabled(false);
        }
        else if (e.getTiempo() > 0) {
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

            siguienteBtn.setEnabled(false);
        } else {
            repeticionesTiempo.setText("Repeticiones: " + e.getNumRepeticiones());
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

    private void iniciarAcelerometro() {
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (accelerometer != null) {
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    private void detenerAcelerometro() {
        if (sensorManager != null) {
            sensorManager.unregisterListener(this);
        }
    }

    private int calcularPuntuacionTotal() {
        int total = 0;
        for (Ejercicio e : ejercicios) {
            total += e.getPuntuacion();
        }
        return total;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        detenerAcelerometro();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (!carreraActiva || event.sensor.getType() != Sensor.TYPE_ACCELEROMETER) return;

        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];
        float magnitude = (float) Math.sqrt(x * x + y * y + z * z);

        if (magnitude > STEP_THRESHOLD) {
            pasosDetectados++;
            distanciaRecorrida = pasosDetectados * 0.6f;

            repeticionesTiempo.setText("Distancia: " + (int) distanciaRecorrida + "m / " + (int) distanciaObjetivo + "m");

            if (distanciaRecorrida >= distanciaObjetivo) {
                carreraActiva = false;
                detenerAcelerometro();
                Toast.makeText(this, "¡Carrera completada!", Toast.LENGTH_SHORT).show();
                avanzarAEjercicioSiguiente();
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
    }
}
