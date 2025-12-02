/* Excepción que se muestra cuando la contraseña es incorrecta
 */
public class IncorrectPasswordException extends Exception {
    // Constructor por defecto
    public IncorrectPasswordException() {
        super();
    }
    // Constructor con mensaje detallado
    public IncorrectPasswordException(String message) {
        super(message);
    }
    
}
