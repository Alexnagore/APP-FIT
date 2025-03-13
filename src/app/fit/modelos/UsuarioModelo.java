package app.fit.modelos;

public class UsuarioModelo {
    
    private String nombre;
    private String apellido;
    private String correo;
    private String contraseña;
    private int puntuacion;
    private int entrenamientosCompletados;

    public UsuarioModelo(String nombre, String apellido, String correo, String contraseña) {
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
    
    
}
