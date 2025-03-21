public class LEGOrdenada<T extends Comparable<T>> extends ListaEnlazada<T> {
    public LEGOrdenada() {
        super();
    }

    @Override
    public void agregar(T dato) {
        NodoLEG<T> nuevoNodo = new NodoLEG<>(dato);
        if (getCabeza() == null) {
            setCabeza(nuevoNodo);
        } else {
            NodoLEG<T> actual = getCabeza(), anterior = null;
            while (actual != null && actual.getDato().compareTo(dato) < 0) {
                anterior = actual;
                actual = actual.getSiguiente();
            }
            if (anterior == null) {
                nuevoNodo.setSiguiente(getCabeza());
                setCabeza(nuevoNodo);
            } else {
                anterior.setSiguiente(nuevoNodo);
                nuevoNodo.setSiguiente(actual);
            }
        }
    }
}