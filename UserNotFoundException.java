/* Excepción que se muestra cuando no se encuentra un usuario
 */
public class UserNotFoundException extends Exception {
    // Constructor por defecto
    public UserNotFoundException() {
        super();
    }
    // Constructor con mensaje detallado
    public UserNotFoundException(String message) {
        super(message);
    }
    
}
