package modelo;

public class Producto {

    String nombre, marca, rubro;
    double precio;
    int id, cantidad;

    public Producto(int id, String nombre, String marca, String rubro, double precio, int cantidad) {
        this.id = id;
        this.nombre = nombre;
        this.marca = marca;
        this.rubro = rubro;
        this.precio = precio;
        this.cantidad = cantidad;
    }
    
    public Producto(String nombre, String marca, String rubro, double precio, int cantidad) {
        this.nombre = nombre;
        this.marca = marca;
        this.rubro = rubro;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public Producto() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getRubro() {
        return rubro;
    }

    public void setRubro(String rubro) {
        this.rubro = rubro;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

}
