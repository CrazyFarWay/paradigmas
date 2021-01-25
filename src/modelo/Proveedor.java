package modelo;

public class Proveedor {

    int codigo;
    String nombre, rubro, telefono, correoElectronico, direccion;

    public Proveedor(int codigo, String nombre, String rubro, String telefono, String correoElectronico, String direccion) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.rubro = rubro;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
        this.direccion = direccion;
    }

    public Proveedor(String nombre, String rubro, String telefono, String correoElectronico, String direccion) {
        this.nombre = nombre;
        this.rubro = rubro;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
        this.direccion = direccion;
    }

    public Proveedor() {
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRubro() {
        return rubro;
    }

    public void setRubro(String rubro) {
        this.rubro = rubro;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

}
