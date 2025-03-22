package librerias.estructurasDeDatos.lineales;

import librerias.estructurasDeDatos.modelos.LE;

/**
 * Clase que implementa una Lista Enlazada Genérica (LEG).
 *
 * @param <T> el tipo de elementos que contendrá la lista
 */
public class LEG<T> implements LE<T> {
    private NodoLEG<T> cabeza; // Nodo inicial de la lista
    private int tamaño; // Tamaño de la lista

    /**
     * Constructor que inicializa una lista vacía.
     */
    public LEG() {
        this.cabeza = null;
        this.tamaño = 0;
    }

    /**
     * Agrega un elemento al final de la lista.
     *
     * @param elemento el elemento a agregar
     */
    @Override
    public void agregar(T elemento) {
        NodoLEG<T> nuevoNodo = new NodoLEG<>(elemento);
        if (cabeza == null) {
            cabeza = nuevoNodo;
        } else {
            NodoLEG<T> actual = cabeza;
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nuevoNodo);
        }
        tamaño++;
    }

    /**
     * Elimina un elemento de la lista.
     *
     * @param elemento el elemento a eliminar
     */
    @Override
    public void eliminar(T elemento) {
        if (cabeza == null) return;

        // Si el elemento a eliminar es la cabeza
        if (cabeza.getDato().equals(elemento)) {
            cabeza = cabeza.getSiguiente();
            tamaño--;
            return;
        }

        // Buscar el elemento en la lista
        NodoLEG<T> actual = cabeza;
        while (actual.getSiguiente() != null && !actual.getSiguiente().getDato().equals(elemento)) {
            actual = actual.getSiguiente();
        }

        // Si se encontró el elemento, eliminarlo
        if (actual.getSiguiente() != null) {
            actual.setSiguiente(actual.getSiguiente().getSiguiente());
            tamaño--;
        }
    }

    /**
     * Busca un elemento en la lista.
     *
     * @param elemento el elemento a buscar
     * @return true si el elemento está en la lista, false en caso contrario
     */
    @Override
    public boolean buscar(T elemento) {
        NodoLEG<T> actual = cabeza;
        while (actual != null) {
            if (actual.getDato().equals(elemento)) {
                return true;
            }
            actual = actual.getSiguiente();
        }
        return false;
    }

    /**
     * Verifica si la lista está vacía.
     *
     * @return true si la lista está vacía, false en caso contrario
     */
    @Override
    public boolean estaVacia() {
        return cabeza == null;
    }

    /**
     * Devuelve el tamaño de la lista.
     *
     * @return el tamaño de la lista
     */
    @Override
    public int tamaño() {
        return tamaño;
    }
}