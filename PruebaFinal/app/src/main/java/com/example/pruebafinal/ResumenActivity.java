package com.example.pruebafinal;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pruebafinal.dao.APIRESTUsuario;
import com.example.pruebafinal.modelos.SesionUsuario;
import com.example.pruebafinal.modelos.Usuario;

public class ResumenActivity extends AppCompatActivity {

    private Usuario usuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumen);

        TextView puntuacionTextView = findViewById(R.id.puntuacionTextView);
        Button volverInicioBtn = findViewById(R.id.volverInicioBtn);

        int puntuacionTotal = getIntent().getIntExtra("puntuacionTotal", 0);
        usuario = SesionUsuario.getUsuario();

        puntuacionTextView.setText("Puntuación total: " + puntuacionTotal);

        if (usuario != null) {
            usuario.setPuntuacion(usuario.getPuntuacion() + puntuacionTotal);
            usuario.setEntrenamientosCompletados(usuario.getEntrenamientosCompletados() + 1);

            new Thread(() -> {
                APIRESTUsuario api = new APIRESTUsuario();
                api.actualizaUsuario(usuario);
            }).start();
        }

        volverInicioBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, MenuInicial.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("usuario", usuario);
            startActivity(intent);
            finish();
        });
    }
}
