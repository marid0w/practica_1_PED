import java.util.Scanner;

public class Menu {

    private Scanner scanner;
    private GestionAsignaturas gestionAsignaturas;
    private Asignatura asignatura;

    public Menu() {
        scanner = new Scanner(System.in);
        gestionAsignaturas = new GestionAsignaturas();
        asignatura = new Asignatura("nombre", "codigo", "profesor");
    }

    public void mostrarMenu() {
        int opcion = 0;
        do {
            System.out.println("\n\tMENU PRINCIPAL");
            System.out.println("\n===================================");
            System.out.println("\n1.- Alta de asignatura");
            System.out.println("\n2.- Baja de asignatura");
            System.out.println("\n3.- Modificación de  asignatura");
            System.out.println("\n---------------------------------------------------");
            System.out.println("\n4.- Añadir tareas a una asignatura");
            System.out.println("\n---------------------------------------------------");
            System.out.println("\n5.- Listado general de asignaturas por codigo (orden ascendente)");
            System.out.println("\n6.- Listado general de asignaturas por codigo (orden descendente)");
            System.out.println("\n7.- Listado datos completos de una asignatura");
            System.out.println("\n8.- Listado completo de tareas");
            System.out.println("\n9.- Listado resumido de tareas");
            System.out.println("\n\n10.- Informe de puntuaciones - resumido");
            System.out.println("\n---------------------------------------------------");
            System.out.println("\n11.- Asignatura/s con el mayor numero de tareas");
            System.out.println("\n12.- Examen/es final/es con menor puntuacion");
            System.out.println("\n---------------------------------------------------");
            System.out.println("\n\n0.- Salir");
            System.out.println("\n\n Elija una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    gestionAsignaturas.altaAsignatura();
                    break;

                case 2:
                    gestionAsignaturas.bajaAsignatura();
                    break;

                case 3:
                    gestionAsignaturas.modificacionAsignaturas();
                    break;

                case 4:
                    gestionAsignaturas.agregarTareasAAsignatura();
                    break;

                case 5:
                    gestionAsignaturas.listarAsignaturasPorCodigoAscendente();
                    break;

                case 6:gestionAsignaturas.listarAsignaturasPorCodigoDescendente();
                    break;

                case 7:gestionAsignaturas.listarDatosCompletosAsignatura();
                    break;

                case 8:
                    gestionAsignaturas.listarCompletoTareas();
                    break;

                case 9:
                    gestionAsignaturas.listarResumidoTareas();
                    break;

                case 10:
                    gestionAsignaturas.informePuntuacionesResumido();
                    break;

                case 11:

                    break;

                case 12:

                    break;

                case 0:
                    gestionAsignaturas.guardarAsignaturas();
                    gestionAsignaturas.guardarTareas();
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }

        }while (opcion != 0);
    }

}

