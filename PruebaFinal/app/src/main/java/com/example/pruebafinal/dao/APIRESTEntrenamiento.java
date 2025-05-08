package com.example.pruebafinal.dao;

import com.example.pruebafinal.modelos.Entrenamiento;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import okhttp3.*;

public class APIRESTEntrenamiento implements EntrenamientoInterface {

    private final String API_URL = "https://parseapi.back4app.com/classes/Entrenamiento";
    private final String APPLICATION_ID ="3W9GkoWV0JU3Wbo4XMHQKThkMbZreQrQTYPXAQ8x";
    private final String REST_API_KEY ="I5VOYj7ZsahchwSf9Po970WMJlGxAtqxpwBFjubu";

    @Override
    public String agregarEntrenamiento(Entrenamiento entrenamiento) {
        String objectId = "";
        Gson gson = new Gson();
        String jsonEjercicio = gson.toJson(entrenamiento);
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
                System.out.println("Entrenamiento insertado correctamente");
                Entrenamiento newEntrenamiento = gson.fromJson(response.body().string(), Entrenamiento.class);
                objectId = newEntrenamiento.getObjectId();
            }
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return objectId;
    }

    @Override
    public void eliminarEntrenamiento(String objectId) {
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
                System.out.println("Entrenamiento eliminado correctamente");
            }
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    @Override
    public ArrayList<Entrenamiento> getListaEntrenamientos() {
        ArrayList<Entrenamiento> entrenamientos = new ArrayList<Entrenamiento>();
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
                Type listType = new TypeToken<ArrayList<Entrenamiento>>() {
                            }.getType();
                entrenamientos = gson.fromJson(jonArray, listType);
            }
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }

        return entrenamientos;    }

    @Override
    public Entrenamiento getEntrenamiento(String objectId) {
        Entrenamiento entrenamiento = null;
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
                entrenamiento = gson.fromJson(responseJson, Entrenamiento.class);
            }
        } catch (IOException ex){
            System.out.println("Error: " + ex.getMessage());
        }

        return entrenamiento;    }

    @Override
    public void actualizaEntrenamientos(Entrenamiento entrenamiento) {
        Gson gson = new Gson();
        try{
            JsonObject updateEntrenamiento = new JsonObject();
            updateEntrenamiento.add("ejercicios", gson.toJsonTree(entrenamiento.getEjercicios()));
            String json = gson.toJson(updateEntrenamiento);
            RequestBody body = RequestBody.create(MediaType.parse("application/json"), json);
            Request request = new Request.Builder()
                    .url(API_URL + "/" + entrenamiento.getObjectId())
                    .addHeader("X-Parse-Application-Id", APPLICATION_ID)
                    .addHeader("X-Parse-REST-API-Key", REST_API_KEY)
                    .addHeader("Content-Type", "application/json")
                    .put(body)
                    .build();
            OkHttpClient client = new OkHttpClient();
            Response response = client.newCall(request).execute();
            if(response.isSuccessful()) {
                System.out.println("Entrenamiento actualizado correctamente");
            }
            else {
                System.out.println("Fallo en la actuaizacion. Code: " + response.code() + ". Mess: " + response.toString());
            }
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

}
