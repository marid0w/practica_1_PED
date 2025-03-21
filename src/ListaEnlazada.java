import java.io.Serializable;

public class ListaEnlazada<T> implements Serializable {
    private NodoLEG<T> cabeza;

    public ListaEnlazada() {
        this.cabeza = null;
    }

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

    public NodoLEG<T> getCabeza() {
        return cabeza;
    }

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
}