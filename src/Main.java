public class Main {
    public static void main(String[] args) {
        //Objetos
        GestionAsignaturas gestion = new GestionAsignaturas();
        Menu menu = new Menu();

        //Instancias
        gestion.cargarAsignaturas();
        gestion.cargarTareas();
        menu.mostrarMenu();
    }
}
