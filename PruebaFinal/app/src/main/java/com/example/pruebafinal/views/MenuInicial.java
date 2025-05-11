package com.example.pruebafinal.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pruebafinal.GymApp;
import com.example.pruebafinal.R;
import com.example.pruebafinal.business.UsuarioManager;
import com.example.pruebafinal.modelos.Usuario;

public class MenuInicial extends AppCompatActivity {

    private UsuarioManager usuarioManager;

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
        setContentView(R.layout.activity_menu_inicial);

        // Obtener el manager de usuarios desde GymApp
        usuarioManager = ((GymApp) getApplication()).getUsuarioManager();

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
                Intent intent = new Intent(MenuInicial.this, GymApp.TablaPuntuaciones.class);
                startActivity(intent);
            }
        });

        trainingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Acción para Entrenamientos
                Intent intent = new Intent(MenuInicial.this, EntrenamientosActivity.class);
                startActivity(intent);
            }
        });

        exercisesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Acción para Ejercicios
                Intent intent = new Intent(MenuInicial.this, EjerciciosActivity.class);
                startActivity(intent);
            }
        });

        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Acción para Perfil
                Intent intent = new Intent(MenuInicial.this, Perfil.class);
                intent.putExtra("usuario", usuario);
                startActivity(intent);
            }
        });
    }
}