
public class Tarea {
    private String nombre;
    private int puntuacion;
    private String tipo;

    public Tarea (String nombre, int puntuacion, String tipo){
        this.nombre = nombre;
        this.puntuacion = puntuacion;
        this.tipo = tipo;
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

    @Override
    public String toString() {
        return nombre + " (" + tipo + ") - Puntos: " + puntuacion;
    }
}
