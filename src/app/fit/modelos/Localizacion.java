package app.fit.modelos;

public class Localizacion {
    private double latitud;
    private double longitud;
    private double altitud;

    public Localizacion(double latitud, double longitud, double altitud) {
        this.latitud = latitud;
        this.longitud = longitud;
        this.altitud = altitud;
    }

    public double getLatitud() {
        return latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public double getAltitud() {
        return altitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public void setAltitud(double altitud) {
        this.altitud = altitud;
    }
    
}
