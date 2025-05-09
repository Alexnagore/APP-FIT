package com.example.pruebafinal.views;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pruebafinal.GymApp;
import com.example.pruebafinal.R;
import com.example.pruebafinal.business.EjercicioManager;
import com.example.pruebafinal.modelos.Ejercicio;

public class EjercicioDetailsActivity extends AppCompatActivity {

    private EjercicioManager ejercicioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio_details);

        // Obtener el manager de ejercicios desde GymApp
        ejercicioManager = ((GymApp) getApplication()).getEjercicioManager();

        TextView descriptionText = findViewById(R.id.descriptionText);
        TextView scoreText = findViewById(R.id.scoreText);
        TextView timeText = findViewById(R.id.timeText);

        String objectId = getIntent().getStringExtra("objectId");

        if (objectId != null) {
            // Obtener el ejercicio usando EjercicioManager
            ejercicioManager.getEjercicio(objectId, ejercicio -> {
                if (ejercicio != null) {
                    runOnUiThread(() -> {
                        descriptionText.setText("Descripción: " + ejercicio.getNombre());
                        scoreText.setText("Puntuación: " + ejercicio.getPuntuacion());
                        timeText.setText("Tiempo: " + ejercicio.getTiempo() + " segundos");
                    });
                }
            });
        }
    }
}