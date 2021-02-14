package modelo;

public class Usuario extends Entidad {
   
    String usuario, contraseña;

  /*  public Usuario(String usuario, String contraseña) {
        this.usuario = usuario;
        this.contraseña = contraseña;
    }*/
    
    public Usuario(GestionConexion conexion, String usuario, String contraseña) {
        super("Usuario", conexion);
        this.usuario = usuario;
        this.contraseña = contraseña;
    }
        
    public Usuario() {
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
    
    
}
