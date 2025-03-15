import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class Asignatura {
    private String nombre;
    private String codigo;
    private String profesor;
    private ListaEnlazada<Tarea> tareas;

    public Asignatura (String nombre, String codigo, String profesor){
        this.nombre = nombre;
        this.codigo = codigo;
        this.profesor = profesor;
        this.tareas = new ListaEnlazada<>();
    }


    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getProfesor() {
        return profesor;
    }

    public ListaEnlazada<Tarea> getTareas() {
        return tareas;
    }

    public void añadirTarea(Tarea tarea) {
        tareas.agregar(tarea);
    }

    public void eliminarTarea(Tarea tarea) {
        tareas.eliminar(tarea);
    }

    @Override
    public String toString() {
        return "Código: " + codigo + ", Nombre: " + nombre + ", Profesor: " + profesor;
    }




    public void escribirTareasEnFichero(String nombreFichero) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreFichero))) {
            NodoLEG<Tarea> nodo = tareas.getCabeza();
            while (nodo != null) {
                writer.write(nodo.getDato().toString());
                writer.newLine();
                nodo = nodo.getSiguiente();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
