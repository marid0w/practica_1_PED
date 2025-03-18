public class NodoLEG<T> {
    private T dato;
    private NodoLEG<T> siguiente;

    public NodoLEG(T dato) {
        this.dato = dato;
        this.siguiente = null;
    }

    public T getDato() {
        return dato;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }

    public NodoLEG<T> getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoLEG<T> siguiente) {

        this.siguiente = siguiente;
    }
}