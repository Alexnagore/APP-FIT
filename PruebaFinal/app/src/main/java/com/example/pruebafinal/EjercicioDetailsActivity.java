package com.example.pruebafinal;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pruebafinal.dao.APIRESTEjercicio;
import com.example.pruebafinal.modelos.Ejercicio;

public class EjercicioDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio_details);

        TextView descriptionText = findViewById(R.id.descriptionText);
        TextView scoreText = findViewById(R.id.scoreText);
        TextView timeText = findViewById(R.id.timeText);

        String objectId = getIntent().getStringExtra("objectId");

        if (objectId != null) {
            new Thread(() -> {
                APIRESTEjercicio apiRESTEjercicio = new APIRESTEjercicio();
                Ejercicio ejercicio = apiRESTEjercicio.getEjercicio(objectId);

                if (ejercicio != null) {
                    runOnUiThread(() -> {
                        descriptionText.setText("Descripción: " + ejercicio.getNombre());
                        scoreText.setText("Puntuación: " + ejercicio.getPuntuacion());
                        timeText.setText("Tiempo: " + ejercicio.getTiempo() + " segundos");
                    });
                }
            }).start();
        }
    }
}