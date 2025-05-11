package com.example.pruebafinal.views;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import java.io.File;
import java.io.IOException;

import com.example.pruebafinal.GymApp;
import com.example.pruebafinal.R;
import com.example.pruebafinal.business.UsuarioManager;
import com.example.pruebafinal.modelos.Usuario;


public class Perfil extends AppCompatActivity {
    private static final int REQUEST_CODE_CAMERA_PERMISSION = 101;
    private static final int REQUEST_GALLERY_IMAGE = 102;
    private Uri photoURI;

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
        setTitle("Perfil");
        setContentView(R.layout.activity_menu);

        // Obtener el manager de usuarios desde GymApp
        usuarioManager = ((GymApp) getApplication()).getUsuarioManager();

        // Obtener el usuario actual
        Usuario usuario = (Usuario) getIntent().getSerializableExtra("usuario");
        Log.d("Perfil", "Usuario recibido: " + usuario);
        Log.d("Perfil Puntuacion", "Usuario recibido: " + usuario.getPuntuacion());

        // Configurar datos
        TextView userText = findViewById(R.id.editTextText);
        TextView trainText = findViewById(R.id.editTextText2);
        TextView ejercicioText = findViewById(R.id.editTextText3);
        userText.setText(usuario.getNombre());
        trainText.setText(String.valueOf(usuario.getEntrenamientosCompletados()));
        ejercicioText.setText(String.valueOf(usuario.getPuntuacion()));
    }

    public void cambiarImagen(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_GALLERY_IMAGE);
    }

    public void abrirCamara(View view) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CAMERA}, REQUEST_CODE_CAMERA_PERMISSION);
            return;
        }

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = File.createTempFile("temp_image", ".jpg", getExternalFilesDir("Pictures"));
            } catch (IOException ex) {
                Log.e("AbrirCamara", "Error al crear archivo de imagen", ex);
                Toast.makeText(this, "Error al crear el archivo de imagen", Toast.LENGTH_SHORT).show();
            }

            if (photoFile != null) {
                try {
                    photoURI = FileProvider.getUriForFile(this, "com.example.pruebafinal.fileprovider", photoFile);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                    startActivityForResult(intent, REQUEST_CODE_CAMERA_PERMISSION);
                } catch (IllegalArgumentException ex) {
                    Log.e("AbrirCamara", "Error al obtener el URI del archivo", ex);
                    Toast.makeText(this, "Error al obtener el URI del archivo", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CAMERA_PERMISSION && resultCode == RESULT_OK) {
            if (photoURI != null) {
                com.google.android.material.imageview.ShapeableImageView fotoImagenPerfil = findViewById(R.id.profileImage);
                fotoImagenPerfil.setImageURI(photoURI);
                Toast.makeText(this, "Imagen capturada con éxito", Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == REQUEST_GALLERY_IMAGE && resultCode == RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            if (selectedImage != null) {
                com.google.android.material.imageview.ShapeableImageView fotoImagenPerfil = findViewById(R.id.profileImage);
                fotoImagenPerfil.setImageURI(selectedImage);
                Toast.makeText(this, "Imagen seleccionada con éxito", Toast.LENGTH_SHORT).show();
            }
        }
    }
}