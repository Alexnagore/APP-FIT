package com.example.pruebafinal.dao;

import android.util.Log;

import com.example.pruebafinal.modelos.Ejercicio;
import com.example.pruebafinal.modelos.Usuario;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class APIRESTUsuario implements UsuarioInterface {

    private final String API_URL = "https://parseapi.back4app.com/classes/Usuario";
    private final String APPLICATION_ID ="IYenyQCIaDzxFOHg1VOrfylNtpXD8Ayq4FXbjkmF";
    private final String REST_API_KEY ="dNuBWXVk7SFzoOZhJNfyV5pxOeUCDFzSuoENa396";

    @Override
    public void agregarUsuario(Usuario usuario) {
        Gson gson = new Gson();
        String jsonUsuario = gson.toJson(usuario);

        Request request = new Request.Builder()
                .url(API_URL)
                .addHeader("X-Parse-Application-Id", APPLICATION_ID)
                .addHeader("X-Parse-REST-API-Key", REST_API_KEY)
                .addHeader("Content-Type", "application/json")
                .post(RequestBody.create(MediaType.get("application/json"), jsonUsuario))
                .build();

        OkHttpClient client = new OkHttpClient();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("API", "Error al insertar usuario: " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    Log.i("API", "Usuario insertado correctamente");
                } else {
                    Log.w("API", "Fallo al insertar usuario: " + response.message());
                }
            }
        });
    }


    @Override
    public void eliminarUsuario(String objectId) {
        try{
            Request request = new Request.Builder()
                    .url(API_URL + "/" + objectId)
                    .addHeader("X-Parse-Application-Id", APPLICATION_ID)
                    .addHeader("X-Parse-REST-API-Key", REST_API_KEY)
                    .delete()
                    .build();
            OkHttpClient client = new OkHttpClient();
            Response response = client.newCall(request).execute();
            if(response.isSuccessful()) {
                System.out.println("Usuario eliminado correctamente");
            }
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    public void getListaUsuarios(Consumer<List<Usuario>> callback) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(API_URL)
                .addHeader("X-Parse-Application-Id", APPLICATION_ID)
                .addHeader("X-Parse-REST-API-Key", REST_API_KEY)
                .addHeader("Content-Type", "application/json")
                .get()
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                List<Usuario> usuarios = new ArrayList<>();
                if (response.isSuccessful() && response.body() != null) {
                    String json = response.body().string();
                    JsonObject jsonObject = new Gson().fromJson(json, JsonObject.class);
                    JsonArray jsonArray = jsonObject.getAsJsonArray("results");
                    Type listType = new TypeToken<ArrayList<Usuario>>() {}.getType();
                    usuarios = new Gson().fromJson(jsonArray, listType);
                }
                callback.accept(usuarios);
            }
        });
    }


    @Override
    public Usuario getUsuario(String objectId) {
        Usuario usuario = null;
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(API_URL + "/" + objectId)
                    .addHeader("X-Parse-Application-Id", APPLICATION_ID)
                    .addHeader("X-Parse-REST-API-Key", REST_API_KEY)
                    .addHeader("Content-Type", "application/json")
                    .get()
                    .build();
            Response response = client.newCall(request).execute();
            if(response.isSuccessful()) {
                String responseJson = response.body().string();
                Gson gson = new Gson();
                usuario = gson.fromJson(responseJson, Usuario.class);
            }
        } catch (IOException ex){
            System.out.println("Error: " + ex.getMessage());
        }

        return usuario;
    }

    @Override
    public void actualizaUsuario(Usuario usuario) {
        Gson gson = new Gson();
        try{
            JsonObject updateUsuario = new JsonObject();
            updateUsuario.addProperty("nombre", usuario.getNombre());
            updateUsuario.addProperty("puntuacion", usuario.getPuntuacion());
            updateUsuario.addProperty("entrenamientosCompletados", usuario.getEntrenamientosCompletados());
            String json = gson.toJson(updateUsuario);
            RequestBody body = RequestBody.create(MediaType.parse("application/json"), json);
            Request request = new Request.Builder()
                    .url(API_URL + "/" + usuario.getObjectId())
                    .addHeader("X-Parse-Application-Id", APPLICATION_ID)
                    .addHeader("X-Parse-REST-API-Key", REST_API_KEY)
                    .addHeader("Content-Type", "application/json")
                    .put(body)
                    .build();
            OkHttpClient client = new OkHttpClient();
            Response response = client.newCall(request).execute();
            if(response.isSuccessful()) {
                System.out.println("Usuario actualizado correctamente");
            }
            else {
                System.out.println("Fallo en la actuaizacion. Code: " + response.code() + ". Mess: " + response.toString());
            }
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

}
