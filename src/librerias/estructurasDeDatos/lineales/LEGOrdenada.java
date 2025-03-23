package librerias.estructurasDeDatos.lineales;

    /**
     * Clase que implementa una Lista Enlazada Genérica Ordenada (LEGOrdenada).
     *
     * @param <T> el tipo de elementos que contendrá la lista, debe ser Comparable
     */
    public class LEGOrdenada<T extends Comparable<T>> extends ListaEnlazada<T> {

        /**
         * Constructor que inicializa una lista ordenada vacía.
         */
        public LEGOrdenada() {
            super();
        }

        /**
         * Agrega un elemento a la lista manteniendo el orden.
         *
         * @param dato el elemento a agregar
         */
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