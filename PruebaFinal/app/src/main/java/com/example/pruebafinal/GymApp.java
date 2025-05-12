package com.example.pruebafinal;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.pruebafinal.business.EjercicioManager;
import com.example.pruebafinal.business.EntrenamientoManager;
import com.example.pruebafinal.business.PartidaManager;
import com.example.pruebafinal.business.UsuarioManager;
import com.example.pruebafinal.dao.APIRESTEjercicio;
import com.example.pruebafinal.dao.APIRESTEntrenamiento;
import com.example.pruebafinal.dao.APIRESTPartida;
import com.example.pruebafinal.dao.APIRESTUsuario;
import com.example.pruebafinal.dao.EjercicioInterface;
import com.example.pruebafinal.dao.EntrenamientoInterface;
import com.example.pruebafinal.dao.PartidaInterface;
import com.example.pruebafinal.dao.UsuarioInterface;
import com.example.pruebafinal.modelos.Ejercicio;
import com.example.pruebafinal.modelos.Entrenamiento;
import com.example.pruebafinal.modelos.Partida;
import com.example.pruebafinal.modelos.SesionUsuario;
import com.example.pruebafinal.modelos.Usuario;
import com.example.pruebafinal.views.DrawingActivity;
import com.example.pruebafinal.views.MenuInicial;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.Priority;

/**
 * Clase Application personalizada para gestionar la inyección de dependencias
 * y proporcionar acceso global a los servicios de la aplicación
 */
public class GymApp extends Application {

    // Instancias de los repositorios
    private EjercicioInterface ejercicioRepository;
    private EntrenamientoInterface entrenamientoRepository;
    private PartidaInterface partidaRepository;
    private UsuarioInterface usuarioRepository;

    // Instancias de los managers (capa de negocio)
    private EjercicioManager ejercicioManager;
    private EntrenamientoManager entrenamientoManager;
    private PartidaManager partidaManager;
    private UsuarioManager usuarioManager;

    // Instancia singleton
    private static GymApp instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        // Inicializar repositorios
        initRepositories();

        // Inicializar managers con sus respectivos repositorios
        initManagers();
    }

    /**
     * Inicializa todos los repositorios de datos
     */
    private void initRepositories() {
        ejercicioRepository = new APIRESTEjercicio();
        entrenamientoRepository = new APIRESTEntrenamiento();
        partidaRepository = new APIRESTPartida();
        usuarioRepository = new APIRESTUsuario();
    }

    /**
     * Inicializa los managers con sus dependencias inyectadas
     */
    private void initManagers() {
        ejercicioManager = new EjercicioManager(ejercicioRepository);
        entrenamientoManager = new EntrenamientoManager(entrenamientoRepository);
        partidaManager = new PartidaManager(partidaRepository);
        usuarioManager = new UsuarioManager(usuarioRepository);
    }

    /**
     * Obtener la instancia singleton de la aplicación
     * @return instancia de GymApp
     */
    public static GymApp getInstance() {
        return instance;
    }

    // Getters para los managers
    public EjercicioManager getEjercicioManager() {
        return ejercicioManager;
    }

    public EntrenamientoManager getEntrenamientoManager() {
        return entrenamientoManager;
    }

    public PartidaManager getPartidaManager() {
        return partidaManager;
    }

    public UsuarioManager getUsuarioManager() {
        return usuarioManager;
    }

    public static class ResumenActivity extends AppCompatActivity {

        private Usuario usuario;
        private UsuarioManager usuarioManager;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_resumen);
            MediaPlayer mediaPlayer = MediaPlayer.create(ResumenActivity.this, R.raw.finish_sound);
            mediaPlayer.setOnCompletionListener(MediaPlayer::release);
            mediaPlayer.start();
            // Obtener el manager de usuarios desde GymApp
            usuarioManager = ((GymApp) getApplication()).getUsuarioManager();

            TextView puntuacionTextView = findViewById(R.id.puntuacionTextView);
            Button volverInicioBtn = findViewById(R.id.volverInicioBtn);

            int puntuacionTotal = getIntent().getIntExtra("puntuacionTotal", 0);
            usuario = SesionUsuario.getUsuario();

            puntuacionTextView.setText("Puntuación total: " + puntuacionTotal);
            Log.d("usuario", "Usuario: " + usuario);
            if (usuario != null) {
                usuario.setPuntuacion(usuario.getPuntuacion() + puntuacionTotal);
                usuario.setEntrenamientosCompletados(usuario.getEntrenamientosCompletados() + 1);
                Log.d("usuario", "Usuario actualizado: " + usuario);

                // Actualizar el usuario usando UsuarioManager
                new Thread(() -> usuarioManager.actualizarUsuario(usuario)).start();
            }

            volverInicioBtn.setOnClickListener(v -> {
                Intent intent = new Intent(this, DrawingActivity.class);
                intent.putExtra("usuario", usuario); // Pasa el objeto usuario
                startActivity(intent);
                finish();
            });
        }
    }

    public static class TablaPuntuaciones extends AppCompatActivity {

        private List<String> texts;
        private ArrayAdapter<String> itemsAdapter;
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
            setTitle("Tabla de Puntuaciones");
            setContentView(R.layout.activity_tabla_puntuaciones);

            // Obtener el manager de usuarios desde GymApp
            usuarioManager = ((GymApp) getApplication()).getUsuarioManager();

            // Obtener la lista de usuarios usando UsuarioManager
            usuarioManager.getListaUsuarios(usuarios -> {
                runOnUiThread(() -> {
                    texts = usuarios.stream()
                            .sorted((u1, u2) -> Integer.compare(u2.getPuntuacion(), u1.getPuntuacion())) // Ordenar por puntuación descendente
                            .map(usuario -> usuario.getNombre() + ": " + usuario.getPuntuacion())
                            .collect(Collectors.toList());
                    System.out.println("Lista de usuarios: " + texts);
                    ListView listView = findViewById(R.id.list);
                    itemsAdapter = new ArrayAdapter<String>(this, R.layout.row_layout, R.id.listText, texts) {
                        @Override
                        public View getView(int position, View convertView, ViewGroup parent) {
                            View view = super.getView(position, convertView, parent);
                            if (position == 0) {
                                view.setBackgroundColor(Color.parseColor("#FFD700")); // Oro
                            } else if (position == 1) {
                                view.setBackgroundColor(Color.parseColor("#C0C0C0")); // Plata
                            } else if (position == 2) {
                                view.setBackgroundColor(Color.parseColor("#CD7F32")); // Bronce
                            } else {
                                view.setBackgroundColor(Color.WHITE); // Blanco
                            }
                            return view;
                        }
                    };

                    listView.setAdapter(itemsAdapter);
                });
            });
        }
    }

    public static class TrainingDetailsActivity extends AppCompatActivity {
        private boolean wasInside = false;

        private EntrenamientoManager entrenamientoManager;

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
            setContentView(R.layout.activity_training_details);

            // Obtener el manager de entrenamientos desde GymApp
            entrenamientoManager = ((GymApp) getApplication()).getEntrenamientoManager();

            TextView titleText = findViewById(R.id.titleText);
            TextView trainingProgram = findViewById(R.id.trainingProgram);
            TextView scoreText = findViewById(R.id.scoreText);
            TextView exercisesTextView = findViewById(R.id.exercisesTextView);
            Button startButton = findViewById(R.id.startButton);

            String objectId = getIntent().getStringExtra("objectId"); // Get the objectId

            if (objectId != null) {
                entrenamientoManager.getEntrenamiento(objectId, entrenamiento -> {
                    if (entrenamiento != null) {
                        runOnUiThread(() -> {
                            trainingProgram.setText(entrenamiento.getNombre());
                            scoreText.setText("Puntuación: " + entrenamiento.getPuntuacion());

                            List<Ejercicio> ejercicios = entrenamiento.getEjercicios();
                            StringBuilder ejerciciosText = new StringBuilder();
                            for (Ejercicio ejercicio : ejercicios) {
                                ejerciciosText.append("• ").append(ejercicio.getNombre()).append("\n");
                            }
                            exercisesTextView.setText(ejerciciosText.toString());
                        });
                    }
                });
            }
            AtomicReference<FusedLocationProviderClient> fusedLocationClient = new AtomicReference<>(LocationServices.getFusedLocationProviderClient(this));

            if (checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                LocationRequest locationRequest = LocationRequest.create();
                locationRequest.setPriority(Priority.PRIORITY_HIGH_ACCURACY);
                locationRequest.setInterval(1000); // Cada segundo

                LocationCallback locationCallback = new LocationCallback() {
                    @Override
                    public void onLocationResult(LocationResult locationResult) {
                        if (locationResult == null) return;

                        Location location = locationResult.getLastLocation();
                        if (location != null) {
                            double userLat = location.getLatitude();
                            double userLon = location.getLongitude();

                            // Coordenadas de los vértices del rectángulo inclinado
                            double[][] aulario = {
                                    {42.801124, -1.635732}, // Vértice 1
                                    {42.800880, -1.635496}, // Vértice 2
                                    {42.799763, -1.637588}, // Vértice 3
                                    {42.799994, -1.637819}  // Vértice 4
                            };

                            double[][] biblioteca = {
                                    {42.800107, -1.636032},
                                    {42.799194, -1.635118},
                                    {42.798986, -1.635504},
                                    {42.799905, -1.636421}
                            };

                            // Método para verificar si el punto está dentro del polígono
                            boolean isInside = puntoEnPoligono(userLat, userLon, aulario);

                            runOnUiThread(() -> {
                                ImageView imageView = findViewById(R.id.imageView);
                                TextView descriptionText = findViewById(R.id.descriptionText);
                                Button startButton = findViewById(R.id.startButton);

                                if (isInside) {
                                    if (!wasInside) {
                                        // Usuario ha entrado
                                        descriptionText.setText("Estas en el aulario");
                                        descriptionText.setTextColor(Color.GREEN);
                                        imageView.setImageResource(R.drawable.aulario);
                                        startButton.setEnabled(true);
                                    }
                                } else {
                                    if (wasInside) {
                                        // Usuario ha salido
                                        descriptionText.setText("No estas en el aulario");
                                        descriptionText.setTextColor(Color.RED);
                                        imageView.setImageResource(R.drawable.aulario_error);
                                        startButton.setEnabled(false);
                                        Toast.makeText(TrainingDetailsActivity.this, "Error: Debes estar dentro del aulario para comenzar.", Toast.LENGTH_SHORT).show();
                                    } else {
                                        // Usuario sigue fuera
                                        descriptionText.setText("No estas en el aulario");
                                        descriptionText.setTextColor(Color.RED);
                                        imageView.setImageResource(R.drawable.aulario_error);
                                        startButton.setEnabled(false);
                                    }
                                }
                                wasInside = isInside;
                            });
                        }
                    }
                };

                fusedLocationClient.get().requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper());
            } else {
                Toast.makeText(this, "Error: No se tienen permisos de ubicación.", Toast.LENGTH_SHORT).show();
            }

            startButton.setOnClickListener(v -> {
                fusedLocationClient.set(LocationServices.getFusedLocationProviderClient(this));

                if (checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

                    LocationRequest locationRequest = LocationRequest.create();
                    locationRequest.setPriority(Priority.PRIORITY_HIGH_ACCURACY);
                    locationRequest.setInterval(1000);

                    LocationCallback locationCallback = new LocationCallback() {
                        @Override
                        public void onLocationResult(LocationResult locationResult) {
                            if (locationResult == null) return;

                            Location location = locationResult.getLastLocation();
                            if (location != null) {
                                double userLat = location.getLatitude();
                                double userLon = location.getLongitude();


                                // Coordenadas de los vértices del rectángulo inclinado
                                double[][] aulario = {
                                        {42.801124, -1.635732}, // Vértice 1
                                        {42.800880, -1.635496}, // Vértice 2
                                        {42.799763, -1.637588}, // Vértice 3
                                        {42.799994, -1.637819}  // Vértice 4
                                };

                                double[][] biblioteca = {
                                        {42.800107, -1.636032},
                                        {42.799194, -1.635118},
                                        {42.798986, -1.635504},
                                        {42.799905, -1.636421}
                                };

                                // Método para verificar si el punto está dentro del polígono
                                boolean isInside = puntoEnPoligono(userLat, userLon, aulario);

                                if (isInside) {

                                    entrenamientoManager.getEntrenamiento(objectId, entrenamiento -> {
                                        if (entrenamiento != null) {
                                            runOnUiThread(() -> {
                                                Intent intent = new Intent(TrainingDetailsActivity.this, TrainingExecutionActivity.class);
                                                intent.putExtra("entrenamiento", entrenamiento);
                                                startActivity(intent);
                                            });
                                        }
                                    });

                                } else {
                                    runOnUiThread(() -> Toast.makeText(TrainingDetailsActivity.this, "Error: Debes estar dentro del aulario para comenzar.", Toast.LENGTH_SHORT).show());
                                }

                                fusedLocationClient.get().removeLocationUpdates(this); // ✅ Parar actualización tras obtener ubicación
                            } else {
                                runOnUiThread(() -> Toast.makeText(TrainingDetailsActivity.this, "Error: No se pudo obtener la ubicación actual.", Toast.LENGTH_SHORT).show());
                            }
                        }
                    };

                    fusedLocationClient.get().requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper());

                } else {
                    ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 1);
                }
            });
        }

        private boolean puntoEnPoligono(double lat, double lon, double[][] vertices) {
            boolean dentro = false;
            int n = vertices.length;

            for (int i = 0, j = n - 1; i < n; j = i++) {
                double lat1 = vertices[i][0];
                double lon1 = vertices[i][1];
                double lat2 = vertices[j][0];
                double lon2 = vertices[j][1];


                if ((lon1 > lon) != (lon2 > lon) && (lat < (lat2 - lat1) * (lon - lon1) / (lon2 - lon1) + lat1)) {
                    dentro = !dentro;
                }
            }

            return dentro;
        }

    }

    public static class TrainingExecutionActivity extends AppCompatActivity implements SensorEventListener {

        private List<Ejercicio> ejercicios;
        private int ejercicioActualIndex = 0;
        private Entrenamiento entrenamiento;

        private TextView nombreEjercicio;
        private TextView repeticionesTiempo;
        private Button siguienteBtn;
        private Button finalizarBtn;

        private SensorManager sensorManager;
        private Sensor accelerometer;

        private static final float STEP_THRESHOLD = 15f; // Umbral para detectar paso
        private int pasosDetectados = 0;
        private float distanciaObjetivo = 100.0f; // En metros
        private float distanciaRecorrida = 0.0f;
        private boolean carreraActiva = false;

        private CountDownTimer countDownTimer;

        // Managers
        private EntrenamientoManager entrenamientoManager;
        private EjercicioManager ejercicioManager;
        private PartidaManager partidaManager;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_training_execution);

            // Obtener managers desde GymApp
            entrenamientoManager = getInstance().getEntrenamientoManager();
            ejercicioManager = getInstance().getEjercicioManager();
            partidaManager = getInstance().getPartidaManager();

            // Inicializar vistas
            nombreEjercicio = findViewById(R.id.nombreEjercicio);
            repeticionesTiempo = findViewById(R.id.repeticionesTiempo);
            siguienteBtn = findViewById(R.id.siguienteBtn);
            finalizarBtn = findViewById(R.id.finalizarBtn);

            // Obtener el entrenamiento del intent
            entrenamiento = (Entrenamiento) getIntent().getSerializableExtra("entrenamiento");
            if (entrenamiento != null) {
                ejercicios = entrenamiento.getEjercicios();
                mostrarEjercicioActual();
            } else {
                // Si el entrenamiento es null, intentar cargarlo usando su ID
                String entrenamientoId = getIntent().getStringExtra("entrenamientoId");
                if (entrenamientoId != null) {
                    cargarEntrenamientoPorId(entrenamientoId);
                } else {
                    Toast.makeText(this, "Error: No se proporcionó un entrenamiento válido", Toast.LENGTH_SHORT).show();
                    finish();
                    return;
                }
            }

            // Configurar listeners de botones
            siguienteBtn.setOnClickListener(v -> avanzarAEjercicioSiguiente());

            finalizarBtn.setOnClickListener(v -> {
                guardarPartida();
                Toast.makeText(this, "Entrenamiento finalizado manualmente", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, MenuInicial.class);
                intent.putExtra("usuario", SesionUsuario.getUsuario());
                startActivity(intent);
                finish();
            });
        }

        /**
         * Carga un entrenamiento por su ID usando el manager
         */
        private void cargarEntrenamientoPorId(String entrenamientoId) {
            entrenamientoManager.getEntrenamiento(entrenamientoId, entrenamientoRecibido -> {
                runOnUiThread(() -> {
                    if (entrenamientoRecibido != null) {
                        entrenamiento = entrenamientoRecibido;
                        ejercicios = entrenamiento.getEjercicios();
                        mostrarEjercicioActual();
                    } else {
                        Toast.makeText(this, "Error: No se pudo cargar el entrenamiento", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
            });
        }

        private void mostrarEjercicioActual() {
            if (countDownTimer != null) {
                countDownTimer.cancel();
            }

            detenerAcelerometro(); // Por si viene de una carrera anterior

            pasosDetectados = 0;
            distanciaRecorrida = 0;

            Ejercicio e = ejercicios.get(ejercicioActualIndex);
            nombreEjercicio.setText(e.getNombre());

            if (e.getNombre().equalsIgnoreCase("Carrera")) {
                carreraActiva = true;
                distanciaObjetivo = 100.0f; // Puedes personalizarlo con datos del ejercicio
                iniciarAcelerometro();
                repeticionesTiempo.setText("Distancia: 0m / " + (int) distanciaObjetivo + "m");

                siguienteBtn.setEnabled(false);
            }
            else if (e.getTiempo() > 0) {
                repeticionesTiempo.setText("Duración: " + e.getTiempo() + "s");

                countDownTimer = new CountDownTimer(e.getTiempo() * 1000L, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        MediaPlayer mediaPlayer = MediaPlayer.create(TrainingExecutionActivity.this, R.raw.tick_sound);
                        mediaPlayer.setOnCompletionListener(MediaPlayer::release);
                        mediaPlayer.start();
                        repeticionesTiempo.setText("Duración restante: " + millisUntilFinished / 1000 + "s");
                    }

                    @Override
                    public void onFinish() {
                        avanzarAEjercicioSiguiente();
                    }
                }.start();

                siguienteBtn.setEnabled(false);
            } else {
                repeticionesTiempo.setText("Repeticiones: " + e.getNumRepeticiones());
                siguienteBtn.setEnabled(true);
            }
        }

        private void avanzarAEjercicioSiguiente() {
            if (ejercicioActualIndex < ejercicios.size() - 1) {
                ejercicioActualIndex++;
                mostrarEjercicioActual();
            } else {
                guardarPartida();
                Toast.makeText(this, "¡Entrenamiento finalizado!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, ResumenActivity.class);
                intent.putExtra("puntuacionTotal", calcularPuntuacionTotal());
                startActivity(intent);
                finish();
            }
        }

        /**
         * Guarda la partida usando el manager
         */
        private void guardarPartida() {
            if (entrenamiento != null && SesionUsuario.getUsuario() != null) {
                Partida partida = new Partida();
                partida.setUsuario(SesionUsuario.getUsuario());

                // Usar el manager para guardar la partida
                partidaManager.agregarPartida(partida);
            }
        }

        private void iniciarAcelerometro() {
            sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
            accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            if (accelerometer != null) {
                sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
            }
        }

        private void detenerAcelerometro() {
            if (sensorManager != null) {
                sensorManager.unregisterListener(this);
            }
        }

        private int calcularPuntuacionTotal() {
            int total = 0;
            for (Ejercicio e : ejercicios) {
                total += e.getPuntuacion();
            }
            return total;
        }

        @Override
        protected void onDestroy() {
            super.onDestroy();
            detenerAcelerometro();
            if (countDownTimer != null) {
                countDownTimer.cancel();
            }
        }

        @Override
        public void onSensorChanged(SensorEvent event) {
            if (!carreraActiva || event.sensor.getType() != Sensor.TYPE_ACCELEROMETER) return;

            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];
            float magnitude = (float) Math.sqrt(x * x + y * y + z * z);

            if (magnitude > STEP_THRESHOLD) {
                pasosDetectados++;
                distanciaRecorrida = pasosDetectados * 0.6f;

                repeticionesTiempo.setText("Distancia: " + (int) distanciaRecorrida + "m / " + (int) distanciaObjetivo + "m");

                if (distanciaRecorrida >= distanciaObjetivo) {
                    carreraActiva = false;
                    detenerAcelerometro();
                    Toast.makeText(this, "¡Carrera completada!", Toast.LENGTH_SHORT).show();
                    avanzarAEjercicioSiguiente();
                }
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {
            // No requiere implementación
        }
    }
}