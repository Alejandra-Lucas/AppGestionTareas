// Clase Desarrollador que hereda de Usuario
public class Desarrollador extends Usuario { Clase desarrollador que extiende a usuario.

    public Desarrollador(int id, String nombre, String nickname, String correo, String password)
    {
      super(id, nombre, nickname, correo, password);
    }

    @Override
    public void mostrarMenu() {
      System.out.println("===MENÚ DESARROLLADOR ===");
      System.out.println("1. Crear tarea para mí");
      System.out.println("2. Ver mis tareas");
      System.out.println("3. Filtrar mis tareas por estado");
      System.out.println("4. Actualizar mis tareas");
      System.out.println("5. Cerrar sesión");
    }

    public void crearTareaPropia() {
      
    }

    public void verMisTareas() {
    }
  }
   
