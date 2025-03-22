package librerias.excepcionesDeUsuario;

import java.io.Serializable;

/**
 * Clase que representa una tarea de una asignatura.
 */
public class Tarea implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nombre;
    private int puntuacion;
    private String tipo;
    private String codigoAsignatura;

    /**
     * Constructor que inicializa una tarea con su nombre, puntuación, tipo y código de asignatura.
     *
     * @param nombre el nombre de la tarea
     * @param puntuacion la puntuación de la tarea
     * @param tipo el tipo de tarea (practica, parcial, Examen Final)
     * @param codigoAsignatura el código de la asignatura a la que pertenece la tarea
     */
    public Tarea(String nombre, int puntuacion, String tipo, String codigoAsignatura) {
        this.nombre = nombre;
        this.puntuacion = puntuacion;
        this.tipo = tipo;
        this.codigoAsignatura = codigoAsignatura;
    }

    /**
     * Obtiene el nombre de la tarea.
     *
     * @return el nombre de la tarea
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene la puntuación de la tarea.
     *
     * @return la puntuación de la tarea
     */
    public int getPuntuacion() {
        return puntuacion;
    }

    /**
     * Obtiene el tipo de la tarea.
     *
     * @return el tipo de la tarea
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Obtiene el código de la asignatura a la que pertenece la tarea.
     *
     * @return el código de la asignatura
     */
    public String getCodigoAsignatura() {
        return codigoAsignatura;
    }

    /**
     * Devuelve una representación en cadena de la tarea.
     *
     * @return una cadena con el nombre, puntuación, tipo y código de asignatura de la tarea
     */
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