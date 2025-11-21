import java.util.List;
public class SistemaLogin {
    // Método para verificar las credenciales de inicio de sesión
    public Usuario iniciarSesion(String correo, String password, List<Usuario> usuarios) throws Exception {
        for (Usuario user : usuarios) {
            boolean credencialValida = user.getCorreo().equals(correo) && user.getPassword().equals(password);
            if (credencialValida) { 
                return user; // Credenciales válidas
            } throw new Exception("Contraseña incorrecta.");
        }
        throw new Exception("Correo no encontrado.");
        
    }
}
