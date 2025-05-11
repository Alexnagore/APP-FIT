package com.example.pruebafinal.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pruebafinal.GymApp;
import com.example.pruebafinal.R;
import com.example.pruebafinal.business.EntrenamientoManager;
import com.example.pruebafinal.modelos.Entrenamiento;

import java.util.ArrayList;
import java.util.List;

public class EntrenamientosActivity extends AppCompatActivity {

    private EntrenamientoManager entrenamientoManager;

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
        setContentView(R.layout.activity_entrenamientos);

        // Obtener el manager de entrenamientos desde GymApp
        entrenamientoManager = ((GymApp) getApplication()).getEntrenamientoManager();

        ListView listView = findViewById(R.id.entrenamientos_list);

        // Obtener la lista de entrenamientos usando EntrenamientoManager
        entrenamientoManager.getListaEntrenamientos(entrenamientos -> {
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
                    Intent intent = new Intent(EntrenamientosActivity.this, GymApp.TrainingDetailsActivity.class);
                    intent.putExtra("objectId", objectId); // Pass the objectId
                    startActivity(intent);
                });
            });
        });
    }
}