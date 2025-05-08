package com.example.pruebafinal.modelos;

public class SesionUsuario {
    private static Usuario usuarioActual;

    public static Usuario getUsuario() {
        return usuarioActual;
    }

    public static void setUsuario(Usuario usuario) {
        usuarioActual = usuario;
    }

    public static void limpiar() {
        usuarioActual = null;
    }
}
