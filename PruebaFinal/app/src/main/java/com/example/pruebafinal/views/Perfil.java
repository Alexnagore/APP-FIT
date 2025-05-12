package com.example.pruebafinal.views;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.pruebafinal.GymApp;
import com.example.pruebafinal.R;
import com.example.pruebafinal.business.UsuarioManager;
import com.example.pruebafinal.modelos.Usuario;
import com.google.android.material.imageview.ShapeableImageView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class Perfil extends AppCompatActivity {
    private static final int REQUEST_CODE_CAMERA_PERMISSION = 101;
    private static final int REQUEST_GALLERY_IMAGE = 102;
    private Uri photoURI;

    private UsuarioManager usuarioManager;
    private Usuario usuario;
    private ShapeableImageView profileImageView;

    // Back4App configuration
    private static final String API_URL = "https://parseapi.back4app.com/classes/Usuario";
    private static final String APPLICATION_ID = "ifahq2RS6hzk7Ea18yPQ42ZjZbMIGQFlWvgGFIAT";
    private static final String REST_API_KEY = "eCPGb30Ej1NSLXzTDwbvjpx7CW33nOKG1OZ1s8Jt";
    private RequestQueue requestQueue;

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

        // Inicializar RequestQueue para Volley
        requestQueue = Volley.newRequestQueue(this);

        // Obtener el manager de usuarios desde GymApp
        usuarioManager = ((GymApp) getApplication()).getUsuarioManager();

        // Obtener el usuario actual
        usuario = (Usuario) getIntent().getSerializableExtra("usuario");
        Log.d("Perfil", "Usuario recibido: " + usuario);
        Log.d("Perfil Puntuacion", "Usuario recibido: " + usuario.getPuntuacion());

        // Configurar datos
        TextView userText = findViewById(R.id.editTextText);
        TextView trainText = findViewById(R.id.editTextText2);
        TextView ejercicioText = findViewById(R.id.editTextText3);
        userText.setText(usuario.getNombre());
        trainText.setText(String.valueOf(usuario.getEntrenamientosCompletados()));
        ejercicioText.setText(String.valueOf(usuario.getPuntuacion()));

        // Inicializar la imagen de perfil
        profileImageView = findViewById(R.id.profileImage);

        // Cargar la imagen del usuario si existe
        cargarImagenDePerfil();
    }

    // Método para cargar la imagen de perfil desde Back4App
    private void cargarImagenDePerfil() {
        // Crear una solicitud para obtener los datos del usuario
        String userId = usuario.getObjectId(); // Asumiendo que el usuario tiene un ID
        String url = API_URL + "/" + userId;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            // Verificar si el usuario tiene una imagen
                            if (response.has("imagenPerfil")) {
                                String base64Image = response.getString("imagenPerfil");
                                if (base64Image != null && !base64Image.isEmpty()) {
                                    // Decodificar la imagen en base64
                                    byte[] decodedString = Base64.decode(base64Image, Base64.DEFAULT);
                                    Bitmap bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

                                    // Mostrar la imagen
                                    profileImageView.setImageBitmap(bitmap);
                                }
                            }
                        } catch (JSONException e) {
                            Log.e("Perfil", "Error al procesar la respuesta JSON", e);
                            Toast.makeText(Perfil.this, "Error al cargar la imagen de perfil", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Perfil", "Error al obtener los datos del usuario", error);
                        Toast.makeText(Perfil.this, "Error al conectar con el servidor", Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("X-Parse-Application-Id", APPLICATION_ID);
                headers.put("X-Parse-REST-API-Key", REST_API_KEY);
                return headers;
            }
        };

        // Agregar la solicitud a la cola
        requestQueue.add(request);
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
                profileImageView = findViewById(R.id.profileImage);
                profileImageView.setImageURI(photoURI);
                Toast.makeText(this, "Imagen capturada con éxito", Toast.LENGTH_SHORT).show();

                // Subir la imagen a Back4App
                subirImagenABack4App(photoURI);
            }
        } else if (requestCode == REQUEST_GALLERY_IMAGE && resultCode == RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            if (selectedImage != null) {
                profileImageView = findViewById(R.id.profileImage);
                profileImageView.setImageURI(selectedImage);
                Toast.makeText(this, "Imagen seleccionada con éxito", Toast.LENGTH_SHORT).show();

                // Subir la imagen a Back4App
                subirImagenABack4App(selectedImage);
            }
        }
    }

    // Método para subir la imagen a Back4App
    private void subirImagenABack4App(Uri imageUri) {
        try {
            // Convertir imagen a Base64
            InputStream inputStream = getContentResolver().openInputStream(imageUri);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

            // Comprimir la imagen para reducir su tamaño
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 70, baos);
            byte[] imageBytes = baos.toByteArray();
            String base64Image = Base64.encodeToString(imageBytes, Base64.DEFAULT);

            // Preparar los datos a enviar
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("imagenPerfil", base64Image);

            String userId = usuario.getObjectId();
            String url = API_URL + "/" + userId;

            // Si el usuario ya existe, actualizar; si no, crear
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.PUT, url, jsonBody,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.d("Perfil", "Imagen subida exitosamente");
                            Toast.makeText(Perfil.this, "Imagen de perfil actualizada", Toast.LENGTH_SHORT).show();
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // Si el usuario no existe, crear un nuevo registro
                            if (error.networkResponse != null && error.networkResponse.statusCode == 404) {
                                crearUsuarioConImagen(base64Image);
                            } else {
                                Log.e("Perfil", "Error al subir imagen", error);
                                Toast.makeText(Perfil.this, "Error al actualizar la imagen de perfil", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> headers = new HashMap<>();
                    headers.put("X-Parse-Application-Id", APPLICATION_ID);
                    headers.put("X-Parse-REST-API-Key", REST_API_KEY);
                    headers.put("Content-Type", "application/json");
                    return headers;
                }
            };

            // Agregar la solicitud a la cola
            requestQueue.add(request);

        } catch (IOException | JSONException e) {
            Log.e("Perfil", "Error al procesar la imagen", e);
            Toast.makeText(this, "Error al procesar la imagen", Toast.LENGTH_SHORT).show();
        }
    }

    // Método para crear un nuevo usuario con imagen si no existe
    private void crearUsuarioConImagen(String base64Image) {
        try {
            // Preparar los datos a enviar
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("objectId", usuario.getObjectId());
            jsonBody.put("nombre", usuario.getNombre());
            jsonBody.put("imagenPerfil", base64Image);

            // Crear un nuevo registro de usuario
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, API_URL, jsonBody,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.d("Perfil", "Usuario creado con imagen exitosamente");
                            Toast.makeText(Perfil.this, "Perfil creado con imagen", Toast.LENGTH_SHORT).show();
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("Perfil", "Error al crear usuario con imagen", error);
                            Toast.makeText(Perfil.this, "Error al guardar la imagen de perfil", Toast.LENGTH_SHORT).show();
                        }
                    }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> headers = new HashMap<>();
                    headers.put("X-Parse-Application-Id", APPLICATION_ID);
                    headers.put("X-Parse-REST-API-Key", REST_API_KEY);
                    headers.put("Content-Type", "application/json");
                    return headers;
                }
            };

            // Agregar la solicitud a la cola
            requestQueue.add(request);

        } catch (JSONException e) {
            Log.e("Perfil", "Error al crear el JSON para el nuevo usuario", e);
            Toast.makeText(this, "Error al crear el perfil", Toast.LENGTH_SHORT).show();
        }
    }
}