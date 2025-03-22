package librerias.estructurasDeDatos.lineales;
import librerias.estructurasDeDatos.modelos.LE;

public class LEG<T> implements LE<T> {
    private NodoLEG<T> cabeza; // Nodo inicial de la lista
    private int tamaño; // Tamaño de la lista

    // Constructor
    public LEG() {
        this.cabeza = null;
        this.tamaño = 0;
    }

    // Implementación de los métodos de la interfaz LE

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

    @Override
    public boolean estaVacia() {
        return cabeza == null;
    }

    @Override
    public int tamaño() {
        return tamaño;
    }
}