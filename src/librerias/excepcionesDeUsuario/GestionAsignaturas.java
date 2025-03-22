package librerias.excepcionesDeUsuario;

import librerias.estructurasDeDatos.lineales.LEGOrdenada;
import librerias.estructurasDeDatos.lineales.NodoLEG;

import java.io.*;
import java.util.Scanner;


/**
 * Clase que gestiona las asignaturas y sus tareas.
 */

public class GestionAsignaturas {
    private ListaEnlazada<Asignatura> asignaturas;
    private Scanner scanner;
    private LEGOrdenada<Asignatura> asignaturasOrdenadasAscendente;

    /**
     * Constructor que inicializa la gestión de asignaturas.
     */

    public GestionAsignaturas() {
        this.asignaturas = new ListaEnlazada<>();
        this.scanner = new Scanner(System.in);
        this.asignaturasOrdenadasAscendente = new LEGOrdenada<>();
        cargarAsignaturas();
        cargarTareas();
    }

    /**
     * Guarda las asignaturas en un archivo.
     */

    public void guardarAsignaturas() {
        String nombreFichero = "asignaturas.dat"; // Nombre fijo del fichero
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nombreFichero))) {
            NodoLEG<Asignatura> nodo = asignaturas.getCabeza(); // Obtener la cabeza de la lista
            while (nodo != null) {
                oos.writeObject(nodo.getDato()); // Escribir la asignatura en el fichero
                nodo = nodo.getSiguiente(); // Avanzar al siguiente nodo
            }
            System.out.println("\tAsignaturas guardadas correctamente.");
        } catch (IOException e) {
            System.err.println("\tError al guardar las asignaturas: " + e.getMessage());
        }
    }


    /**
     * Carga las asignaturas desde un archivo.
     */
    public void cargarAsignaturas() {
        String filePath = "asignaturas.dat"; // Ruta  del fichero
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            asignaturas = new ListaEnlazada<>();
            while (true) {
                try {
                    Asignatura asignatura = (Asignatura) ois.readObject();
                    asignaturas.agregar(asignatura);
                } catch (EOFException e) {
                    break;
                }
            }
            System.out.println("\tAsignaturas cargadas correctamente.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("\tError al cargar las asignaturas: " + e.getMessage());
        }
    }

    /**
     * Guarda las tareas en un archivo.
     */

    public void guardarTareas() {
        String nombreFichero = "tareas.dat"; // Nombre fijo del fichero
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nombreFichero))) {
            NodoLEG<Asignatura> nodo = asignaturas.getCabeza(); // Obtener la cabeza de la lista
            while (nodo != null) {
                ListaEnlazada<Tarea> tareas = nodo.getDato().getTareas();
                NodoLEG<Tarea> tareaNodo = tareas.getCabeza();
                while (tareaNodo != null) {
                    oos.writeObject(tareaNodo.getDato());
                    tareaNodo = tareaNodo.getSiguiente();
                }
                nodo = nodo.getSiguiente(); // Avanzar al siguiente nodo
            }
            System.out.println("\tTareas guardadas correctamente.");
        } catch (IOException e) {
            System.err.println("\tError al guardar las tareas: " + e.getMessage());
        }
    }

    /**
     * Carga las tareas desde un archivo.
     */

    public void cargarTareas() {
        String filePath = "tareas.dat"; // Ruta del fichero
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            // Limpiar las tareas existentes antes de cargar las nuevas
            NodoLEG<Asignatura> nodoAsignatura = asignaturas.getCabeza();
            while (nodoAsignatura != null) {
                nodoAsignatura.getDato().getTareas().limpiar();
                nodoAsignatura = nodoAsignatura.getSiguiente();
            }

            // Cargar las tareas desde el archivo
            while (true) {
                try {
                    Tarea tarea = (Tarea) ois.readObject();
                    Asignatura asignatura = buscarAsignaturaPorCodigo(tarea.getCodigoAsignatura());
                    if (asignatura != null) {
                        asignatura.getTareas().agregar(tarea);
                    }
                } catch (EOFException e) {
                    break;
                }
            }
            System.out.println("\tTareas cargadas correctamente.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("\tError al cargar las tareas: " + e.getMessage());
        }
    }

    /**
     * Da de alta una nueva asignatura.
     */

    public void altaAsignatura() {
        boolean continuar = true;

        System.out.println("\n\t\t\tALTA DE ASIGNATURA");

        while (continuar) {
            System.out.println("\n\tIntroduce el código de la asignatura (numero entero): ");
            String codigo = scanner.nextLine();

            // Comprueba si el código ya existe en la lista
            if (buscarAsignaturaPorCodigo(codigo) != null) {
                System.out.println("\n\tError: este código ya está registrado en la aplicación.");
            } else {

                System.out.println("\tIntroduce el nombre de la asignatura: ");
                String nombre = scanner.nextLine();
                System.out.println("\tIntroduce el profesor de la asignatura: ");
                String profesor = scanner.nextLine();

                Asignatura asignatura = new Asignatura(nombre, codigo, profesor);
                asignaturas.agregar(asignatura);
                System.out.println("\tAsignatura añadida correctamente.");
            }


            System.out.println("\n\t¿Desea añadir una nueva asignatura? (S/N)");
            String respuesta = scanner.nextLine().trim().toUpperCase();
            if (!respuesta.equals("S")) {
                continuar = false;
            }
        }
    }

    /**
     * Busca una asignatura por su código.
     *
     * @param codigo el código de la asignatura a buscar
     * @return la asignatura encontrada o null si no se encuentra
     */

    public Asignatura buscarAsignaturaPorCodigo(String codigo) {
        if (codigo == null) {
            return null;
        }
        NodoLEG<Asignatura> nodo = asignaturas.getCabeza(); // Obtener la cabeza de la lista

        while (nodo != null) {
            Asignatura asignatura = nodo.getDato();

            // Comparamos los códigos
            if (asignatura.getCodigo().trim().equalsIgnoreCase(codigo.trim())) {
                return asignatura;
            }

            nodo = nodo.getSiguiente(); // Avanzar al siguiente nodo
        }

        return null;
    }

    /**
     * Da de baja una asignatura.
     */

    public void bajaAsignatura() {
        boolean continuar = true;
        mostrarListaAsignaturas();

        System.out.println("\n\t\t\tBAJA DE ASIGNATURA");

        while (continuar) {
            System.out.println("\n\tIntroduce el código de la asignatura a dar de baja: ");
            String codigo = scanner.nextLine();

            Asignatura asignatura = buscarAsignaturaPorCodigo(codigo);

            if (asignatura == null) {
                System.out.println("\n\tError: este código no está registrado en la aplicación.");
            } else {
                // Mostrar los detalles de la asignatura en el formato deseado
                mostrarDetallesAsignatura(asignatura);

                // Confirmación del usuario
                System.out.println("\n\t¿Desea realmente dar de baja a esta asignatura? (S/N)");
                String respuesta = scanner.nextLine().trim().toUpperCase();

                if (respuesta.equals("S")) {
                    asignaturas.eliminar(asignatura);
                    System.out.println("\tAsignatura eliminada correctamente.");
                } else {
                    System.out.println("\tProceso de baja abortado.");
                }
            }

            // Preguntar si desea dar de baja otra asignatura
            System.out.println("\n\t¿Desea dar de baja otra asignatura? (S/N)");
            String respuestaContinuar = scanner.nextLine().trim().toUpperCase();
            if (!respuestaContinuar.equals("S")) {
                continuar = false;
            }
        }
        mostrarListaAsignaturas();
    }

    /**
     * Muestra los detalles de una asignatura.
     *
     * @param asignatura la asignatura cuyos detalles se mostrarán
     */

    public void mostrarDetallesAsignatura(Asignatura asignatura) {
        System.out.println("\nASIGNATURA: " + asignatura.getNombre());
        System.out.println("Código: " + asignatura.getCodigo());
        System.out.println("Profesor: " + asignatura.getProfesor());

        // Mostrar las tareas de la asignatura
        System.out.println("\nTareas\t\tPuntuación");
        System.out.println("-----------------------------");

        NodoLEG<Tarea> nodoTarea = asignatura.getTareas().getCabeza();
        int contador = 0; // Contador para depuración
        while (nodoTarea != null) {
            Tarea tarea = nodoTarea.getDato();
            System.out.println(tarea.getNombre() + "\t\t" + tarea.getPuntuacion());
            nodoTarea = nodoTarea.getSiguiente();
            contador++; // Incrementar el contador
        }

        System.out.println("Total de tareas mostradas: " + contador); // Depuración
        System.out.println("\nPulse <Intro> para continuar...");
        scanner.nextLine(); // Esperar a que el usuario pulse Intro
    }

    /**
     * Muestra la lista de asignaturas.
     */

    public void mostrarListaAsignaturas() {
        NodoLEG<Asignatura> nodo = asignaturas.getCabeza();
        while (nodo != null) {
            System.out.println(nodo.getDato());
            nodo = nodo.getSiguiente();
        }
    }

    /**
     * Modifica los datos de una asignatura.
     */

    public void modificacionAsignaturas() {
        boolean continuar = true;
        mostrarListaAsignaturas();

        System.out.println("\n\t\t\tMODIFICACION DATOS DE ASIGNATURA");

        while (continuar) {
            System.out.println("\n\tIntroduce el código de la asignatura a modificar: ");
            String codigo = scanner.nextLine();

            Asignatura asignatura = buscarAsignaturaPorCodigo(codigo);

            if (asignatura == null) {
                System.out.println("\n\tError: este código no está registrado en la aplicación.");
            } else {

                System.out.println("\tAsignatura encontrada:");
                System.out.println(asignatura);


                System.out.println("\t¿Desea realmente modificar los datos de esta asignatura? (S/N)");
                String respuesta = scanner.nextLine().trim().toUpperCase();

                if (respuesta.equals("S")) {
                    boolean modificar = true;
                    while (modificar) {
                        System.out.println("\n\tSeleccione el campo a modificar:");
                        System.out.println("\t1. Nombre");
                        System.out.println("\t2. Profesor");
                        System.out.println("\t0. Finalizar modificación");
                        int opcion = Integer.parseInt(scanner.nextLine());

                        switch (opcion) {
                            case 1:
                                System.out.println("\n\tIntroduce el nuevo nombre de la asignatura: ");
                                String nuevoNombre = scanner.nextLine();
                                asignatura.setNombre(nuevoNombre);
                                System.out.println("\tNombre de la asignatura modificado correctamente.");
                                break;

                            case 2:
                                System.out.println("\n\tIntroduce el nuevo profesor de la asignatura: ");
                                String nuevoProfesor = scanner.nextLine();
                                asignatura.setProfesor(nuevoProfesor);
                                System.out.println("\tProfesor de la asignatura modificado correctamente.");
                                break;

                            case 0:
                                modificar = false;
                                System.out.println("\n\tModificación finalizada.");
                                break;

                            default:
                                System.out.println("\tOpción no válida. Intente de nuevo.");
                                break;
                        }
                    }
                }
            }


            System.out.println("\n\t¿Desea modificar otra asignatura? (S/N)");
            String respuestaContinuar = scanner.nextLine().trim().toUpperCase();
            if (!respuestaContinuar.equals("S")) {
                continuar = false;
            }
        }
    }

    /**
     * Ordena la lista de asignaturas por código en orden ascendente.
     */
    public void ordenarListaPorCodigo() {
        if (asignaturas.getCabeza() == null) return;

        NodoLEG<Asignatura> actual = asignaturas.getCabeza();
        while (actual != null) {
            NodoLEG<Asignatura> minimo = actual;
            NodoLEG<Asignatura> siguiente = actual.getSiguiente();

            while (siguiente != null) {
                if (siguiente.getDato().getCodigo().compareTo(minimo.getDato().getCodigo()) < 0) {
                    minimo = siguiente;
                }
                siguiente = siguiente.getSiguiente();
            }

            if (minimo != actual) {
                Asignatura temp = actual.getDato();
                actual.setDato(minimo.getDato());
                minimo.setDato(temp);
            }

            actual = actual.getSiguiente();
        }
    }


    /**
     * Lista las asignaturas por código en orden ascendente.
     */

    public void listarAsignaturasPorCodigoAscendente() {
        ordenarListaPorCodigo();

        System.out.println("\n\tLISTADO GENERAL DE ASIGNATURAS (Ascendente)");
        System.out.println();
        System.out.printf("\t%-10s %-30s %-30s\n", "Código", "Asignatura", "Profesor/a");
        System.out.println("\t----------------------------------------------------------------------------");

        NodoLEG<Asignatura> nodo = asignaturas.getCabeza();
        while (nodo != null) {
            Asignatura asignatura = nodo.getDato();
            System.out.printf("\t%-10s %-30s %-30s\n",
                    asignatura.getCodigo(),
                    asignatura.getNombre(),
                    asignatura.getProfesor());
            nodo = nodo.getSiguiente();
        }

        System.out.println("\t----------------------------------------------------------------------------");
        System.out.println("\t...... Pulse <Intro> para continuar...");
        scanner.nextLine();
    }

    /**
     * Ordena la lista de asignaturas por código en orden descendente.
     */

    public void ordenarListaPorCodigoDescendente() {
        if (asignaturas.getCabeza() == null) return;

        boolean swapped;
        do {
            swapped = false;
            NodoLEG<Asignatura> current = asignaturas.getCabeza();

            while (current != null && current.getSiguiente() != null) {
                NodoLEG<Asignatura> next = current.getSiguiente();

                if (Integer.parseInt(current.getDato().getCodigo()) < Integer.parseInt(next.getDato().getCodigo())) {
                    Asignatura temp = current.getDato();
                    current.setDato(next.getDato());
                    next.setDato(temp);
                    swapped = true;
                }

                current = next;
            }
        } while (swapped);
    }

    /**
     * Lista las asignaturas por código en orden descendente.
     */

    public void listarAsignaturasPorCodigoDescendente() {
        ordenarListaPorCodigoDescendente();

        System.out.println("\n\tLISTADO GENERAL DE ASIGNATURAS (Descendente)");
        System.out.println();
        System.out.printf("\t%-10s %-30s %-30s\n", "Código", "Asignatura", "Profesor/a");
        System.out.println("\t----------------------------------------------------------------------------");

        NodoLEG<Asignatura> nodo = asignaturas.getCabeza();
        while (nodo != null) {
            Asignatura asignatura = nodo.getDato();
            System.out.printf("\t%-10s %-30s %-30s\n",
                    asignatura.getCodigo(),
                    asignatura.getNombre(),
                    asignatura.getProfesor());
            nodo = nodo.getSiguiente();
        }

        System.out.println("\t----------------------------------------------------------------------------");
        System.out.println("\t...... Pulse <Intro> para continuar...");
        scanner.nextLine();
    }

    /**
     * Lista los datos completos de una asignatura.
     */

    public void listarDatosCompletosAsignatura() {
        boolean continuar = true;

        System.out.println("\n\t\t\tLISTAR DATOS COMPLETOS DE UNA ASIGNATURA");

        while (continuar) {
            System.out.println("\n\t\tIntroduce el código de la asignatura: ");
            String codigo = scanner.nextLine();

            // Buscar la asignatura por código
            Asignatura asignatura = buscarAsignaturaPorCodigo(codigo);

            if (asignatura == null) {
                System.out.println("\n\tError: este código no está registrado en la aplicación.");
            } else {
                // Mostrar los datos de la asignatura
                System.out.println("\n\tASIGNATURA: " + asignatura.getNombre());
                System.out.printf("\n\t%-20s %s\n", "Código:", asignatura.getCodigo());
                System.out.printf("\t%-20s %s\n", "Profesor:", asignatura.getProfesor());

                // Mostrar las tareas de la asignatura
                System.out.printf("\n\t%-30s %s\n", "Tareas", "Puntuación");
                System.out.println("\t------------------------------------------------");

                NodoLEG<Tarea> nodoTarea = asignatura.getTareas().getCabeza();
                while (nodoTarea != null) {
                    Tarea tarea = nodoTarea.getDato();
                    System.out.printf("\t%-30s %d\n", tarea.getNombre(), tarea.getPuntuacion());
                    nodoTarea = nodoTarea.getSiguiente();
                }
            }

            System.out.println("\n\t¿Desea ver la información de otra asignatura? (S/N)");
            String respuesta = scanner.nextLine().trim().toUpperCase();
            if (!respuesta.equals("S")) {
                continuar = false;
            }
        }

        System.out.println("\n\tPulse <Intro> para continuar...");
        scanner.nextLine(); // Esperar a que el usuario pulse Intro
    }

    /**
     * Agrega tareas a una asignatura.
     */
    public void agregarTareasAAsignatura() {
        boolean continuar = true;

        System.out.println("\n\t\t\tAGREGAR TAREAS A ASIGNATURA");

        while (continuar) {
            mostrarListaAsignaturas();
            System.out.println("\n\tIntroduce el código de la asignatura a la que deseas agregar tareas: ");
            String codigo = scanner.nextLine();

            Asignatura asignatura = buscarAsignaturaPorCodigo(codigo);

            if (asignatura == null) {
                System.out.println("\n\tError: este código no está registrado en la aplicación.\n");
            } else {
                System.out.println("\tAsignatura encontrada:");
                System.out.println(asignatura);
                asignatura.agregarTareasAsignatura();
            }

            System.out.println("\n\t¿Desea agregar tareas a otra asignatura? (S/N)");
            String respuesta = scanner.nextLine().trim().toUpperCase();
            if (!respuesta.equals("S")) {
                continuar = false;
            }
        }
    }

    /**
     * Lista todas las tareas de las asignaturas.
     */

    public void listarCompletoTareas() {
        ordenarListaPorCodigo(); // Ordenar la lista de asignaturas por código en orden ascendente

        System.out.println("\n\t\t\t\tLISTADO DE TAREAS");
        System.out.println();
        System.out.println("\tCódigo      Asignatura                     Tareas                 Puntuación");
        System.out.println("\t----------------------------------------------------------------------------------");

        NodoLEG<Asignatura> nodoAsignatura = asignaturas.getCabeza();
        int numeroAsignaturas = 0;
        int numeroTotalTareas = 0;

        while (nodoAsignatura != null) {
            Asignatura asignatura = nodoAsignatura.getDato();
            System.out.printf("\t%-10s %-30s\n", asignatura.getCodigo(), asignatura.getNombre());
            NodoLEG<Tarea> nodoTarea = asignatura.getTareas().getCabeza();

            while (nodoTarea != null) {
                Tarea tarea = nodoTarea.getDato();
                System.out.printf("\t%40s %-25s %d\n", "", tarea.getNombre(), tarea.getPuntuacion());
                nodoTarea = nodoTarea.getSiguiente();
                numeroTotalTareas++;
            }

            System.out.println("\t----------------------------------------------------------------------------------");
            nodoAsignatura = nodoAsignatura.getSiguiente();
            numeroAsignaturas++;
        }

        System.out.println("\t**   NÚMERO DE ASIGNATURAS: " + numeroAsignaturas);
        System.out.println("\t**   NÚMERO TOTAL DE TAREAS ESTABLECIDAS: " + numeroTotalTareas);
        System.out.println("\n\tPulse <Intro> para continuar...");
        scanner.nextLine();
    }

    /**
     * Lista las tareas de las asignaturas de forma resumida.
     */
    public void listarResumidoTareas() {
        ordenarListaPorCodigo(); // Ordenar la lista de asignaturas por código en orden ascendente

        System.out.println("\t\tLISTADO RESUMIDO DE TAREAS");
        System.out.println();
        System.out.println("\tCódigo      Asignatura                     Tareas         Examen Final");
        System.out.println("\t----------------------------------------------------------------------------");

        NodoLEG<Asignatura> nodoAsignatura = asignaturas.getCabeza();

        while (nodoAsignatura != null) {
            Asignatura asignatura = nodoAsignatura.getDato();
            int numeroTareas = 0;
            boolean examenFinal = false;

            NodoLEG<Tarea> nodoTarea = asignatura.getTareas().getCabeza();
            while (nodoTarea != null) {
                Tarea tarea = nodoTarea.getDato();
                if (tarea.getTipo().equalsIgnoreCase("Examen Final")) {
                    examenFinal = true;
                } else {
                    numeroTareas++;
                }
                nodoTarea = nodoTarea.getSiguiente();
            }

            System.out.printf("\t%-10s %-30s %-15d %s\n", asignatura.getCodigo(), asignatura.getNombre(), numeroTareas, examenFinal ? "SI" : "NO");
            System.out.println("\t----------------------------------------------------------------------------");
            nodoAsignatura = nodoAsignatura.getSiguiente();
        }

        System.out.println("\n\tPulse <Intro> para continuar...");
        scanner.nextLine(); // Esperar a que el usuario pulse Intro
    }

    /**
     * Genera un informe resumido de las puntuaciones de las tareas de las asignaturas.
     */
    public void informePuntuacionesResumido() {
        ordenarListaPorCodigo(); // Ordenar la lista de asignaturas por código en orden ascendente

        System.out.println("\t\tINFORME DE PUNTUACIONES (Resumido)");
        System.out.println();
        System.out.println("\tCódigo      Asignatura                     Prácticas        Exámenes Parciales         Examen Final");
        System.out.println("\t-------------------------------------------------------------------------------------------------------------");

        NodoLEG<Asignatura> nodoAsignatura = asignaturas.getCabeza();

        while (nodoAsignatura != null) {
            Asignatura asignatura = nodoAsignatura.getDato();
            int practicas = 0;
            int examenesParciales = 0;
            int examenFinal = 0;

            NodoLEG<Tarea> nodoTarea = asignatura.getTareas().getCabeza();
            while (nodoTarea != null) {
                Tarea tarea = nodoTarea.getDato();
                if (tarea.getTipo().equalsIgnoreCase("Practica")) {
                    practicas += tarea.getPuntuacion();
                } else if (tarea.getTipo().equalsIgnoreCase("Parcial")) {
                    examenesParciales += tarea.getPuntuacion();
                } else if (tarea.getTipo().equalsIgnoreCase("Examen Final")) {
                    examenFinal = tarea.getPuntuacion();
                }
                nodoTarea = nodoTarea.getSiguiente();
            }

            System.out.printf("\t%-10s %-30s %-15s %-30s %s\n",
                    asignatura.getCodigo(),
                    asignatura.getNombre(),
                    practicas == 0 ? "----" : String.valueOf(practicas),
                    examenesParciales == 0 ? "----" : String.valueOf(examenesParciales),
                    examenFinal == 0 ? "----" : String.valueOf(examenFinal));
            System.out.println("\t-------------------------------------------------------------------------------------------------------------");
            nodoAsignatura = nodoAsignatura.getSiguiente();
        }

        System.out.println("\n\tPulse <Intro> para continuar...");
        scanner.nextLine(); // Esperar a que el usuario pulse Intro
    }

    /**
     * Lista las asignaturas con el mayor número de tareas.
     */
    public void listarAsignaturasConMayorNumeroTareas() {
        ordenarListaPorCodigo(); // Ordenar la lista de asignaturas por código en orden ascendente

        if (asignaturas.getCabeza() == null) {
            System.out.println("No hay asignaturas registradas.");
            return;
        }

        // Variables para almacenar el máximo número de tareas y las asignaturas correspondientes
        int maxTareas = 0;
        ListaEnlazada<Asignatura> asignaturasConMaxTareas = new ListaEnlazada<>();

        // Recorrer todas las asignaturas
        NodoLEG<Asignatura> nodo = asignaturas.getCabeza();
        while (nodo != null) {
            Asignatura asignatura = nodo.getDato();
            int numTareas = contarTareas(asignatura.getTareas());

            // Comparar con el máximo actual
            if (numTareas > maxTareas) {
                maxTareas = numTareas;
                asignaturasConMaxTareas = new ListaEnlazada<>(); // Reiniciar la lista
                asignaturasConMaxTareas.agregar(asignatura);
            } else if (numTareas == maxTareas) {
                asignaturasConMaxTareas.agregar(asignatura); // Añadir a la lista si tiene el mismo número de tareas
            }

            nodo = nodo.getSiguiente();
        }

        // Mostrar las asignaturas con el mayor número de tareas
        System.out.println("\n\t\tASIGNATURAS CON EL MAYOR NÚMERO DE TAREAS");
        System.out.println("\t\t-----------------------------------------");

        NodoLEG<Asignatura> nodoMax = asignaturasConMaxTareas.getCabeza();
        while (nodoMax != null) {
            Asignatura asignatura = nodoMax.getDato();
            int numTareas = contarTareas(asignatura.getTareas());

            System.out.println("\t" + asignatura.getNombre() + " (cod. " + asignatura.getCodigo() + ") : " +
                    obtenerDetalleTareas(asignatura.getTareas()) + " ==> " + numTareas + " tareas");
            nodoMax = nodoMax.getSiguiente();
        }

        System.out.println("\n\tPulse <Intro> para continuar...");
        scanner.nextLine();
    }

    /**
     * Cuenta el número de tareas en una lista de tareas.
     *
     * @param tareas la lista de tareas
     * @return el número de tareas
     */
    private int contarTareas(ListaEnlazada<Tarea> tareas) {
        int contador = 0;
        NodoLEG<Tarea> nodo = tareas.getCabeza();
        while (nodo != null) {
            contador++;
            nodo = nodo.getSiguiente();
        }
        return contador;
    }

    /**
     * Obtiene un detalle de las tareas en una lista de tareas.
     *
     * @param tareas la lista de tareas
     * @return una cadena con el detalle de las tareas
     */
    private String obtenerDetalleTareas(ListaEnlazada<Tarea> tareas) {
        int practicas = 0, parciales = 0, examenesFinales = 0;

        NodoLEG<Tarea> nodo = tareas.getCabeza();
        while (nodo != null) {
            Tarea tarea = nodo.getDato();
            switch (tarea.getTipo().toLowerCase()) {
                case "Practica":
                    practicas++;
                    break;
                case "Parcial":
                    parciales++;
                    break;
                case "examen final":
                    examenesFinales++;
                    break;
            }
            nodo = nodo.getSiguiente();
        }

        return practicas + " Practicas, " + parciales + " Parciales y " + examenesFinales + " exámenes finales";
    }

    /**
     * Lista las asignaturas con la menor puntuación en el examen final.
     */
    public void listarExamenesFinalesConMenorPuntuacion() {
        if (asignaturas.getCabeza() == null) {
            System.out.println("\tNo hay asignaturas registradas.");
            return;
        }

        // Variables para almacenar la menor puntuación y las asignaturas
        double menorPuntuacion = Double.MAX_VALUE;
        ListaEnlazada<Asignatura> asignaturasConMenorPuntuacion = new ListaEnlazada<>();

        NodoLEG<Asignatura> nodo = asignaturas.getCabeza();
        while (nodo != null) {
            Asignatura asignatura = nodo.getDato();
            double puntuacionExamenFinal = obtenerPuntuacionExamenFinal(asignatura.getTareas());

            // Comparar con la menor puntuación actual
            if (puntuacionExamenFinal != -1) {
                if (puntuacionExamenFinal < menorPuntuacion) {
                    menorPuntuacion = puntuacionExamenFinal;
                    asignaturasConMenorPuntuacion = new ListaEnlazada<>();
                    asignaturasConMenorPuntuacion.agregar(asignatura);
                } else if (puntuacionExamenFinal == menorPuntuacion) {
                    asignaturasConMenorPuntuacion.agregar(asignatura);
                }
            }

            nodo = nodo.getSiguiente();
        }

        // Mostrar las asignaturas con la menor puntuación en el examen final
        System.out.println("\n\t\tASIGNATURAS CON MENOR PUNTUACIÓN EN EXAMEN FINAL");
        System.out.println("\t\t-----------------------------------------------");

        if (menorPuntuacion == Double.MAX_VALUE) {
            System.out.println("\tNo hay exámenes finales registrados.");
        } else {
            System.out.println("\tMenor puntuación asignada:  " + menorPuntuacion + " puntos ");

            System.out.println("\n\tCódigo                    Asignatura    ");
            System.out.println("\t-----------------------------------------------");

            NodoLEG<Asignatura> nodoMenor = asignaturasConMenorPuntuacion.getCabeza();
            while (nodoMenor != null) {
                Asignatura asignatura = nodoMenor.getDato();
                System.out.printf("\t%-8s  %-12s \n",
                        asignatura.getCodigo(),
                        asignatura.getNombre());
                nodoMenor = nodoMenor.getSiguiente();
            }
        }

        System.out.println("\n\tPulse <Intro> para continuar...");
        scanner.nextLine();
    }

    /**
     * Obtiene la puntuación del examen final de una lista de tareas.
     *
     * @param tareas la lista de tareas
     * @return la puntuación del examen final o -1 si no hay examen final
     */
    private double obtenerPuntuacionExamenFinal(ListaEnlazada<Tarea> tareas) {
        NodoLEG<Tarea> nodo = tareas.getCabeza();
        while (nodo != null) {
            Tarea tarea = nodo.getDato();
            if (tarea.getTipo().equalsIgnoreCase("Examen Final")) {
                return tarea.getPuntuacion();
            }
            nodo = nodo.getSiguiente();
        }
        return -1;
    }


}