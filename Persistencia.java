mport java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Persistencia {

    private static final String ARCHIVO_USUARIOS = "usuarios.dat";
    private static final String ARCHIVO_TAREAS = "tareas.dat";

    // Guardar la lista de usuarios 
    public static void guardarUsuarios(List<Usuario> usuarios) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(ARCHIVO_USUARIOS))) {
            out.writeObject(new ArrayList<>(usuarios)); // serializamos una copia
            System.out.println("[✓] Usuarios guardados en " + ARCHIVO_USUARIOS);
        } catch (IOException e) {
            System.err.println("Error guardando usuarios: " + e.getMessage());
        }
    }

    // Devuelve la lista de usuarios cargada o una lista vacía si no existe
    @SuppressWarnings("unchecked")
    public static List<Usuario> cargarUsuarios() {
        File f = new File(ARCHIVO_USUARIOS);
        if (!f.exists()) {
            System.out.println("[i] No se encontró " + ARCHIVO_USUARIOS + " — se usará lista vacía.");
            return new ArrayList<>();
        }
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(f))) {
            Object obj = in.readObject();
            if (obj instanceof List) {
                System.out.println("[✓] Usuarios cargados desde " + ARCHIVO_USUARIOS);
                return (List<Usuario>) obj;
            } else {
                System.err.println("Formato inválido en " + ARCHIVO_USUARIOS);
            }
        } catch (Exception e) {
            System.err.println("Error cargando usuarios: " + e.getMessage());
        }
        return new ArrayList<>();
    }

    // Guardamos la lista de tareas 
    public static void guardarTareas(List<Tarea> tareas) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(ARCHIVO_TAREAS))) {
            out.writeObject(new ArrayList<>(tareas));
            System.out.println("[✓] Tareas guardadas en " + ARCHIVO_TAREAS);
        } catch (IOException e) {
            System.err.println("Error guardando tareas: " + e.getMessage());
        }
    }

    // Devolver la lista de tareas cargada o una lista vacía 
    @SuppressWarnings("unchecked")
    public static List<Tarea> cargarTareas() {
        File f = new File(ARCHIVO_TAREAS);
        if (!f.exists()) {
            System.out.println("[i] No se encontró " + ARCHIVO_TAREAS + " — se usará lista vacía.");
            return new ArrayList<>();
        }
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(f))) {
            Object obj = in.readObject();
            if (obj instanceof List) {
                System.out.println("[✓] Tareas cargadas desde " + ARCHIVO_TAREAS);
                return (List<Tarea>) obj;
            } else {
                System.err.println("Formato inválido en " + ARCHIVO_TAREAS);
            }
        } catch (Exception e) {
            System.err.println("Error cargando tareas: " + e.getMessage());
        }
        return new ArrayList<>();
    }
}
