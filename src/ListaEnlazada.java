public class ListaEnlazada<T> {
    // NodoLEG es la cabeza de la lista enlazada
    private NodoLEG<T> cabeza;

    // El constructor inicializa la cabeza a null, indicando una lista vacía
    public ListaEnlazada() {

        this.cabeza = null;
    }

    // Metodo para agregar un nuevo elemento a la lista enlazada
    public void agregar(T dato) {
        // Crear un nuevo nodo con el dato proporcionado
        NodoLEG<T> nuevoNodo = new NodoLEG<>(dato);
        // Si la lista está vacía, establecer el nuevo nodo como la cabeza
        if (cabeza == null) {
            cabeza = nuevoNodo;
        } else {
            // De lo contrario, recorrer hasta el final de la lista
            NodoLEG<T> actual = cabeza;
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            // Establecer el nuevo nodo como el siguiente nodo del último nodo
            actual.setSiguiente(nuevoNodo);
        }
    }

    // Método para obtener la cabeza de la lista
    public NodoLEG<T> getCabeza() {
        return cabeza;
    }

    public boolean eliminar(T dato) {
        if (dato == null) return false; // Dato inválido

        if (cabeza == null) return false; // La lista está vacía

        NodoLEG<T> actual = cabeza, anterior = null;

        // Recorrer la lista para encontrar el nodo a eliminar
        while (actual != null && !actual.getDato().equals(dato)) {
            anterior = actual;
            actual = actual.getSiguiente();
        }

        if (actual == null) return false; // Dato no encontrado

        if (anterior == null) {
            // Eliminar la cabeza de la lista
            cabeza = actual.getSiguiente();
        } else {
            // Saltar el nodo a eliminar
            anterior.setSiguiente(actual.getSiguiente());
        }

        return true;
    }


}