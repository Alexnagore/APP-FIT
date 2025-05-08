package com.example.pruebafinal;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView;

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

                // Set an item click listener
                listView.setOnItemClickListener((parent, view, position, id) -> {
                    String objectId = entrenamientos.get(position).getObjectId(); // Get the objectId
                    Intent intent = new Intent(EntrenamientosActivity.this, TrainingDetailsActivity.class);
                    intent.putExtra("objectId", objectId); // Pass the objectId
                    startActivity(intent);
                });
            });
        }).start();
    }
}