import java.io.Serializable;
import java.util.Scanner;

public class Asignatura implements Serializable, Comparable<Asignatura> {
    private static final long serialVersionUID = 1L;
    private String nombre;
    private String codigo;
    private String profesor;
    private ListaEnlazada<Tarea> tareas;
    private transient Scanner scanner;

    public Asignatura(String nombre, String codigo, String profesor) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.profesor = profesor;
        this.tareas = new ListaEnlazada<>();
        this.scanner = new Scanner(System.in);
    }

    public void aniadirTarea(Tarea tarea) {
        tareas.agregar(tarea);
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getProfesor() {
        return profesor;
    }

    public void setNombre(String nuevoNombre) {
        this.nombre = nuevoNombre;
    }

    public void setProfesor(String nuevoProfesor) {
        this.profesor = nuevoProfesor;
    }

    public ListaEnlazada<Tarea> getTareas() {
        return tareas;
    }

    public void setTareas(ListaEnlazada<Tarea> tareas) {
        this.tareas = tareas;
    }

    public void eliminarTarea(Tarea tarea) {
        tareas.eliminar(tarea);
    }

    @Override
    public String toString() {
        return "Código: " + codigo + ", Nombre: " + nombre + ", Profesor: " + profesor;
    }

    public double obtenerSumaPuntuacionesTareas() {
        double suma = 0;
        NodoLEG<Tarea> nodo = tareas.getCabeza();
        while (nodo != null) {
            suma += nodo.getDato().getPuntuacion();
            nodo = nodo.getSiguiente();
        }
        return suma;
    }

    public boolean agregarTarea(Tarea tarea) {
        // Verificar si ya existe un examen final
        if (tarea.getTipo().equalsIgnoreCase("Examen Final")) {
            NodoLEG<Tarea> nodo = tareas.getCabeza();
            while (nodo != null) {
                if (nodo.getDato().getTipo().equalsIgnoreCase("Examen Final")) {
                    System.out.println("Ya existe un examen final");
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
            System.out.println("No se puede agregar la tarea, la suma de las puntuaciones supera 10");
            return false;
        }
    }

    public void agregarTareasAsignatura() {
        if (scanner == null) {
            scanner = new Scanner(System.in);
        }

        boolean continuar = true;
        while (continuar) {
            System.out.println("Introduce el nombre de la tarea: ");
            String nombreTarea = scanner.nextLine().trim();

            System.out.println("Introduce la puntuación de la tarea (0-10): ");
            int puntuacion;
            while (true) {
                try {
                    puntuacion = Integer.parseInt(scanner.nextLine().trim());
                    if (puntuacion < 0 || puntuacion > 10) {
                        throw new NumberFormatException();
                    }
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Error: Introduzca un valor válido entre 0 y 10.");
                }
            }

            System.out.println("Introduce el tipo de tarea (practica, parcial, Examen Final): ");
            String tipo;
            while (true) {
                tipo = scanner.nextLine().trim().toLowerCase();
                if (tipo.equals("practica") || tipo.equals("parcial") || tipo.equals("examen final")) {
                    break;
                }
                System.out.println("Error: Tipo de tarea inválido. Use 'practica', 'parcial' o 'Examen Final'.");
            }

            Tarea nuevaTarea = new Tarea(nombreTarea, puntuacion, tipo, this.codigo);
            if (agregarTarea(nuevaTarea)) {
                System.out.println("Tarea añadida correctamente.");
            }

            System.out.println("¿Desea añadir otra tarea? (S/N)");
            String respuesta = scanner.nextLine().trim().toUpperCase();
            if (!respuesta.equals("S")) {
                continuar = false;
            }
        }
    }

    @Override
    public int compareTo(Asignatura asignatura) {
        return this.codigo.compareTo(asignatura.getCodigo());
    }
}