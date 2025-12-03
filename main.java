/**
 * Punto de entrada del sistema.
 * - Carga usuarios y tareas desde archivos (la serialización)
 * - Ejecuta login y menú
 * - Guarda datos al salir
 */
public class Main {

    public static void main(String[] args) {

        // Creamos los gestores
        GestorUsuarios gestorUsuarios = new GestorUsuarios();
        GestorTareas gestorTareas = new GestorTareas();

        // Cargar desde la persistencia 
        try {
            java.util.List<Usuario> usuarios = Persistencia.cargarUsuarios();
            if (usuarios != null && !usuarios.isEmpty()) {
                gestorUsuarios.setUsuarios(usuarios);
            }
        } catch (Exception e) {
            System.err.println("Error al cargar usuarios: " + e.getMessage());
        }

        try {
            java.util.List<Tarea> tareas = Persistencia.cargarTareas();
            if (tareas != null && !tareas.isEmpty()) {
                gestorTareas.setTareas(tareas);
            }
        } catch (Exception e) {
            System.err.println("Error al cargar tareas: " + e.getMessage());
        }

        InterfazConsola.titulo("Sistema de Gestión de Tareas");

        SistemaLogin login = new SistemaLogin(gestorUsuarios);
        MenuManager menu = new MenuManager(gestorUsuarios, gestorTareas);

        Usuario usuario = null;
        try {
            usuario = login.iniciarSesion(); 
        } catch (Exception e) {
            System.err.println("Error en login: " + e.getMessage());
        }

        if (usuario == null) {
            System.out.println("No se inició sesión. Saliendo.");
        } else {
            menu.mostrarMenuSegunRol(usuario);
        }

        // guardamos los datos antes de salir
        try {
            Persistencia.guardarUsuarios(gestorUsuarios.getUsuarios());
            Persistencia.guardarTareas(gestorTareas.getTareas());
        } catch (Exception e) {
            System.err.println("Error guardando datos: " + e.getMessage());
        }

        System.out.println("\nDatos guardados. Fin del programa.");
    }
}
