import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class GestorTareas {
    private List<Tarea> tareas;
    private final String ARCHIVO_TAREAS = "tareas.txt";
    
    public GestorTareas() {
        this.tareas = new ArrayList<>();
        cargarTareas();
  
    }
    
    public boolean crearTarea(Tarea tarea) {
        try {
        int nuevoId = 1;
        if (!tareas.isEmpty()) {
            nuevoId = tareas.get(getTotalTareas() - 1).getId() + 1;
        }
        tarea.setId(nuevoId);
        tarea.setEstado("Pendiente");
        tareas.add(tarea);
        guardarTareas();
        return true;
    } catch (Exception e) {
        System.out.println("Error al guardar la tarea: " + e.getMessage());
        return false;
    }
    }
  
    public List<Tarea> getTodasLasTareas() {
        return new ArrayList<>(tareas); 
    }
    
    public List<Tarea> getTareasPorUsuario(String usuario) {
        List<Tarea> resultado = new ArrayList<>();
        for (Tarea tarea : tareas) {
            if (tarea.getUsuarioAsignado().equals(usuario)) {
                resultado.add(tarea);
            }
        }
        return resultado;
    }
    
    public List<Tarea> getTareasPorEstado(String estado) {
        List<Tarea> resultado = new ArrayList<>();
        for (Tarea tarea : tareas) {
            if (tarea.getEstado().equals(estado)) {
                resultado.add(tarea);
            }
        }
        return resultado;
    }
    
    public Tarea buscarTareaPorId(int id) {
        for (Tarea tarea : tareas) {
            if (tarea.getId() == id) {
                return tarea;
            }
        }
        return null; // No encontrada
    }
    
    public boolean actualizarTarea(Tarea tareaActualizada) {
        for (int i = 0; i < tareas.size(); i++) {
            if (tareas.get(i).getId() == tareaActualizada.getId()) {
                tareas.set(i, tareaActualizada);
                guardarTareas();
                return true;
            }
        }
        return false; // No se encontró la tarea
    }
    
    public boolean eliminarTarea(int id) {
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
        return tareas.size();
    }
    private void guardarTareas() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO_TAREAS))) {
            oos.writeObject(tareas);
        } catch (IOException e) {
            System.out.println("Error al guardar en archivo: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private void cargarTareas() {
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
}
