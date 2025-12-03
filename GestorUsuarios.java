import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class GestorUsuarios {
  private List<Usuario> usuarios;
  private final String ARCHIVO_USUARIOS = "usuarios.dat";

  public GestorUsuarios() {
    usuarios = new ArrayList<>();
    cargarUsuarios();
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

  public void crearUsuario(Usuario nuevoUsuario) throws ErrorCrearUsuarioException {
    // Método para crear un nuevo usuario
    if (getUsuarioPorNickname(nuevoUsuario.getNickname()) != null) {
      throw new ErrorCrearUsuarioException("El nickname ya está en uso.");
    }

    // Creación de ID
    int nuevoId = 1;
    if (!usuarios.isEmpty()) {
      nuevoId = usuarios.get(usuarios.size() - 1).getId() + 1;
    }
    nuevoUsuario.setId(nuevoId);
    
    // Agregar usuario a la lista y guardar
    usuarios.add(nuevoUsuario);
    guardarUsuarios();
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

  private void guardarUsuarios() {
    // Guardar usuarios en archivo
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO_USUARIOS))) {
      oos.writeObject(usuarios);
    } catch (IOException e) {
      System.out.println("Error al guardar usuarios: " + e.getMessage());
    }
  }

  @SuppressWarnings("unchecked")
  private void cargarUsuarios() {
    // Cargar usuarios desde archivo
    File archivo = new File(ARCHIVO_USUARIOS);
    
    if (archivo.exists()) {
      try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
        usuarios = (List<Usuario>) ois.readObject();
      } catch (IOException | ClassNotFoundException e) {
        System.out.println("Error al cargar usuarios: " + e.getMessage());
        usuarios = new ArrayList<>();
      }
    } else {
      cargarUsuariosPrueba();
    }
  }

  private void cargarUsuariosPrueba() {
    // Usuarios de prueba si no existe el archivo
    usuarios.add(new Administrador(1, "Admin Principal", "admin", "admin@correo.com", "123"));
    usuarios.add(new Desarrollador(2, "Ana Dev", "anadev", "ana@correo.com", "123"));
    usuarios.add(new Invitado(3, "Invitado Test", "invitado", "invitado@correo.com", "123"));
    guardarUsuarios();
  }

  public void setUsuarios(List<Usuario> lista) {
  }
}
