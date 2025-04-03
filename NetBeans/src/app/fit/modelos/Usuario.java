package app.fit.modelos;

public class Usuario {
    
    private String nombre;
    private String apellido;
    private String correo;
    private String contraseña;
    private int puntuacion;
    private int entrenamientosCompletados;

    public Usuario(String nombre, String apellido, String correo, String contraseña) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.contraseña = contraseña;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public int getPuntuacion() {
        return puntuacion;
    }
    
    public void aumentarPuntuacion(int puntos) {
        this.puntuacion += puntos;
    }

    public int getEntrenamientosCompletados() {
        return entrenamientosCompletados;
    }
    
    public void incrementarEntrenamientosCompletados() {
        this.entrenamientosCompletados += 1;
    }

    @Override
    public String toString() {
        return "Usuario{" + "nombre=" + nombre + ", apellido=" + apellido + ", puntuacion=" + puntuacion + ", entrenamientosCompletados=" + entrenamientosCompletados + '}';
    }
    
    
}
