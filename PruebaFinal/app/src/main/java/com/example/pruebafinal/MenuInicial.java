package com.example.pruebafinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pruebafinal.modelos.Usuario;

public class MenuInicial extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_inicial);

        // Mostrar el usuario recibido
        Usuario usuario = (Usuario) getIntent().getSerializableExtra("usuario");
        TextView userText = findViewById(R.id.user_text);
        userText.setText("Usuario: " + usuario.getNombre());

        // Configurar botones
        Button scoresButton = findViewById(R.id.scores_button);
        Button trainingsButton = findViewById(R.id.trainings_button);
        Button exercisesButton = findViewById(R.id.exercises_button);
        Button profileButton = findViewById(R.id.profile_button);

        scoresButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Acción para Tabla de Puntuaciones
                System.out.println("Tabla de Puntuaciones seleccionada");
                Intent intent = new Intent(MenuInicial.this, TablaPuntuaciones.class);
                startActivity(intent);
            }
        });

        trainingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuInicial.this, EntrenamientosActivity.class);
                System.out.println("Entrenamientos seleccionados");
                startActivity(intent); // Añadida esta línea para iniciar la actividad
            }
        });

        exercisesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuInicial.this, EjerciciosActivity.class);
                System.out.println("Ejercicios seleccionados");
                startActivity(intent); // Añadida esta línea para iniciar la actividad
            }
        });

        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuInicial.this, Perfil.class);
                intent.putExtra("usuario", usuario);
                startActivity(intent);
                // Acción para Perfil
                System.out.println("Perfil seleccionado");
            }
        });
    }
}