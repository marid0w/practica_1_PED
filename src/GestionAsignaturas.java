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
                System.out.println("Introduce el nombre de la asignatura: ");
                String nombre = scanner.nextLine();
                System.out.println("Introduce el código de la asignatura: ");
                String codigo = scanner.nextLine();
                System.out.println("Introduce el profesor de la asignatura: ");
                String profesor = scanner.nextLine();
                Asignatura asignatura = new Asignatura(nombre, codigo, profesor);
                asignaturas.agregar(asignatura);
                guardarAsignaturasEnFichero();
        }

        public void guardarAsignaturasEnFichero() {
                String nombreFichero = "asignaturas.txt"; // Nombre fijo del fichero
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreFichero, true))) { // Append mode
                        NodoLEG<Asignatura> nodo = asignaturas.getCabeza(); // Obtener la cabeza de la lista
                        while (nodo != null) {
                                // Escribir la asignatura en el fichero
                                writer.write(nodo.getDato().toString());
                                writer.newLine(); // Nueva línea para la siguiente asignatura
                                nodo = nodo.getSiguiente(); // Avanzar al siguiente nodo
                        }
                        System.out.println("Asignaturas guardadas en el fichero: " + nombreFichero);
                } catch (IOException e) {
                        System.err.println("Error al escribir en el fichero: " + e.getMessage());
                }
        }
        public void bajaAsignatura() {
        }
}