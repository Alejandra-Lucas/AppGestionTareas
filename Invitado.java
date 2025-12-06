// Classe que representa un usuario invitado que hereda de Usuario
public class Invitado extends Usuario {

    public Invitado(int id, String nombre, String nickname, String correo, String password) {
      super(id, nombre, nickname, correo, password);
        }

    @Override
    public void mostrarMenu() {
      System.out.println("===MENÚ INVITADO");
      System.out.println("1. Ver todas las tareas");
      System.out.println("2. Filtrar las tareas por estado");
      System.out.println("3. Filtrar tareas por usuarios");
      System.out.println("4. Cerrar sesión");
    }

    public void verTareas(){

    }
  }
