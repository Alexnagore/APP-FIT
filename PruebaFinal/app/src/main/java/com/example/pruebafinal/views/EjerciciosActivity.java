package com.example.pruebafinal.views;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pruebafinal.GymApp;
import com.example.pruebafinal.R;
import com.example.pruebafinal.business.EjercicioManager;
import com.example.pruebafinal.modelos.Ejercicio;

import java.util.ArrayList;
import java.util.List;

public class EjerciciosActivity extends AppCompatActivity {

    private EjercicioManager ejercicioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicios);

        // Obtener el manager de ejercicios desde GymApp
        ejercicioManager = ((GymApp) getApplication()).getEjercicioManager();

        ListView listView = findViewById(R.id.ejercicios_list);

        // Obtener la lista de ejercicios usando EjercicioManager
        ejercicioManager.getListaEjercicios(ejercicios -> {
            List<String> ejercicioNames = new ArrayList<>();
            for (Ejercicio ejercicio : ejercicios) {
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