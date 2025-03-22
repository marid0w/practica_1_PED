package librerias.estructurasDeDatos.modelos;

/**
 * Interfaz que define los métodos para una Lista Enlazada (LE).
 *
 * @param <T> el tipo de elementos que contendrá la lista
 */
public interface LE<T> {

    /**
     * Añade un elemento a la lista.
     *
     * @param elemento el elemento a añadir
     */
    void agregar(T elemento);

    /**
     * Elimina un elemento de la lista.
     *
     * @param elemento el elemento a eliminar
     */
    void eliminar(T elemento);

    /**
     * Busca un elemento en la lista.
     *
     * @param elemento el elemento a buscar
     * @return true si el elemento está en la lista, false en caso contrario
     */
    boolean buscar(T elemento);

    /**
     * Verifica si la lista está vacía.
     *
     * @return true si la lista está vacía, false en caso contrario
     */
    boolean estaVacia();

    /**
     * Devuelve el número de elementos en la lista.
     *
     * @return el tamaño de la lista
     */
    int tamaño();
}