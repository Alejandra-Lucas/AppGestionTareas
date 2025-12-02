/* Excepción que se muestra para errores al crear una tarea
 */
public class ErrorCrearTareaException extends Exception {
    // Constructor por defecto
    public ErrorCrearTareaException() {
        super();
    }
    // Constructor con mensaje detallado
    public ErrorCrearTareaException(String message) {
        super(message);
    }
    
}
