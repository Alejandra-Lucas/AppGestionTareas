/* Excepción que se muestra para errores al crear un usuario
 */
public class ErrorCrearUsuarioException extends Exception {
    // Constructor por defecto
    public ErrorCrearUsuarioException() {
        super();
    }
    // Constructor con mensaje detallado
    public ErrorCrearUsuarioException(String message) {
        super(message);
    }
    
}