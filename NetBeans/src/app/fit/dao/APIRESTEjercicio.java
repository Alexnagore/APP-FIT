package app.fit.dao;

import app.fit.modelos.Ejercicio;
import java.util.List;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import okhttp3.*;



public class APIRESTEjercicio implements EjercicioInterface {

    private final String API_URL = "https://parseapi.back4app.com/classes/Ejercicio";
    private final String APPLICATION_ID ="3W9GkoWV0JU3Wbo4XMHQKThkMbZreQrQTYPXAQ8x";
    private final String REST_API_KEY ="I5VOYj7ZsahchwSf9Po970WMJlGxAtqxpwBFjubu";

    @Override
    public void agregarEjercicio(Ejercicio ejercicio) {
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
                Ejercicio newEjercicio = gson.fromJson(response.body().string(), Ejercicio.class);
                objectId = newEjercicio.getObjectId();
            }
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    @Override
    public void eliminarEjercicio(Ejercicio ejercicio) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Ejercicio> getListaEjercicios() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Ejercicio getEjercicio(Ejercicio ejercicio) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setListaEjercicios(List<Ejercicio> listaEjercicios) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void actualizaEjercicios(Ejercicio ejercicio) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
