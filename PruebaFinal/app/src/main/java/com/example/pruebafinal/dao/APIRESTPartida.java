package com.example.pruebafinal.dao;

import com.example.pruebafinal.modelos.Entrenamiento;
import com.example.pruebafinal.modelos.Partida;
import com.example.pruebafinal.modelos.Ejercicio;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class APIRESTPartida implements PartidaInterface {

    private final String API_URL = "https://parseapi.back4app.com/classes/Partida";
    private final String APPLICATION_ID ="IYenyQCIaDzxFOHg1VOrfylNtpXD8Ayq4FXbjkmF";
    private final String REST_API_KEY ="dNuBWXVk7SFzoOZhJNfyV5pxOeUCDFzSuoENa396";
    
    @Override
    public void agregarPartida(Partida partida) {
        Gson gson = new Gson();
        String jsonEjercicio = gson.toJson(partida);
        try{
            Request request = new Request.Builder()
                    .url(API_URL)
                    .addHeader("X-Parse-Application-Id", APPLICATION_ID)
                    .addHeader("X-Parse-REST-API-Key", REST_API_KEY)
                    .post(RequestBody.create(MediaType.get("application/json"),jsonEjercicio))
                    .build();
            OkHttpClient client = new OkHttpClient();
            Response response = client.newCall(request).execute();
            if(response.isSuccessful()) {
                System.out.println("Partida insertada correctamente");
            }
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    @Override
    public void eliminarPartida(String objectId) {
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
                System.out.println("Partida eliminada correctamente");
            }
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    @Override
    public List<Partida> getListaPartidas() {
        ArrayList<Partida> partidas = new ArrayList<Partida>();
        try{
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(API_URL)
                    .addHeader("X-Parse-Application-Id", APPLICATION_ID)
                    .addHeader("X-Parse-REST-API-Key", REST_API_KEY)
                    .addHeader("Content-Type", "application/json")
                    .get()
                    .build();
            Response response = client.newCall(request).execute();
            if(response.isSuccessful()) {
                String responseJson = response.body().string();
                JsonObject jsonObject = new Gson().fromJson(responseJson, JsonObject.class);
                JsonArray jonArray = jsonObject.getAsJsonArray("results");
                Gson gson = new Gson();
                Type listType = new TypeToken<ArrayList<Partida>>() {
                            }.getType();
                partidas = gson.fromJson(jonArray, listType);
            }
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        
        return partidas;
    }

    @Override
    public Partida getPartida(String objectId) {
        Partida partida = null;
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
                partida = gson.fromJson(responseJson, Partida.class);
            }
        } catch (IOException ex){
            System.out.println("Error: " + ex.getMessage());
        }
        
        return partida;
    }

    @Override
    public void actualizaPartida(Partida partida) {
        Gson gson = new Gson();
        try{
            JsonObject updatePartida = new JsonObject();
            updatePartida.add("usuario", gson.toJsonTree(partida.getUsuario()));
            updatePartida.add("entrenamientos", gson.toJsonTree(partida.getEntrenamientos()));
            String json = gson.toJson(updatePartida);
            RequestBody body = RequestBody.create(MediaType.parse("application/json"), json);
            Request request = new Request.Builder()
                    .url(API_URL + "/" + partida.getObjectId())
                    .addHeader("X-Parse-Application-Id", APPLICATION_ID)
                    .addHeader("X-Parse-REST-API-Key", REST_API_KEY)
                    .addHeader("Content-Type", "application/json")
                    .put(body)
                    .build();
            OkHttpClient client = new OkHttpClient();
            Response response = client.newCall(request).execute();
            if(response.isSuccessful()) {
                System.out.println("Ejercicio actualizado correctamente");
            }
            else {
                System.out.println("Fallo en la actuaizacion. Code: " + response.code() + ". Mess: " + response.toString());
            }
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

}
