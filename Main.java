import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("\n====================================");
        System.out.println("   SISTEMA DE GESTIÓN DE TAREAS   ");
        System.out.println("====================================\n");
        
        // Inicializar gestores
        GestorUsuarios gestorUsuarios = new GestorUsuarios();
        GestorTareas gestorTareas = new GestorTareas();
        
        System.out.println("[✓] Sistema inicializado");
        
        // Crear MenuManager con los gestores
        MenuManager menu = new MenuManager(gestorUsuarios, gestorTareas);
        
        System.out.println("\nPresiona ENTER para comenzar...");
        scanner.nextLine();
        
    
        // Iniciar el sistema de menús
        menu.inicio();
        
        // guardamos los datos
        System.out.println("\nGuardando datos antes de salir...");
        try {
            Persistencia.guardarUsuarios(gestorUsuarios.getUsuarios());
            Persistencia.guardarTareas(gestorTareas.getTareas());
            System.out.println("[✓] Datos guardados exitosamente");
        } catch (Exception e) {
            System.err.println("Error al guardar datos: " + e.getMessage());
        }
        
        // Mensaje de despedida
        System.out.println("\n====================================");
        System.out.println("   ¡Gracias por usar el sistema!   ");
        System.out.println("====================================\n");
        
        scanner.close();
    }
}

