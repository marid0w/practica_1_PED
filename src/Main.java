public class Main {
            public static void main(String[] args) {
                // Crear una asignatura
                Asignatura asignatura = new Asignatura("Matemáticas", "MAT101", "Dr. Smith");

                // Crear algunas tareas
                Tarea tarea1 = new Tarea("Tarea 1", 10, "Ejercicio");
                Tarea tarea2 = new Tarea("Tarea 2", 20, "Proyecto");
                Tarea tarea3 = new Tarea("Tarea 3", 15, "Examen");

                // Añadir tareas a la asignatura
                asignatura.añadirTarea(tarea1);
                asignatura.añadirTarea(tarea2);
                asignatura.añadirTarea(tarea3);

                // Escribir las tareas en un fichero de texto
                asignatura.escribirTareasEnFichero("tareas.txt");

                // Mostrar las tareas de la asignatura
                System.out.println("Tareas de la asignatura " + asignatura.getNombre() + ":");
                ListaEnlazada<Tarea> tareas = asignatura.getTareas();
                NodoLEG<Tarea> nodo = tareas.getCabeza();
                while (nodo != null) {
                    System.out.println(nodo.getDato());
                    nodo = nodo.getSiguiente();
                }

                // Eliminar una tarea
                asignatura.eliminarTarea(tarea2);

                // Escribir las tareas en un fichero de texto después de eliminar una tarea
                asignatura.escribirTareasEnFichero("tareas_actualizadas.txt");

                // Mostrar las tareas de la asignatura después de eliminar una tarea
                System.out.println("\nTareas de la asignatura después de eliminar una tarea:");
                nodo = tareas.getCabeza();
                while (nodo != null) {
                    System.out.println(nodo.getDato());
                    nodo = nodo.getSiguiente();
                }
            }
        }