package librerias.estructurasDeDatos.lineales;

import java.io.Serializable;
import librerias.estructurasDeDatos.modelos.LE;

/**
 * Clase que representa una lista enlazada genérica.
 *
 * @param <T> el tipo de elementos que contendrá la lista
 */
public class ListaEnlazada<T> implements LE<T>, Serializable {
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
    @Override
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
     * Elimina un elemento de la lista.
     *
     * @param dato el dato a eliminar
     */
    @Override
    public void eliminar(T dato) {
        if (dato == null) return;
        if (cabeza == null) return;

        NodoLEG<T> actual = cabeza, anterior = null;

        while (actual != null && !actual.getDato().equals(dato)) {
            anterior = actual;
            actual = actual.getSiguiente();
        }

        if (actual == null) return;

        if (anterior == null) {
            cabeza = actual.getSiguiente();
        } else {
            anterior.setSiguiente(actual.getSiguiente());
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