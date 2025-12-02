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
        while(true){
            try{
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida. Por favor, ingrese un número.");
            }
        }
    }

    private LocalDate leerFecha(String mensaje) {
        // Método para leer fechas en formato AAAA-MM-DD
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-DD");
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

    private void registrarUsuario() {
        // Método para registrar un nuevo usuario
        try {
            System.out.print("Ingrese nombre completo: ");
            String nombre = scanner.nextLine();
            System.out.print("Ingrese nickname: ");
            String nickname = scanner.nextLine();
            System.out.print("Ingrese correo electrónico: ");
            String correo = scanner.nextLine();
            System.out.print("Ingrese contraseña: ");
            String password = scanner.nextLine();
            System.out.print("Seleccione tipo de usuario (1-Administrador, 2-Desarrollador, 3-Invitado): ");
            int tipo = leerEntero();

            Usuario nuevoUsuario;
            switch (tipo) {
                case 1:
                    nuevoUsuario = new Administrador(0, nombre, nickname, correo, password);
                    break;
                case 2:
                    nuevoUsuario = new Desarrollador(0, nombre, nickname, correo, password);
                    break;
                case 3:
                    nuevoUsuario = new Invitado(0, nombre, nickname, correo, password);
                    break;
                default:
                    System.out.println("Tipo de usuario no válido.");
                    return;
            }

            gestorUsuarios.crearUsuario(nuevoUsuario);
            System.out.println("Usuario creado exitosamente.");
        } catch (ErrorCrearUsuarioException e) {
            System.out.println("Error al crear usuario: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }
    }

    private void crearTarea(boolean esAdmin){
        // Método para crear una nueva tarea
        System.out.print("Crear tarea nueva \n");
        try{
            System.out.print("Ingrese una descripción de la tarea: ");
            String descripcion = scanner.nextLine();

            Usuario asignado = usuarioActual;
            if (esAdmin){
                System.out.println("Ingrese el nickname del usuario asignado: ");
                System.out.print("(Si desea asignarse a sí mismo, deje en blanco y presione Enter)");
                String nickname = scanner.nextLine();
                if (!nickname.isEmpty()){
                    asignado = gestorUsuarios.getUsuarioPorNickname(nickname);
                    // Validar que el usuario exista
                    if (asignado == null){
                        throw new ErrorCrearTareaException("Usuario no encontrado.");
                    }
                }
            }
            // Validar que las fechas sean correctas
            LocalDate fechaInicioEst = leerFecha("Fecha estimada de inicio (AAAA-MM-DD: ");

            if (fechaInicioEst.isBefore(LocalDate.now())) {
                throw new ErrorCrearTareaException("La fecha de inicio no puede ser anterior a la fecha actual.");
            }
            LocalDate fechaFinEst = leerFecha("Fecha estimada de fin (AAAA-MM-DD): ");

            if (fechaFinEst.isBefore(fechaInicioEst)) {
                throw new ErrorCrearTareaException("La fecha de finalización no puede ser antes que la fecha de inicio.");
            }

            // Crear y guardar tarea
            Tarea nuevaTarea = new Tarea(descripcion, asignado, fechaInicioEst, fechaFinEst);
            gestorTareas.crearTarea(nuevaTarea);
            System.out.println("Tarea creada exitosamente.");

        } catch (ErrorCrearTareaException e){
            System.out.println("No se pudo crear la tarea: " + e.getMessage());
        } catch (Exception e){
            System.out.println("Error inesperado: " + e.getMessage());
        }
    }

    private void eliminarTarea() {
        // Método para eliminar una tarea
        System.out.print("Ingrese ID de la tarea a eliminar: ");
        int id = leerEntero();
        
        Tarea tarea = gestorTareas.buscarTareaPorId(id); 
        
        if (tarea == null) {
            System.out.println("Tarea no encontrada.");
            return;
        }

        gestorTareas.eliminarTarea(id);
        System.out.println("Tarea eliminada exitosamente.");
    }

    private void actualizarTarea(boolean esAdmin) {
        // Método para actualizar una tarea
        try{
            System.out.print("Ingrese ID de la tarea a actualizar: ");
            int id = leerEntero();
            
            Tarea tarea = gestorTareas.buscarTareaPorId(id); 
            
            if (tarea == null) {
                System.out.println("Tarea no encontrada.");
                return;
            }

            if (!esAdmin && !tarea.getUsuarioAsignado().equals(usuarioActual)) {
                System.out.println("No tienes permiso para editar esta tarea.");
                return;
            }

            System.out.println("Estado actual: " + tarea.getEstado());
            System.out.println("1. Cambiar estado de la tarea");
            System.out.println("2. Cambiar usuario asignado");
            System.out.println("3. Editar descripción");
            System.out.print("4. Modificar fecha estimada de inicio o finalización");
            System.out.print("Seleccione una opción: ");
            
            int op = leerEntero();
            if (op == 1) {
                cambioEstado(tarea);
            } else if (op == 2) {
                if (!esAdmin) {
                    System.out.println("No tienes permiso para cambiar el usuario asignado.");
                    return;
                }
                System.out.print("Ingrese el nickname del nuevo usuario asignado: ");
                String nickname = scanner.nextLine();
                Usuario nuevoUsuario = gestorUsuarios.getUsuarioPorNickname(nickname);
                if (nuevoUsuario != null) {
                    tarea.setUsuarioAsignado(nuevoUsuario);
                    System.out.println("Usuario asignado actualizado.");
                } else {
                    System.out.println("Usuario no encontrado.");
                }
            } else if (op == 3) {
                System.out.print("Ingrese nueva descripción: ");
                String nuevaDesc = scanner.nextLine();
                tarea.setDescripcion(nuevaDesc);
                System.out.println("Descripción actualizada.");
            } else if(op == 4){
                LocalDate nuevaFechaInicio = leerFecha("Nueva fecha estimada de inicio (AAAA-MM-DD): ");
                LocalDate nuevaFechaFin = leerFecha("Nueva fecha estimada de fin (AAAA-MM-DD): ");

                if (nuevaFechaFin.isBefore(nuevaFechaInicio)) {
                    System.out.println("La fecha de finalización no puede ser antes que la fecha de inicio.");
                    return;
                }
                tarea.setFechaEstimadaInicio(nuevaFechaInicio);
                tarea.setFechaEstimadaFin(nuevaFechaFin);
                System.out.println("Fechas estimadas actualizadas.");
            } else {
                System.out.println("Opción no válida.");
            }
            gestorTareas.actualizarTarea(tarea);
        } catch (ErrorActualizarTareaException e){
            System.out.println("No se pudo actualizar la tarea: " + e.getMessage());
        } catch (Exception e){
            System.out.println("Error inesperado: " + e.getMessage());
        }
    }

    private void cambioEstado(Tarea tarea) {
        // Método para cambiar el estado de una tarea
        String estadoActual = tarea.getEstado();

        System.out.println("Seleccione nuevo estado:");

        if (estadoActual.equals("Pendiente")) {
            System.out.println("1. En Curso");
        } else if (estadoActual.equals("En Curso")) {
            System.out.println("1. Completada");
        } else {
            System.out.println("La tarea ya está completada y no puede cambiar.");
            return;
        }

        int op = leerEntero();
        if (op == 1) {
            if (estadoActual.equals("Pendiente")) {
                tarea.setEstado("En Curso");
                tarea.setFechaInicioReal(LocalDate.now());
            } else if (estadoActual.equals("En Curso")) {
                tarea.setEstado("Completada");
                tarea.setFechaFinReal(LocalDate.now());
            }
            System.out.println("Estado actualizado.");
        }
    }

    private void imprimirListaTareas(java.util.List<Tarea> tareas) {
        if (tareas.isEmpty()) {
            System.out.println("No hay tareas para mostrar.");
        } else {
            System.out.println("Lista de tareas:");
            for (Tarea t : tareas) {
                System.out.println(t.toString()); 
            }
        }
    }


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
        }
    }

    private void procesarLogin() {

        // Método para inicio de sesión
        System.out.print("Ingrese su correo o nickname: ");
        String credencial = scanner.nextLine();
        System.out.print("Ingrese su contraseña: ");
        String password = scanner.nextLine();
        try {
            this.usuarioActual = sistemaLogin.iniciarSesion(credencial, password, gestorUsuarios);
            System.out.println("Bienvenidx, " + usuarioActual.getNombre());
            mostrarMenuCorrespondiente();
        } catch (UserNotFoundException e) {
            System.out.println("Error de inicio de sesión: " + e.getMessage());
        } catch (IncorrectPasswordException e) {
            System.out.println("Error de inicio de sesión: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error inesperado durante el inicio de sesión: " + e.getMessage());  
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
                    imprimirListaTareas(gestorTareas.getTodasLasTareas());
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
                    imprimirListaTareas(gestorTareas.getTareasPorUsuario(usuarioActual));
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
        gestorTareas.getTareasPorUsuario(usuarioActual);
        System.out.println("Presione Enter para cerrar sesión.");
        scanner.nextLine();
        usuarioActual = null;
    }

}