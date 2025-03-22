package librerias.excepcionesDeUsuario;

import java.io.Serializable;

public class Tarea implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nombre;
    private int puntuacion;
    private String tipo;
    private String codigoAsignatura;

    public Tarea(String nombre, int puntuacion, String tipo, String codigoAsignatura) {
        this.nombre = nombre;
        this.puntuacion = puntuacion;
        this.tipo = tipo;
        this.codigoAsignatura = codigoAsignatura;
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

    public String getCodigoAsignatura() {
        return codigoAsignatura;
    }

    @Override
    public String toString() {
        return "Tarea{" +
                "nombre='" + nombre + '\'' +
                ", puntuacion=" + puntuacion +
                ", tipo='" + tipo + '\'' +
                ", codigoAsignatura='" + codigoAsignatura + '\'' +
                '}';
    }

}