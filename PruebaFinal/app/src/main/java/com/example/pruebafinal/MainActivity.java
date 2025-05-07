package com.example.pruebafinal;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pruebafinal.dao.APIRESTUsuario;
import com.example.pruebafinal.modelos.SesionUsuario;
import com.example.pruebafinal.modelos.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        APIRESTUsuario apiRESTUsuario = new APIRESTUsuario();
        AtomicReference<List<Usuario>> listaUsuarios = new AtomicReference<>(new ArrayList<>());
        apiRESTUsuario.getListaUsuarios(usuarios -> {
            listaUsuarios.set(usuarios);
            Log.d("API", "Cantidad de usuarios: " + listaUsuarios);
        });

        Button startButton = findViewById(R.id.start_button);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Guardar nombre de usuario
                String username = ((EditText) findViewById(R.id.username_input)).getText().toString();
                // Comprobar si en la base de datos existe ese usuario con ese nombre
                boolean usuarioExiste = false;
                Usuario nuevoUsuario = null;
                for (Usuario u : listaUsuarios.get()) {
                    nuevoUsuario = u;
                    if (u.getNombre().equals(username)) {
                        usuarioExiste = true;
                        Log.d("Usuario Existente", "Usuario existente: " + nuevoUsuario);
                        break;
                    }
                }
                if (!usuarioExiste) {
                    // Crear un nuevo usuario
                    nuevoUsuario = new Usuario(username);
                    apiRESTUsuario.agregarUsuario(nuevoUsuario);
                    Log.d("Usuario Nuevo", "Usuario nuevo: " + nuevoUsuario);
                }
                // Abrir actividad de MenuInicial, le paso el usuario
                SesionUsuario.setUsuario(nuevoUsuario);
                Intent intent = new Intent(MainActivity.this, MenuInicial.class);
                intent.putExtra("usuario", nuevoUsuario);
                startActivity(intent);

            }
        });
    }
}