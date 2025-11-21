import java.util.ArrayList;
import java.util.List;

public class GestorTareas {
    private List<Tarea> tareas;
    
    public GestorTareas() {
        this.tareas = new ArrayList<>();
  
    }
    
    public boolean crearTarea(Tarea tarea) {
        tareas.add(tarea);
        return true;
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
    
    public Tarea buscarTareaPorId(String id) {
        for (Tarea tarea : tareas) {
            if (tarea.getId().equals(id)) {
                return tarea;
            }
        }
        return null; // No encontrada
    }
    
    public boolean actualizarTarea(Tarea tareaActualizada) {
        for (int i = 0; i < tareas.size(); i++) {
            if (tareas.get(i).getId().equals(tareaActualizada.getId())) {
                tareas.set(i, tareaActualizada);
                return true;
            }
        }
        return false; // No se encontró la tarea
    }
    
    public boolean eliminarTarea(String id) {
        for (int i = 0; i < tareas.size(); i++) {
            if (tareas.get(i).getId().equals(id)) {
                tareas.remove(i);
                return true;
            }
        }
        return false;
    }
    
    public int getTotalTareas() {
        return tareas.size();
    }
}
