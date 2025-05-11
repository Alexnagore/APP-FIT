package com.example.pruebafinal.views;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pruebafinal.GymApp;
import com.example.pruebafinal.R;
import com.example.pruebafinal.business.EjercicioManager;

import org.osmdroid.config.Configuration;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.Polygon;

import java.util.Arrays;

public class EjercicioDetailsActivity extends AppCompatActivity {

    private EjercicioManager ejercicioManager;
    private MapView mapView;
    private Marker userMarker;

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
        setContentView(R.layout.activity_ejercicio_details);

        // Obtener el manager de ejercicios desde GymApp
        ejercicioManager = ((GymApp) getApplication()).getEjercicioManager();

        TextView descriptionText = findViewById(R.id.descriptionText);
        TextView scoreText = findViewById(R.id.scoreText);
        TextView timeText = findViewById(R.id.timeText);

        mapView = findViewById(R.id.mapView);
        Configuration.getInstance().load(getApplicationContext(), PreferenceManager.getDefaultSharedPreferences(this));
        mapView.setMultiTouchControls(true);
        mapView.getController().setZoom(19.0);
        GeoPoint aulario = new GeoPoint(42.80044188568163, -1.6366760290683449);
        mapView.getController().setCenter(aulario);

        Polygon polygon = new Polygon();
        polygon.setFillColor(0x12121212); // Color de relleno con transparencia
        polygon.setStrokeColor(0xFF0000FF); // Color del borde
        polygon.setStrokeWidth(5.0f); // Ancho del borde

        polygon.setPoints(Arrays.asList(
                new GeoPoint(42.799988130324, -1.6378452917701332),
                new GeoPoint(42.801121272728125, -1.6357500811484318),
                new GeoPoint(42.800870842604645, -1.6354966282506453),
                new GeoPoint(42.79974513428342, -1.637595218244317), // Aulario
                new GeoPoint(42.799988130324, -1.6378452917701332) // Cerrar el polígono
        ));
        polygon.setFillColor(0x801212FF); // Color de relleno azul con transparencia
        mapView.getOverlays().add(polygon);

        Marker marker = new Marker(mapView);
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        marker.setPosition(aulario);
        marker.setTitle("Aulario");
        marker.setIcon(new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(
                ((BitmapDrawable) getResources().getDrawable(R.drawable.marcador, null)).getBitmap(),
                70, // Ancho deseado
                70, // Alto deseado
                true)));
        mapView.getOverlays().add(marker);

        String objectId = getIntent().getStringExtra("objectId");

        if (objectId != null) {
            // Obtener el ejercicio usando EjercicioManager
            ejercicioManager.getEjercicio(objectId, ejercicio -> {
                if (ejercicio != null) {
                    runOnUiThread(() -> {
                        descriptionText.setText("Descripción: " + ejercicio.getNombre());
                        scoreText.setText("Puntuación: " + ejercicio.getPuntuacion());
                        timeText.setText("Tiempo: " + ejercicio.getTiempo() + " segundos");
                    });
                }
            });
        }
    }
}