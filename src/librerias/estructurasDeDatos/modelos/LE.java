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

}