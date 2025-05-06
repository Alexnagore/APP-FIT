package com.example.pruebafinal;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pruebafinal.dao.APIRESTEntrenamiento;
import com.example.pruebafinal.modelos.Entrenamiento;

import java.util.ArrayList;
import java.util.List;

public class EntrenamientosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrenamientos);

        ListView listView = findViewById(R.id.entrenamientos_list);

        APIRESTEntrenamiento apiRESTEntrenamiento = new APIRESTEntrenamiento();
        new Thread(() -> {
            List<Entrenamiento> entrenamientos = apiRESTEntrenamiento.getListaEntrenamientos();
            List<String> entrenamientoNames = new ArrayList<>();
            for (Entrenamiento entrenamiento : entrenamientos) {
                entrenamientoNames.add(entrenamiento.getNombre());
            }
            runOnUiThread(() -> {
                ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, entrenamientoNames);
                listView.setAdapter(adapter);
            });
        }).start();
    }
}