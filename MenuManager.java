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

    // Definimos métodos necesarios para el menú

    private int leerEntero() {
        // Método para leer números enteros
        try{
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1; // Valor inválido
        }
    }

    private LocalDate leerFecha(String mensaje) {
        // Método para leer fechas en formato AAAA-MM-DD
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        while (true) {
            System.out.print(mensaje);
            String entrada = scanner.nextLine();
            try {
                return LocalDate.parse(entrada, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Formato de fecha inválido. Por favor, use AAAA-MM-DD.");
            }
        }
    }

    private void crearTarea(boolean esAdmin){
        // Método para crear una nueva tarea
        try{
            System.out.print("Crear nueva tarea: ");
            System.out.print("Ingrese una descripción de la tarea: ");
            String descripcion = scanner.nextLine();

            Usuario asignado = usuarioActual;
            if (esAdmin){
                System.out.println("Ingrese el nickname del usuario asignado: ");
                System.out.print("Si desea asignarse a sí mismo, deje en blanco y presione Enter ");
                String nickname = scanner.nextLine();
                if (!nickname.isEmpty()){
                    asignado = gestorUsuarios.getUsuarioPorNickname(nickname);
                    if (asignado == null){
                        System.out.println("Usuario no encontrado.");
                        return;
                    }
                }
            }
            // Validar fecha
            LocalDate fechaInicioEst = leerFecha("Fecha estimada de inicio (AAAA-MM-DD: ");
            LocalDate fechaFinEst = leerFecha("Fecha estimada de fin (AAAA-MM-DD): ");

            if (fechaFinEst.isBefore(fechaInicioEst)) {
                throw new IllegalArgumentException("La fecha de finalización no puede ser antes que la fecha de inicio.")
                return;
            }
            Tarea nuevaTarea = new Tarea(descripcion, asignado, fechaInicioEst, fechaFinEst);
        } catch (Exception e){
            System.out.println("Error al crear la tarea: " + e.getMessage());
        }
    }

    /*
    Métodos para registrar, actualizar y eliminar tareas */

    public void inicio(){
        // Método principal para iniciar el menú
        boolean salir = false;
        while (!salir) {
            try{
                System.out.println("=== Menú Principal ===");
                System.out.println("1. Iniciar Sesión");
                System.out.println("2. Salir");
                System.out.print("Seleccione una opción: ");
                int opcion = leerEntero();

                switch (opcion) {
                    case 1:
                        procesarLogin();
                        break;
                    case 2:
                        salir = true;
                        System.out.println("Saliendo del sistema. ¡Hasta luego!");
                        System.exit(0);
                    default:
                        System.out.println("Opción no válida. Intente de nuevo.");
                }
            } catch (NumberFormatException e) {
            System.out.println("Entrada no válida. Por favor, ingrese un número.");
            } catch (Exception e) {
                System.out.println("Error del sistema: " + e.getMessage());
                }
        } // Fin while
    } // Fin método inicio

    private void procesarLogin() {

        // Método para procesar el inicio de sesión
        System.out.print("Ingrese su correo o nickname: ");
        String credencial = scanner.nextLine();
        System.out.print("Ingrese su contraseña: ");
        String password = scanner.nextLine();
        try {
            this.usuarioActual = sistemaLogin.iniciarSesion(credencial, password, gestorUsuarios);
            System.out.println("Bienvenidx, " + usuarioActual.getNombre());
        } catch (Exception e) {
            System.out.println("Error de inicio de sesión: " + e.getMessage());
        }
    }

    private void mostrarMenuCorrespondiente() {

        // Método para mostrar el menú según el tipo de usuario
        if (usuarioActual instanceof Administrador) {
            menuAdmin();
        } else if (usuarioActual instanceof Desarrollador) {
            menuDesarrollador();
        } else if (usuarioActual instanceof Invitado) {
            menuInvitado();
        } else {
            System.out.println("Tipo de usuario no reconocido.");
        }
    }

    // ------ Menú Administrador ------
    private void menuAdmin(){
        boolean salir = false;
        while (!salir) {
            System.out.println("--- Menú Administrador ---");
            System.out.println("1. Crear Usuario");
            System.out.println("2. Crear tarea nueva");
            System.out.println("3. Ver todas las tareas");
            System.out.println("4. Actualizar tarea");
            System.out.println("5. Eliminar tarea");
            System.out.println("6. Cerrar sesión");

            System.out.print("Seleccione una opción: ");
            int opcion = leerEntero();
            
            switch (opcion){
                case 1: 
                    registrarUsuario();
                    break;
                case 2: 
                    crearTarea(true);
                    break;
                case 3:
                    gestorTareas.getTodasLasTareas();
                    break;
                case 4:
                    actualizarTarea(true);
                    break;
                case 5:
                    eliminarTarea();
                    break;
                case 6:
                    salir = true;
                    usuarioActual = null;
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
                    break;
            }
        }
    }

    // ------ Menú Desarrollador ------
    private void menuDesarrollador(){
        boolean salir = false;
        while (!salir) {
            System.out.println("--- Menú Desarrollador ---");
            System.out.println("1. Crear tareas");
            System.out.println("2. Ver mis tareas");
            System.out.println("3. Actualizar tareas");
            System.out.println("4. Cerrar sesión");

            System.out.print("Seleccione una opción: ");
            int opcion = leerEntero();
            
            switch (opcion){
                case 1: 
                    crearTarea(false);
                    break;
                case 2:
                    gestorTareas.getTareasPorUsuario(usuarioActual.getNickname());
                    break;
                case 3:
                    actualizarTarea(false);
                    break;
                case 4:
                    salir = true;
                    usuarioActual = null;
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
                    break;
            }
        }
    }

    // ------ Menú Invitado ------
    private void menuInvitado(){
        System.out.println("---Menú Invitado ---");
        System.out.println("Tareas asignadas: ");
        gestorTareas.getTareasPorUsuario(usuarioActual.getNickname());
        System.out.println("Presione Enter para cerrar sesión.");
        scanner.nextLine();
        usuarioActual = null;
    }

} // Fin de la clase MenuManager