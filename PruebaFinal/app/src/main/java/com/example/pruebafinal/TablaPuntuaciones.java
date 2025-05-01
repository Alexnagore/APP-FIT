package com.example.pruebafinal;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.pruebafinal.dao.APIRESTUsuario;

import java.util.List;
import java.util.stream.Collectors;

public class TablaPuntuaciones extends AppCompatActivity {

    private List<String> texts;
    private ArrayAdapter<String> itemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Tabla de Puntuaciones");
        setContentView(R.layout.activity_tabla_puntuaciones);

        APIRESTUsuario apiRESTUsuario = new APIRESTUsuario();
        apiRESTUsuario.getListaUsuarios(usuarios -> {
            runOnUiThread(() -> {
                texts = usuarios.stream()
                        .sorted((u1, u2) -> Integer.compare(u2.getPuntuacion(), u1.getPuntuacion())) // Ordenar por puntuación descendente
                        .map(usuario -> usuario.getNombre() + ": " + usuario.getPuntuacion())
                        .collect(Collectors.toList());

                ListView listView = findViewById(R.id.list);
                itemsAdapter = new ArrayAdapter<String>(this, R.layout.row_layout, R.id.listText, texts) {
                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {
                        View view = super.getView(position, convertView, parent);
                        if (position == 0) {
                            view.setBackgroundColor(Color.parseColor("#FFD700")); // Oro
                        } else if (position == 1) {
                            view.setBackgroundColor(Color.parseColor("#C0C0C0")); // Plata
                        } else if (position == 2) {
                            view.setBackgroundColor(Color.parseColor("#CD7F32")); // Bronce
                        } else {
                            view.setBackgroundColor(Color.WHITE); // Blanco
                        }
                        return view;
                    }
                };

                listView.setAdapter(itemsAdapter);
            });
        });
    }
}