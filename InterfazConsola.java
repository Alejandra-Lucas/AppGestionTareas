import java.util.Scanner;

//creamos la interfaz de nuestra app
public class InterfazConsola {

    private static final Scanner sc = new Scanner(System.in);
     
    //mostramos el titulo
    public static void titulo(String t) {
        limpiarLinea();
        System.out.println();
        System.out.println("==============================");
        System.out.println("  " + t);
        System.out.println("==============================");
    }

    public static void limpiarLinea() {
        System.out.println();
    }

    public static void pausa() {
        System.out.println("\nPresiona ENTER para continuar...");
        sc.nextLine();
    }

    public static String pedirTexto(String mensaje) {
        System.out.print(mensaje + ": ");
        return sc.nextLine().trim();
    }

    public static int pedirEntero(String mensaje, int defecto) {
        System.out.print(mensaje + " (enter para " + defecto + "): ");
        String l = sc.nextLine().trim();
        if (l.isEmpty()) return defecto;
        try {
            return Integer.parseInt(l);
        } catch (NumberFormatException e) {
            System.out.println("Entrada no válida. Usando " + defecto);
            return defecto;
        }
    }

    public static void mostrarLinea() {
        System.out.println("--------------------------------");
    }

    public static void mostrarMensaje(String msg) {
        System.out.println(msg);
    }

    // Mostramos la lista de tareas 
    public static void mostrarTareas(java.util.List<Tarea> tareas) {
        System.out.println("\n--- Lista de Tareas ---");
        if (tareas == null || tareas.isEmpty()) {
            System.out.println("(no hay tareas)");
            return;
        }
        int i = 1;
        for (Tarea t : tareas) {
            System.out.println(i + ") " + t);
            i++;
        }
    }

    // Mostrar lista de usuarios
    public static void mostrarUsuarios(java.util.List<Usuario> usuarios) {
        System.out.println("\n--- Lista de Usuarios ---");
        if (usuarios == null || usuarios.isEmpty()) {
            System.out.println("(no hay usuarios)");
            return;
        }
        int i = 1;
        for (Usuario u : usuarios) {
            System.out.println(i + ") " + u);
            i++;
        }
    }
}

