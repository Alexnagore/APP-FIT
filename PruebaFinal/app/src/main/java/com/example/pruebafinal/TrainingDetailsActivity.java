package com.example.pruebafinal;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pruebafinal.dao.APIRESTEntrenamiento;
import com.example.pruebafinal.modelos.Entrenamiento;
import com.example.pruebafinal.modelos.Ejercicio;

import java.util.List;

public class TrainingDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training_details);

        TextView titleText = findViewById(R.id.titleText);
        TextView trainingProgram = findViewById(R.id.trainingProgram);
        TextView scoreText = findViewById(R.id.scoreText);
        TextView exercisesTextView = findViewById(R.id.exercisesTextView);
        Button startButton = findViewById(R.id.startButton);

        String objectId = getIntent().getStringExtra("objectId"); // Get the objectId

        if (objectId != null) {
            new Thread(() -> {
                APIRESTEntrenamiento apiRESTEntrenamiento = new APIRESTEntrenamiento();
                Entrenamiento entrenamiento = apiRESTEntrenamiento.getEntrenamiento(objectId);

                if (entrenamiento != null) {
                    runOnUiThread(() -> {
                        trainingProgram.setText(entrenamiento.getNombre());
                        scoreText.setText("Puntuación: " + entrenamiento.getPuntuacion());

                        List<Ejercicio> ejercicios = entrenamiento.getEjercicios();
                        StringBuilder ejerciciosText = new StringBuilder();
                        for (Ejercicio ejercicio : ejercicios) {
                            ejerciciosText.append("• ").append(ejercicio.getNombre()).append("\n");
                        }
                        exercisesTextView.setText(ejerciciosText.toString());
                    });
                }
            }).start();
        }

        startButton.setOnClickListener(v -> {
            new Thread(() -> {
                APIRESTEntrenamiento apiRESTEntrenamiento = new APIRESTEntrenamiento();
                Entrenamiento entrenamiento = apiRESTEntrenamiento.getEntrenamiento(objectId);

                if (entrenamiento != null) {
                    runOnUiThread(() -> {
                        Intent intent = new Intent(TrainingDetailsActivity.this, TrainingExecutionActivity.class);
                        intent.putExtra("entrenamiento", entrenamiento);
                        startActivity(intent);
                    });
                }
            }).start();
        });

    }
}