package librerias.estructurasDeDatos.lineales;

import java.io.Serializable;

/**
 * Clase que representa un nodo de una lista enlazada genérica.
 *
 * @param <T> el tipo de dato que almacenará el nodo
 */
public class NodoLEG<T> implements Serializable {
    private T dato; // Dato almacenado en el nodo
    private NodoLEG<T> siguiente; // Referencia al siguiente nodo

    /**
     * Constructor que inicializa el nodo con un dato.
     *
     * @param dato el dato a almacenar en el nodo
     */
    public NodoLEG(T dato) {
        this.dato = dato;
        this.siguiente = null;
    }

    /**
     * Obtiene el dato almacenado en el nodo.
     *
     * @return el dato almacenado en el nodo
     */
    public T getDato() {
        return dato;
    }

    /**
     * Establece el dato del nodo.
     *
     * @param dato el dato a almacenar en el nodo
     */
    public void setDato(T dato) {
        this.dato = dato;
    }

    /**
     * Obtiene la referencia al siguiente nodo.
     *
     * @return la referencia al siguiente nodo
     */
    public NodoLEG<T> getSiguiente() {
        return siguiente;
    }

    /**
     * Establece la referencia al siguiente nodo.
     *
     * @param siguiente la referencia al siguiente nodo
     */
    public void setSiguiente(NodoLEG<T> siguiente) {
        this.siguiente = siguiente;
    }
}