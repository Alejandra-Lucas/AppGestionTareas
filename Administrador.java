// Clase Administrador que hereda de Usuario
public class Administrador extends Usuario {

    public Administrador(int id, String nombre, String nickname, String correo, String password){
      super(id, nombre, nickname, correo, password);
    }

    @Override
    public void mostrarMenu() {
      System.out.println("=== MENÚ ADMINISTRADOR ===");
      System.out.println("1. Crear nuevo usuario");
      System.out.println("2. Crear tarea");
      System.out.println("3. Ver todas las tareas");
      System.out.println("4. Actualizar tareas");
      System.out.println("5. Eliminar tareas");
      System.out.println("6. Filtrar tareas");
      System.out.println("7. Cerrar sesión");
    }

    public void crearUsuario() {
    }

    public void crearTarea() {
    }

    public void verTodasTareas() {
    }
  }   
