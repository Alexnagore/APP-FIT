package com.example.pruebafinal;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pruebafinal.dao.APIRESTEjercicio;
import com.example.pruebafinal.modelos.Ejercicio;

import java.util.ArrayList;
import java.util.List;

public class EjerciciosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicios);

        ListView listView = findViewById(R.id.ejercicios_list);

        APIRESTEjercicio apiRESTEjercicio = new APIRESTEjercicio();
        new Thread(() -> {
            List<Ejercicio> ejercicios = apiRESTEjercicio.getListaEjercicios();
            List<String> ejercicioNames = new ArrayList<>();
            for (Ejercicio ejercicio : ejercicios) {
                ejercicioNames.add(ejercicio.getNombre());
            }
            runOnUiThread(() -> {
                ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, ejercicioNames);
                listView.setAdapter(adapter);
            });
        }).start();
    }
}