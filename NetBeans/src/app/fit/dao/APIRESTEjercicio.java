package app.fit.dao;

import app.fit.modelos.Ejercicio;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import okhttp3.*;
import okio.ByteString;



public class APIRESTEjercicio implements EjercicioInterface {

    private final String API_URL = "https://parseapi.back4app.com/classes/Ejercicio";
    private final String APPLICATION_ID ="3W9GkoWV0JU3Wbo4XMHQKThkMbZreQrQTYPXAQ8x";
    private final String REST_API_KEY ="I5VOYj7ZsahchwSf9Po970WMJlGxAtqxpwBFjubu";

    @Override
    public void agregarEjercicio(Ejercicio ejercicio) {
        Gson gson = new Gson();
        String jsonEjercicio = gson.toJson(ejercicio);
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
                System.out.println("Usuario insertado correctamente");
            }
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    @Override
    public void eliminarEjercicio(String objectId) {
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

    @Override
    public ArrayList<Ejercicio> getListaEjercicios() {
        ArrayList<Ejercicio> ejercicios = new ArrayList<Ejercicio>();
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
                Type listType = new TypeToken<ArrayList<Ejercicio>>() {
                            }.getType();
                ejercicios = gson.fromJson(jonArray, listType);
            }
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        
        return ejercicios;
    }

    @Override
    public Ejercicio getEjercicio(String objectId) {
        Ejercicio ejercicio = null;
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
                ejercicio = gson.fromJson(responseJson, Ejercicio.class);
            }
        } catch (IOException ex){
            System.out.println("Error: " + ex.getMessage());
        }
        
        return ejercicio;
    }

    @Override
    public void actualizaEjercicios(Ejercicio ejercicio) {
        Gson gson = new Gson();
        try{
            JsonObject updateEjercicio = new JsonObject();
            updateEjercicio.addProperty("descripcion", ejercicio.getDescripcion());
            updateEjercicio.addProperty("puntuacion", ejercicio.getPuntuacion());
            updateEjercicio.addProperty("tiempo", ejercicio.getTiempo());
            updateEjercicio.addProperty("puntoInicial", ejercicio.getPuntoInicial().toString());
            updateEjercicio.addProperty("puntoFinal", ejercicio.getPuntoFinal().toString());
            updateEjercicio.addProperty("numRepeticiones", ejercicio.getNumRepeticiones());
            String json = gson.toJson(updateEjercicio);
            RequestBody body = RequestBody.create(MediaType.parse("application/json"), json);
            Request request = new Request.Builder()
                    .url(API_URL + "/" + ejercicio.getObjectId())
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
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
    
}
