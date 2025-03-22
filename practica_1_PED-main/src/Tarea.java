import java.io.Serializable;

public class Tarea implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nombre;
    private int puntuacion;
    private String tipo;
    private String codigoAsignatura; // Add this field

    public Tarea(String nombre, int puntuacion, String tipo, String codigoAsignatura) {
        this.nombre = nombre;
        this.puntuacion = puntuacion;
        this.tipo = tipo;
        this.codigoAsignatura = codigoAsignatura; // Initialize the field
    }

    public String getNombre() {
        return nombre;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getCodigoAsignatura() {
        return codigoAsignatura; // Return the code of the associated Asignatura
    }

    @Override
    public String toString() {
        return nombre + " (" + tipo + ") - Puntos: " + puntuacion;
    }
}