// Clase Administrador que hereda de Usuario Representa a un usuario Administrador, con permisos para gestionar usuarios y tareas.
public class Administrador extends Usuario {

    public Administrador(int id, String nombre, String nickname, String correo, String password){
      super(id, nombre, nickname, correo, password);
    }

    @Override
    public void mostrarMenu() {  Muestra en pantalla las opciones disponibles.
    
      System.out.println("=== MENÚ ADMINISTRADOR ==="); presenta el menú principal para usuarios.
      System.out.println("1. Crear nuevo usuario");
      System.out.println("2. Crear tarea");
      System.out.println("3. Ver todas las tareas");
      System.out.println("4. Actualizar tareas");
      System.out.println("5. Eliminar tareas");
      System.out.println("6. Filtrar tareas"); Linea de menú que habilita la función de filtrado de tareas
      System.out.println("7. Cerrar sesión");
    }

    public void crearUsuario() {
    }

    public void crearTarea() {
    }

    public void verTodasTareas() {
    }
  }   
