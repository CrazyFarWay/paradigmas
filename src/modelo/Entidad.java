/**
 *
 * @author ValeS
 */
package modelo;

public abstract class Entidad {
    
    private String entidad;
    private GestionConexion GestionConexion;
    
    public Entidad() {
    } 

    public Entidad(String entidad, GestionConexion GestionConexion) {
        this.entidad = entidad;
        this.GestionConexion = GestionConexion;
    } 
    
     public GestionConexion getGestionConexion() {
        return GestionConexion;
    } 

    public void setGestionConexion(GestionConexion GestionConexion) {
        this.GestionConexion = GestionConexion;
    } 
    
    public String getEntidad() {
        return entidad;
    } 

    public void setEntidad(String entidad) {
        this.entidad = entidad;
    } 

    
}
