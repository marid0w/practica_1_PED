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

            if (respuesta.equals("S")) {
                // Solicitar nuevos datos
                System.out.println("Introduce el nuevo nombre de la asignatura: ");
                String nuevoNombre = scanner.nextLine();
                System.out.println("Introduce el nuevo profesor de la asignatura: ");
                String nuevoProfesor = scanner.nextLine();

                // Actualizar los datos de la asignatura
                asignatura.setNombre(nuevoNombre);
                asignatura.setProfesor(nuevoProfesor);
                System.out.println("Asignatura modificada correctamente.");
            } else {
                System.out.println("Proceso de modificación abortado.");
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



}
