import java.time.LocalDate;

public class Tarea {
    private int id;
    private String estado; //Pendiente, En Curso, Completado
    private Usuario usuarioAsignado;
    private String descripcion;
    private LocalDate fechaEstimadaInicio;
    private LocalDate fechaInicioReal;
    private LocalDate fechaEstimadaFin;
    private LocalDate fechaFinReal;

    public Tarea(String descripcion, Usuario usuarioAsignado, LocalDate fechaEstimadaInicio, LocalDate fechaEstimadaFin) {
      // Constructor de la clase Tarea
      this.id = 0; // El ID se asignará al guardar la tarea
      this.descripcion = descripcion;
      this.usuarioAsignado = usuarioAsignado;
      this.estado = "Pendiente";
      this.fechaEstimadaInicio = fechaEstimadaInicio; 
      this.fechaEstimadaFin = fechaEstimadaFin;
      this.fechaInicioReal = null; 
      this.fechaFinReal = null;
    }
    // Definición de métodos getter para acceder a los atributos de clase tarea
    public int getId() {return id; }
    public String getEstado() {return estado; }
    public Usuario getUsuarioAsignado() {return usuarioAsignado; }
    public String getDescripcion() { return descripcion; }
    public LocalDate getFechaEstimadaInicio() { return fechaEstimadaInicio; }
    public LocalDate getFechaInicioReal() { return fechaInicioReal; }
    public LocalDate getFechaEstimadaFin () { return fechaEstimadaFin; }
    public LocalDate getFechaFinReal() {return fechaFinReal; }

// Definición de métodos setter para actualizar los atributos de clase tarea

public void setId(int id) {
  this.id = id;
}

public void setEstado(String nuevoEstado) {
  this.estado = nuevoEstado;
}

public void setUsuarioAsignado(Usuario usuarioAsignado) {
  this.usuarioAsignado = usuarioAsignado;
}

public void setDescripcion(String descripcion) {
  this.descripcion = descripcion;
}

public void setFechaEstimadaInicio(LocalDate fecha) {
  this.fechaEstimadaInicio = fecha;
}

public void setFechaEstimadaFin(LocalDate fecha) {
  this.fechaEstimadaFin = fecha;
}

public void iniciarTarea(LocalDate fechaInicioReal) {
  this.estado = "En Curso";
  this.fechaInicioReal = fechaInicioReal;
}



@Override
  public String toString() {
  return "Tarea ID: " + id +
    " | Estado: " + estado +
    " | Asignada a" +usuarioAsignado +
    " | Descripcion: " + descripcion;
}
}

