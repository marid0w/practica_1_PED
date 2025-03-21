import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class GestionAsignaturas {
        private ListaEnlazada<Asignatura> asignaturas;
        private Scanner scanner;

        public GestionAsignaturas() {
                this.asignaturas = new ListaEnlazada<>();
                this.scanner = new Scanner(System.in);
        }

        public void altaAsignatura() {
                boolean continuar = true;

                while (continuar) {
                        System.out.println("Introduce el código de la asignatura: ");
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
                                guardarAsignaturasEnFichero();
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



        public void guardarAsignaturasEnFichero() {
                String nombreFichero = "asignaturas.txt";
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreFichero))) { // Sin append
                        NodoLEG<Asignatura> nodo = asignaturas.getCabeza();
                        while (nodo != null) {
                                // Escribir la asignatura en el fichero
                                writer.write("Asignatura: " + nodo.getDato().getNombre() + ", Código: " + nodo.getDato().getCodigo());
                                writer.newLine();

                                // Escribir las tareas de la asignatura
                                NodoLEG<Tarea> nodoTarea = nodo.getDato().getTareas().getCabeza();
                                while (nodoTarea != null) {
                                        writer.write("  Tarea: " + nodoTarea.getDato().getNombre() + ", Puntuación: " + nodoTarea.getDato().getPuntuacion());
                                        writer.newLine();
                                        nodoTarea = nodoTarea.getSiguiente();
                                }

                                nodo = nodo.getSiguiente();
                        }
                        System.out.println("Asignaturas y tareas guardadas en el fichero: " + nombreFichero);
                } catch (IOException e) {
                        System.err.println("Error al escribir en el fichero: " + e.getMessage());
                }
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


        public  void  modificacionAsignaturas(){


        }







}
