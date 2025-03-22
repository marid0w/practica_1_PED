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
            System.out.println("===================================");
            System.out.println("1.- Alta de asignatura");
            System.out.println("2.- Baja de asignatura");
            System.out.println("3.- Modificación de  asignatura");
            System.out.println("---------------------------------------------------");
            System.out.println("4.- Añadir tareas a una asignatura");
            System.out.println("---------------------------------------------------");
            System.out.println("5.- Listado general de asignaturas por codigo (orden ascendente)");
            System.out.println("6.- Listado general de asignaturas por codigo (orden descendente)");
            System.out.println("7.- Listado datos completos de una asignatura");
            System.out.println("8.- Listado completo de tareas");
            System.out.println("9.- Listado resumido de tareas");
            System.out.println("\n10.- Informe de puntuaciones - resumido");
            System.out.println("---------------------------------------------------");
            System.out.println("11.- Asignatura/s con el mayor numero de tareas");
            System.out.println("12.- Examen/es final/es con menor puntuacion");
            System.out.println("---------------------------------------------------");
            System.out.println("\n0.- Salir");
            System.out.println(" Elija una opción: ");
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
                    gestionAsignaturas.listarAsignaturasConMayorNumeroTareas();
                    break;

                case 12:
                    gestionAsignaturas.listarExamenesFinalesConMenorPuntuacion();
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

