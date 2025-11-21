import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class MenuManager {
    private Scanner scanner;
    private SistemaLogin sistemaLogin;
    private GestorUsuarios gestorUsuarios;
    private GestorTareas gestorTareas;
    private Usuario usuarioActual;
    public MenuManager() {
        this.scanner = new Scanner(System.in);
        this.sistemaLogin = new SistemaLogin();
        this.gestorUsuarios = new GestorUsuarios(); 
        this.gestorTareas = new GestorTareas();
    }
}