// Clase GestorTareas que maneja las operaciones para las tareas
import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class GestorTareas {
    private List<Tarea> tareas;
    private final String ARCHIVO_TAREAS = "tareas.dat";
    
    public GestorTareas() {
        this.tareas = new ArrayList<>();
        cargarTareas();
  
    }
    
    public void crearTarea(Tarea tarea) throws ErrorCrearTareaException {
        // Método para crear una nueva tarea
        try {

        // Creación de ID
        int nuevoId = 1;
        if (!tareas.isEmpty()) {
            nuevoId = tareas.get(getTotalTareas() - 1).getId() + 1;
        }
        tarea.setId(nuevoId);
        tarea.setEstado("Pendiente"); // Estado inicial
        tareas.add(tarea);
        guardarTareas();
    } catch (Exception e) {
        throw new ErrorCrearTareaException("Error al crear la tarea: " + e.getMessage());
    }
    }
    
    public List<Tarea> getTareas() {
        // Método para obtener todas las tareas
        return new ArrayList<>(tareas);
    }

    public List<Tarea> getTareasPorUsuario(Usuario usuario) {
        // Método para obtener tareas asignadas a un usuario específico
        List<Tarea> resultado = new ArrayList<>();
        for (Tarea tarea : tareas) {
            if (tarea.getUsuarioAsignado().equals(usuario)) {
                resultado.add(tarea);
            }
        }
        return resultado;
    }
    
    public List<Tarea> getTareasPorEstado(String estado) {
        // Método para obtener tareas por estado
        List<Tarea> resultado = new ArrayList<>();
        for (Tarea tarea : tareas) {
            if (tarea.getEstado().equals(estado)) {
                resultado.add(tarea);
            }
        }
        return resultado;
    }
    
    public Tarea buscarTareaPorId(int id) {
        // Método para buscar una tarea por su ID
        for (Tarea tarea : tareas) {
            if (tarea.getId() == id) {
                return tarea;
            }
        }
        return null; // No encontrada
    }
    
    public void actualizarTarea(Tarea tareaActualizada) throws ErrorActualizarTareaException {
        // Método para actualizar una tarea existente
        try{
            for (int i = 0; i < tareas.size(); i++) {
                if (tareas.get(i).getId() == tareaActualizada.getId()) {
                    tareas.set(i, tareaActualizada);
                    guardarTareas();
                }
            }
        } catch (Exception e){
            throw new ErrorActualizarTareaException("Error al actualizar la tarea: " + e.getMessage());
        }
    }
    
    public boolean eliminarTarea(int id) {
        // Método para eliminar una tarea por su ID
        for (int i = 0; i < tareas.size(); i++) {
            if (tareas.get(i).getId() == id) {
                tareas.remove(i);
                guardarTareas();
                return true;
            }
        }
        return false;
    }
    
    public int getTotalTareas() {
        // Método para obtener el total de tareas
        return tareas.size();
    }
    private void guardarTareas() {
        // Guardar la lista de tareas en un archivo
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO_TAREAS))) {
            oos.writeObject(tareas);
        } catch (IOException e) {
            System.out.println("Error al guardar en archivo: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private void cargarTareas() {
        // Cargar la lista de tareas desde un archivo
        File archivo = new File(ARCHIVO_TAREAS);
        if (archivo.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
                tareas = (List<Tarea>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Error al cargar tareas: " + e.getMessage());
                tareas = new ArrayList<>(); // Iniciar vacío si falla
            }
        }
    }
public void setTareas(List<Tarea> lista) {
    // Método SET para establecer la lista de tareas
    this.tareas = lista;
}
}

