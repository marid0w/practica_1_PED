package librerias.estructurasDeDatos.modelos;

public interface LE<T> {
    void agregar(T elemento); // Añade un elemento a la lista
    void eliminar(T elemento); // Elimina un elemento de la lista
    boolean buscar(T elemento); // Busca un elemento en la lista
    boolean estaVacia(); // Verifica si la lista está vacía
    int tamaño(); // Devuelve el número de elementos en la lista
}