import java.util.List;

public class SistemaLogin {

    // Método para verificar las credenciales de inicio de sesión
    public Usuario iniciarSesion(String credencial, String password, GestorUsuarios gestorUsuarios) throws Exception {
        List<Usuario> usuarios = gestorUsuarios.getUsuarios();
        for (Usuario user : usuarios) {

            // Verificar correo o nickname
            boolean credencialValida = user.getCorreo().equals(credencial) || user.getNickname().equals(credencial);
            if (credencialValida) { 
                if (user.getPassword().equals(password)) {
                    return user; // Credenciales correctas
            } else{
                throw new IncorrectPasswordException("La contraseña es incorrecta.");
            }
            }
        }
        throw new UserNotFoundException("El usuario no fue encontrado.");
    }
}
