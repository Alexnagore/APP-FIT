package com.example.pruebafinal.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pruebafinal.GymApp;
import com.example.pruebafinal.R;
import com.example.pruebafinal.business.EjercicioManager;
import com.example.pruebafinal.modelos.Ejercicio;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EjerciciosActivity extends AppCompatActivity {

    private EjercicioManager ejercicioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
            getWindow().setDecorFitsSystemWindows(false);
        } else {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        }
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicios);

        // Obtener el manager de ejercicios desde GymApp
        ejercicioManager = ((GymApp) getApplication()).getEjercicioManager();

        ListView listView = findViewById(R.id.ejercicios_list);

        // Obtener la lista de ejercicios usando EjercicioManager
        ejercicioManager.getListaEjercicios(ejercicios -> {
            List<String> ejercicioNames = new ArrayList<>();
            for (Ejercicio ejercicio : ejercicios) {
                if(Objects.equals(ejercicio.getNombre(), "Descanso")) {
                    continue;
                }
                ejercicioNames.add(ejercicio.getNombre());
            }
            runOnUiThread(() -> {
                ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, ejercicioNames);
                listView.setAdapter(adapter);

                // Pass only the objectId to the next activity
                listView.setOnItemClickListener((parent, view, position, id) -> {
                    String objectId = ejercicios.get(position).getObjectId(); // Get the objectId
                    Intent intent = new Intent(EjerciciosActivity.this, EjercicioDetailsActivity.class);
                    intent.putExtra("objectId", objectId); // Pass the objectId
                    startActivity(intent);
                });
            });
        });
    }
}