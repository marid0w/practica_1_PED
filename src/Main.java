import librerias.excepcionesDeUsuario.GestionAsignaturas;
import librerias.excepcionesDeUsuario.Menu;

/**
 * Clase principal que inicia la aplicación de gestión de asignaturas y tareas.
 */
public class Main {
    public static void main(String[] args) {
        // Objetos
        GestionAsignaturas gestion = new GestionAsignaturas();
        Menu menu = new Menu();

        // Instancias
        gestion.cargarAsignaturas();
        gestion.cargarTareas();
        menu.mostrarMenu();
    }
}