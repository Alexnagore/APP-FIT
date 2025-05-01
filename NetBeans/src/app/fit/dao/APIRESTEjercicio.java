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



public class APIRESTEjercicio implements EjercicioInterface {

    private final String API_URL = "https://parseapi.back4app.com/classes/Ejercicio";
    private final String APPLICATION_ID ="IYenyQCIaDzxFOHg1VOrfylNtpXD8Ayq4FXbjkmF";
    private final String REST_API_KEY ="dNuBWXVk7SFzoOZhJNfyV5pxOeUCDFzSuoENa396";

    public APIRESTEjercicio() {
    }

    
    @Override
    public String agregarEjercicio(Ejercicio ejercicio) {
        String objectId = "";
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
                System.out.println("Ejercicio insertado correctamente");
                Ejercicio newEjercicio = gson.fromJson(response.body().string(), Ejercicio.class);
                objectId = newEjercicio.getObjectId();
            }
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return objectId;
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
                System.out.println("Ejercicio eliminado correctamente");
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
    public void actualizaEjercicio(Ejercicio ejercicio) {
        Gson gson = new Gson();
        try{
            JsonObject updateEjercicio = new JsonObject();
            updateEjercicio.addProperty("nombre", ejercicio.getNombre());
            updateEjercicio.add("puntoInicial", gson.toJsonTree(ejercicio.getPuntoInicial()));
            updateEjercicio.add("puntoFinal", gson.toJsonTree(ejercicio.getPuntoFinal()));
            updateEjercicio.addProperty("numRepeticiones", ejercicio.getNumRepeticiones());
            updateEjercicio.addProperty("tiempo", ejercicio.getTiempo());
            updateEjercicio.addProperty("puntuacion", ejercicio.getPuntuacion());
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
            else {
                System.out.println("Fallo en la actuaizacion. Code: " + response.code() + ". Mess: " + response.toString());
            }
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
    
}
