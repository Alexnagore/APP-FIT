package com.example.pruebafinal.views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pruebafinal.GymApp;
import com.example.pruebafinal.R;
import com.example.pruebafinal.business.UsuarioManager;
import com.example.pruebafinal.modelos.SesionUsuario;
import com.example.pruebafinal.modelos.Usuario;

public class MainActivity extends AppCompatActivity {

    private UsuarioManager usuarioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Obtener el manager de usuarios desde la aplicación
        usuarioManager = ((GymApp) getApplication()).getUsuarioManager();

        Button startButton = findViewById(R.id.start_button);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Guardar nombre de usuario
                String username = ((EditText) findViewById(R.id.username_input)).getText().toString();

                // Buscar si existe un usuario con ese nombre
                usuarioManager.getListaUsuarios(usuarios -> {
                    boolean usuarioExiste = false;
                    Usuario nuevoUsuario = null;

                    for (Usuario u : usuarios) {
                        if (u.getNombre().equals(username)) {
                            usuarioExiste = true;
                            nuevoUsuario = u;
                            Log.d("Usuario Existente", "Usuario existente: " + nuevoUsuario);
                            break;
                        }
                    }

                    if (!usuarioExiste) {
                        // Crear un nuevo usuario
                        nuevoUsuario = new Usuario(username);
                        final Usuario finalUsuario = nuevoUsuario;
                        usuarioManager.agregarUsuario(finalUsuario);
                        Log.d("Usuario Nuevo", "Usuario nuevo: " + finalUsuario);
                    }

                    // Abrir actividad de MenuInicial, le paso el usuario
                    final Usuario finalUsuario = nuevoUsuario;
                    SesionUsuario.setUsuario(finalUsuario);

                    runOnUiThread(() -> {
                        Intent intent = new Intent(MainActivity.this, MenuInicial.class);
                        intent.putExtra("usuario", finalUsuario);
                        startActivity(intent);
                    });
                });
            }
        });
    }
}