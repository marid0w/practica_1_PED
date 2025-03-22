package librerias.excepcionesDeUsuario;

import librerias.estructurasDeDatos.lineales.NodoLEG;

import java.io.Serializable;

/**
 * Clase que representa una lista enlazada genérica.
 *
 * @param <T> el tipo de elementos que contendrá la lista
 */
public class ListaEnlazada<T> implements Serializable {
    private NodoLEG<T> cabeza;

    /**
     * Constructor que inicializa una lista enlazada vacía.
     */
    public ListaEnlazada() {
        this.cabeza = null;
    }

    /**
     * Agrega un nuevo elemento al final de la lista.
     *
     * @param dato el dato a agregar
     */
    public void agregar(T dato) {
        NodoLEG<T> nuevoNodo = new NodoLEG<>(dato);
        if (cabeza == null) {
            cabeza = nuevoNodo;
        } else {
            NodoLEG<T> actual = cabeza;
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nuevoNodo);
        }
    }

    /**
     * Obtiene la cabeza de la lista.
     *
     * @return la cabeza de la lista
     */
    public NodoLEG<T> getCabeza() {
        return cabeza;
    }

    /**
     * Elimina un elemento de la lista.
     *
     * @param dato el dato a eliminar
     * @return true si el elemento fue eliminado, false en caso contrario
     */
    public boolean eliminar(T dato) {
        if (dato == null) return false;
        if (cabeza == null) return false;

        NodoLEG<T> actual = cabeza, anterior = null;

        while (actual != null && !actual.getDato().equals(dato)) {
            anterior = actual;
            actual = actual.getSiguiente();
        }

        if (actual == null) return false;

        if (anterior == null) {
            cabeza = actual.getSiguiente();
        } else {
            anterior.setSiguiente(actual.getSiguiente());
        }

        return true;
    }

    /**
     * Establece la cabeza de la lista.
     *
     * @param cabeza la nueva cabeza de la lista
     */
    protected void setCabeza(NodoLEG<T> cabeza) {
        this.cabeza = cabeza;
    }

    /**
     * Elimina todos los elementos de la lista.
     */
    public void limpiar() {
        this.cabeza = null; // Eliminar todos los nodos
    }
}