package com.example.pruebafinal.views;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pruebafinal.OnTouchPaintLineView;
import com.example.pruebafinal.R;
import com.example.pruebafinal.modelos.Usuario;

public class DrawingActivity extends AppCompatActivity {

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