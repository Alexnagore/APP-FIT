package com.example.pruebafinal.views;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pruebafinal.OnTouchPaintLineView;
import com.example.pruebafinal.R;
import com.example.pruebafinal.modelos.Usuario;

public class DrawingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawing);

        Usuario usuario = (Usuario) getIntent().getSerializableExtra("usuario");

        OnTouchPaintLineView paintView = findViewById(R.id.paintView);

        // Cambiar texto del botón
        Button volverInicioBtn = findViewById(R.id.volverInicioBtn);

        // Selector de colores
        findViewById(R.id.colorRed).setOnClickListener(v -> paintView.setDrawColor(Color.RED));
        findViewById(R.id.colorBlue).setOnClickListener(v -> paintView.setDrawColor(Color.BLUE));
        findViewById(R.id.colorGreen).setOnClickListener(v -> paintView.setDrawColor(Color.GREEN));
        findViewById(R.id.colorYellow).setOnClickListener(v -> paintView.setDrawColor(Color.YELLOW));
        findViewById(R.id.colorBlack).setOnClickListener(v -> paintView.setDrawColor(Color.BLACK));

        volverInicioBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, MenuInicial.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("usuario", usuario);
            startActivity(intent);
            finish();
        });
    }
}