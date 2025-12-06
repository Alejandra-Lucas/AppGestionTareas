// Classe que representa un usuario
import java.io.Serializable;

public abstract class Usuario implements Serializable {
  protected int id;
  protected String nombre; 
  protected String nickname;
  protected String correo;
  protected String password; 

public Usuario(int id, String nombre, String nickname, String correo, String password) {
  this.id = id;
  this.nombre = nombre;
  this.nickname = nickname;
  this.correo = correo; 
  this.password = password; 
}

// Definición de métodos getter de clase usuario
public int getId() { return id; }
public String getNombre() { return nombre; }
public String getNickname() { return nickname; }
public String getCorreo() { return correo; }
public String getPassword() { return password; }

// Definición de métodos setter de clase usuario

public void setId(int id) {
  this.id = id;
}
public void setNombre(String nombre) {
  this.nombre = nombre;
}
public void setNickname(String nickname) {
  this.nickname = nickname;
}
public void setCorreo(String correo) {
  this.correo = correo;
}
public void setPassword(String password) {
  this.password = password;
}

public abstract void mostrarMenu();
}
