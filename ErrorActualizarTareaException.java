/* Excepción que se muestra para errores al actualizar una tarea
 */
public class ErrorActualizarTareaException extends Exception {
    // Constructor por defecto
    public ErrorActualizarTareaException() {
        super();
    }
    // Constructor con mensaje detallado
    public ErrorActualizarTareaException(String message) {
        super(message);
    }
    
}
