import java.io.*;
import java.util.Scanner;

public class GestionAsignaturas {
        private ListaEnlazada<Asignatura> asignaturas;
        private Scanner scanner;


        public GestionAsignaturas() {
                this.asignaturas = new ListaEnlazada<>();
                this.scanner = new Scanner(System.in);
                cargarAsignaturas();
                cargarTareas();
        }

        public void guardarAsignaturas() {
            String nombreFichero = "asignaturas.dat"; // Nombre fijo del fichero
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nombreFichero))) {
                NodoLEG<Asignatura> nodo = asignaturas.getCabeza(); // Obtener la cabeza de la lista
                while (nodo != null) {
                    oos.writeObject(nodo.getDato()); // Escribir la asignatura en el fichero
                    nodo = nodo.getSiguiente(); // Avanzar al siguiente nodo
                }
                System.out.println("Asignaturas guardadas correctamente.");
            } catch (IOException e) {
                System.err.println("Error al guardar las asignaturas: " + e.getMessage());
            }
        }

        public void cargarAsignaturas() {
            String filePath = "asignaturas.dat"; // Ruta fija del fichero
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
                asignaturas = new ListaEnlazada<>();
                while (true) {
                    try {
                        Asignatura asignatura = (Asignatura) ois.readObject();
                        asignaturas.agregar(asignatura);
                    } catch (EOFException e) {
                        break; // End of file reached
                    }
                }
                System.out.println("Asignaturas cargadas correctamente.");
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Error al cargar las asignaturas: " + e.getMessage());
            }
        }

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
                System.out.println("Tareas guardadas correctamente.");
            } catch (IOException e) {
                System.err.println("Error al guardar las tareas: " + e.getMessage());
            }
        }

        public void cargarTareas() {
            String filePath = "tareas.dat"; // Ruta fija del fichero
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
                while (true) {
                    try {
                        Tarea tarea = (Tarea) ois.readObject();
                        Asignatura asignatura = buscarAsignaturaPorCodigo(tarea.getCodigoAsignatura());
                        if (asignatura != null) {
                            asignatura.getTareas().agregar(tarea);
                        }
                    } catch (EOFException e) {
                        break; // End of file reached
                    }
                }
                System.out.println("Tareas cargadas correctamente.");
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Error al cargar las tareas: " + e.getMessage());
            }
        }

        public void altaAsignatura() {
                boolean continuar = true;

                while (continuar) {
                        System.out.println("Introduce el código de la asignatura(Ej.: MAT_0001): ");
                        String codigo = scanner.nextLine();

                        // Verificar si el código ya existe en la lista
                        if (buscarAsignaturaPorCodigo(codigo) != null) {
                                System.out.println("Error: este código ya está registrado en la aplicación.");
                        } else {
                                // Si el código es único, solicitar el resto de los datos
                                System.out.println("Introduce el nombre de la asignatura: ");
                                String nombre = scanner.nextLine();
                                System.out.println("Introduce el profesor de la asignatura: ");
                                String profesor = scanner.nextLine();

                                Asignatura asignatura = new Asignatura(nombre, codigo, profesor);
                                asignaturas.agregar(asignatura);
                                System.out.println("Asignatura añadida correctamente.");
                        }

                        // Preguntar si desea añadir otra asignatura
                        System.out.println("¿Desea añadir una nueva asignatura? (S/N)");
                        String respuesta = scanner.nextLine().trim().toUpperCase();
                        if (!respuesta.equals("S")) {
                                continuar = false;
                        }
                }
        }

        public Asignatura buscarAsignaturaPorCodigo(String codigo) {
            if (codigo == null) {
                return null;
            }
            NodoLEG<Asignatura> nodo = asignaturas.getCabeza(); // Obtener la cabeza de la lista

            while (nodo != null) {
                Asignatura asignatura = nodo.getDato();

                // Comparamos los códigos sin espacios extra
                if (asignatura.getCodigo().trim().equalsIgnoreCase(codigo.trim())) {
                    return asignatura; // Se encontró la asignatura
                }

                nodo = nodo.getSiguiente(); // Avanzar al siguiente nodo
            }

            return null; // No se encontró la asignatura
        }


        public void bajaAsignatura() {
                boolean continuar = true;
                mostrarListaAsignaturas();

                while (continuar) {
                        System.out.println("Introduce el código de la asignatura a dar de baja: ");
                        String codigo = scanner.nextLine();

                        Asignatura asignatura = buscarAsignaturaPorCodigo(codigo);

                        if (asignatura == null) {
                                System.out.println("Error: este código no está registrado en la aplicación.");
                        } else {
                                // Mostrar los datos de la asignatura
                                System.out.println("Asignatura encontrada:");
                                System.out.println(asignatura);

                                // Confirmación del usuario
                                System.out.println("¿Desea realmente dar de baja a esta asignatura? (S/N)");
                                String respuesta = scanner.nextLine().trim().toUpperCase();

                                if (respuesta.equals("S")) {
                                        asignaturas.eliminar(asignatura);
                                        System.out.println("Asignatura eliminada correctamente.");
                                } else {
                                        System.out.println("Proceso de baja abortado.");
                                }
                        }

                        // Preguntar si desea dar de baja otra asignatura
                        System.out.println("¿Desea dar de baja otra asignatura? (S/N)");
                        String respuestaContinuar = scanner.nextLine().trim().toUpperCase();
                        if (!respuestaContinuar.equals("S")) {
                                continuar = false;
                        }
                }
                mostrarListaAsignaturas();
        }

        public void mostrarListaAsignaturas() {
                NodoLEG<Asignatura> nodo = asignaturas.getCabeza();
                while (nodo != null) {
                        System.out.println(nodo.getDato());
                        nodo = nodo.getSiguiente();
                }
        }

        public void modificacionAsignaturas() {
                boolean continuar = true;
                mostrarListaAsignaturas();

                while (continuar) {
                        System.out.println("Introduce el código de la asignatura a modificar: ");
                        String codigo = scanner.nextLine();

                        Asignatura asignatura = buscarAsignaturaPorCodigo(codigo);

                        if (asignatura == null) {
                                System.out.println("Error: este código no está registrado en la aplicación.");
                        } else {
                                // Mostrar los datos de la asignatura
                                System.out.println("Asignatura encontrada:");
                                System.out.println(asignatura);

                                // Confirmación del usuario
                                System.out.println("¿Desea realmente modificar los datos de esta asignatura? (S/N)");
                                String respuesta = scanner.nextLine().trim().toUpperCase();

                                while (respuesta.equals("S")) {
                                        System.out.println("Seleccione el campo a modificar:");
                                        System.out.println("1. Nombre");
                                        System.out.println("2. Profesor");
                                        System.out.println("0. Finalizar modificación");
                                        int opcion = Integer.parseInt(scanner.nextLine());

                                        switch (opcion) {
                                                case 1:
                                                        System.out.println("Introduce el nuevo nombre de la asignatura: ");
                                                        String nuevoNombre = scanner.nextLine();
                                                        asignatura.setNombre(nuevoNombre);
                                                        System.out.println("Nombre de la asignatura modificado correctamente.");
                                                        break;
                                                case 2:
                                                        System.out.println("Introduce el nuevo profesor de la asignatura: ");
                                                        String nuevoProfesor = scanner.nextLine();
                                                        asignatura.setProfesor(nuevoProfesor);
                                                        System.out.println("Profesor de la asignatura modificado correctamente.");
                                                        break;
                                                case 0:
                                                        continuar = false;
                                                        System.out.println("Modificación finalizada.");
                                                        break;
                                                default:
                                                        System.out.println("Opción no válida. Intente de nuevo.");
                                                        break;
                                        }
                                        System.out.println("Asignatura modificada correctamente.");
                                }
                        }

                        // Preguntar si desea modificar otra asignatura
                        System.out.println("¿Desea modificar otra asignatura? (S/N)");
                        String respuestaContinuar = scanner.nextLine().trim().toUpperCase();
                        if (!respuestaContinuar.equals("S")) {
                                continuar = false;
                        }
                }
        }

        public void listarAsignaturasPorCodigoAscendente() {
                // Paso 1: Ordenar la lista de asignaturas por código (ascendente)
                ordenarListaPorCodigo();

                // Paso 2: Mostrar las asignaturas ordenadas
                System.out.println("| LISTADO GENERAL DE ASIGNATURAS (Ascendente) |");
                System.out.println("|--------------------------------------------|");
                System.out.println("| Código   | Asignatura          | Profesor/a |");
                System.out.println("|--------------------------------------------|");

                NodoLEG<Asignatura> nodo = asignaturas.getCabeza();
                while (nodo != null) {
                        Asignatura asignatura = nodo.getDato();
                        System.out.printf("| %-8s | %-18s | %-10s |\n",
                                asignatura.getCodigo(),
                                asignatura.getNombre(),
                                asignatura.getProfesor());
                        nodo = nodo.getSiguiente();
                }

                System.out.println("|--------------------------------------------|");
                System.out.println("...... Pulse <Intro> para continuar…");
                scanner.nextLine(); // Esperar a que el usuario pulse Intro
        }

        private void ordenarListaPorCodigo() {
                NodoLEG<Asignatura> actual = asignaturas.getCabeza();

                while (actual != null) {
                        NodoLEG<Asignatura> minimo = actual;
                        NodoLEG<Asignatura> siguiente = actual.getSiguiente();

                        // Encontrar el nodo con el código mínimo en la sublista restante
                        while (siguiente != null) {
                                if (siguiente.getDato().getCodigo().compareTo(minimo.getDato().getCodigo()) < 0) {
                                        minimo = siguiente;
                                }
                                siguiente = siguiente.getSiguiente();
                        }

                        // Intercambiar los datos del nodo actual con el nodo mínimo
                        if (minimo != actual) {
                                Asignatura temp = actual.getDato();
                                actual.setDato(minimo.getDato());
                                minimo.setDato(temp);
                        }

                        actual = actual.getSiguiente();
                }
        }

        public void agregarTareasAAsignatura() {
            mostrarListaAsignaturas();
            System.out.println("Introduce el código de la asignatura a la que deseas agregar tareas: ");
            String codigo = scanner.nextLine();

            Asignatura asignatura = buscarAsignaturaPorCodigo(codigo);

            if (asignatura == null) {
                System.out.println("Error: este código no está registrado en la aplicación.");
            } else {
                System.out.println("Asignatura encontrada:");
                System.out.println(asignatura);
                asignatura.agregarTareasAsignatura();
            }
        }
}