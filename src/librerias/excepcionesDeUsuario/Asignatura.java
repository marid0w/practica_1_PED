package librerias.excepcionesDeUsuario;

import librerias.estructurasDeDatos.lineales.ListaEnlazada;
import librerias.estructurasDeDatos.lineales.NodoLEG;

import java.io.Serializable;
import java.util.Scanner;

/**
 * Clase que representa una asignatura con tareas.
 */
public class Asignatura implements Serializable, Comparable<Asignatura> {
    private static final long serialVersionUID = 1L;
    private String nombre;
    private String codigo;
    private String profesor;
    private ListaEnlazada<Tarea> tareas;
    private transient Scanner scanner;

    /**
     * Constructor que inicializa una asignatura con su nombre, código y profesor.
     *
     * @param nombre   el nombre de la asignatura
     * @param codigo   el código de la asignatura
     * @param profesor el nombre del profesor de la asignatura
     */
    public Asignatura(String nombre, String codigo, String profesor) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.profesor = profesor;
        this.tareas = new ListaEnlazada<>();
        this.scanner = new Scanner(System.in);
    }

    /**
     * Añade una tarea a la lista de tareas de la asignatura.
     *
     * @param tarea la tarea a añadir
     */
    public void aniadirTarea(Tarea tarea) {
        tareas.agregar(tarea);
    }

    /**
     * Obtiene el código de la asignatura.
     *
     * @return el código de la asignatura
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Obtiene el nombre de la asignatura.
     *
     * @return el nombre de la asignatura
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene el nombre del profesor de la asignatura.
     *
     * @return el nombre del profesor
     */
    public String getProfesor() {
        return profesor;
    }

    /**
     * Establece un nuevo nombre para la asignatura.
     *
     * @param nuevoNombre el nuevo nombre de la asignatura
     */
    public void setNombre(String nuevoNombre) {
        this.nombre = nuevoNombre;
    }

    /**
     * Establece un nuevo nombre para el profesor de la asignatura.
     *
     * @param nuevoProfesor el nuevo nombre del profesor
     */
    public void setProfesor(String nuevoProfesor) {
        this.profesor = nuevoProfesor;
    }

    /**
     * Obtiene la lista de tareas de la asignatura.
     *
     * @return la lista de tareas
     */
    public ListaEnlazada<Tarea> getTareas() {
        return tareas;
    }

    /**
     * Establece una nueva lista de tareas para la asignatura.
     *
     * @param tareas la nueva lista de tareas
     */
    public void setTareas(ListaEnlazada<Tarea> tareas) {
        this.tareas = tareas;
    }

    /**
     * Elimina una tarea de la lista de tareas de la asignatura.
     *
     * @param tarea la tarea a eliminar
     */
    public void eliminarTarea(Tarea tarea) {
        tareas.eliminar(tarea);
    }

    /**
     * Devuelve una representación en cadena de la asignatura.
     *
     * @return una cadena con el código, nombre y profesor de la asignatura
     */
    @Override
    public String toString() {
        return "Código: " + codigo + ", Nombre: " + nombre + ", Profesor: " + profesor;
    }

    /**
     * Obtiene la suma de las puntuaciones de todas las tareas de la asignatura.
     *
     * @return la suma de las puntuaciones
     */
    public double obtenerSumaPuntuacionesTareas() {
        double suma = 0;
        NodoLEG<Tarea> nodo = tareas.getCabeza();
        while (nodo != null) {
            suma += nodo.getDato().getPuntuacion();
            nodo = nodo.getSiguiente();
        }
        return suma;
    }

    /**
     * Agrega una tarea a la lista de tareas de la asignatura, verificando que no se excedan las restricciones.
     *
     * @param tarea la tarea a agregar
     * @return true si la tarea se agregó correctamente, false en caso contrario
     */
    public boolean agregarTarea(Tarea tarea) {
        // Verificar si ya existe un examen final
        if (tarea.getTipo().equalsIgnoreCase("\tExamen Final")) {
            NodoLEG<Tarea> nodo = tareas.getCabeza();
            while (nodo != null) {
                if (nodo.getDato().getTipo().equalsIgnoreCase("\tExamen Final")) {
                    System.out.println("\tYa existe un examen final");
                    return false;
                }
                nodo = nodo.getSiguiente();
            }
        }

        // Verificar si la suma de las puntuaciones no supera 10
        if (obtenerSumaPuntuacionesTareas() + tarea.getPuntuacion() <= 10) {
            aniadirTarea(tarea);
            return true;
        } else {
            System.out.println("\n\tNo se puede agregar la tarea, la suma de las puntuaciones supera 10\n");
            return false;
        }
    }

    /**
     * Permite agregar tareas a la asignatura mediante la entrada del usuario.
     */
    public void agregarTareasAsignatura() {
        if (scanner == null) {
            scanner = new Scanner(System.in);
        }

        boolean continuar = true;
        while (continuar) {
            System.out.println("\n\tIntroduce el nombre de la tarea: ");
            String nombreTarea = scanner.nextLine().trim();

            System.out.println("\tIntroduce la puntuación de la tarea (0-10): ");
            int puntuacion;
            while (true) {
                try {
                    puntuacion = Integer.parseInt(scanner.nextLine().trim());
                    if (puntuacion < 0 || puntuacion > 10) {
                        throw new NumberFormatException();
                    }
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("\n\tError: Introduzca un valor válido entre 0 y 10.");
                }
            }

            System.out.println("\tIntroduce el tipo de tarea (practica, parcial, Examen Final): ");
            String tipo;
            while (true) {
                tipo = scanner.nextLine().trim().toLowerCase();
                if (tipo.equals("practica") || tipo.equals("parcial") || tipo.equals("examen final")) {
                    break;
                }
                System.out.println("\n\tError: Tipo de tarea inválido. Use 'practica', 'parcial' o 'Examen Final'.");
            }

            Tarea nuevaTarea = new Tarea(nombreTarea, puntuacion, tipo, this.codigo);
            if (agregarTarea(nuevaTarea)) {
                System.out.println("\tTarea añadida correctamente.");
            }

            System.out.println("\n\t¿Desea añadir otra tarea? (S/N)");
            String respuesta = scanner.nextLine().trim().toUpperCase();
            if (!respuesta.equals("S")) {
                continuar = false;
            }
        }
    }

    /**
     * Compara esta asignatura con otra asignatura por su código.
     *
     * @param asignatura la asignatura a comparar
     * @return un valor negativo, cero o positivo si esta asignatura es menor, igual o mayor que la asignatura especificada
     */
    @Override
    public int compareTo(Asignatura asignatura) {
        return this.codigo.compareTo(asignatura.getCodigo());
    }
}