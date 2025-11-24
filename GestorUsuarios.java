import java.util.ArrayList;
import java.util.List;

public class GestorUsuarios {
  private List<Usuario> usuarios;

  public GestorUsuarios() {
    usuarios = new ArrayList<>();
    cargarUsuariosPrueba();
  }

  private void cargarUsuariosPrueba() {
    usuarios.add(new Administrador("1", "Admin principal", "admin", "admin@correo.com", "123"));
    usuarios.add(new Desarrollador("2", "Ana Dev", "anadev", "ana@correo.com", "123"));
    usuarios.add(new Invitado("3", "Invitado Test", "invitado", "invitado@correo.com", "123"));
  }

  public Usuario autenticar(String correoNickname, String password) {
    for (Usuario usuario : usuarios) {
      if ((usuario.getCorreo().equals(correoNickname) || usuario.getNickname().equals(correoNickname))
          && usuario.getPassword().equals(password)) {
        return usuario;
      }
    }
    return null;
  } 

  public boolean crearUsuario(Usuario nuevoUsuario) {
    usuarios.add(nuevoUsuario);
    return true;
  }

  public List<Usuario> getUsuarios() {
    return usuarios;
  }

  public Usuario getUsuarioPorId(int id) {
    for (Usuario usuario : usuarios) {
      if (usuario.getId() == id) {
        return usuario;
      }
    }
    return null;
  }
  public Usuario getUsuarioPorNickname(String nickname) {
    for (Usuario usuario : usuarios) {
      if (usuario.getNickname().equals(nickname)) {
        return usuario;
      }
    }
    return null;
  }
}
