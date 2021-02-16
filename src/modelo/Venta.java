package modelo;

import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Venta extends Entidad {

    int id, cantidad, descuento;
    String nombre, marca;
    double precioUnidad, subtotal;
    
    public Venta(GestionConexion conexion, String nombre){
	    super("Venta", conexion);
	    this.nombre = nombre;
    }

    public Venta(GestionConexion conexion, int id, String nombre, String marca, int cantidad, double precioUnidad, int descuento, double subtotal) {
	   super("Venta", conexion);
        this.id = id;
        this.nombre = nombre;
        this.marca = marca;
        this.cantidad = cantidad;
        this.precioUnidad = precioUnidad;
        this.descuento = descuento;
        this.subtotal = subtotal;
    }
    
    public Venta(int id, String nombre, String marca, int cantidad, double precioUnidad, int descuento, double subtotal) {
        this.id = id;
        this.nombre = nombre;
        this.marca = marca;
        this.cantidad = cantidad;
        this.precioUnidad = precioUnidad;
        this.descuento = descuento;
        this.subtotal = subtotal;
    }

    public Venta() {
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

    public int getDescuento() {
        return descuento;
    }

    public void setDescuento(int descuento) {
        this.descuento = descuento;
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

    public double getPrecioUnidad() {
        return precioUnidad;
    }

    public void setPrecioUnidad(double precioUnidad) {
        this.precioUnidad = precioUnidad;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
    
    
     public ArrayList<Venta> obtenerVenta() {
        ArrayList<Venta> ventas = new ArrayList<>();
	String query = "select * from venta order by id";

        try {
            ResultSet resultado = getGestionConexion().getStatement().executeQuery(query);

            while (resultado.next()) {
                Venta venta = new Venta(resultado.getInt("id"),
                        resultado.getString("nombre"),
                        resultado.getString("marca"),
                        resultado.getInt("cantidad"),
                        resultado.getDouble("precioUnidad"),
                        resultado.getInt("descuento"),
                        resultado.getInt("subtotal")
                        );

                ventas.add(venta);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return ventas;

    }

    public void agregarVenta(Venta venta) {
	String query = "insert into venta (nombre, marca, cantidad, precioUnidad, descuento, subtotal) values ('"+ venta.getNombre() + "', '" + venta.getMarca() + "', " + venta.getCantidad() + ", " + venta.getPrecioUnidad() + ", " + venta.getDescuento() + ", " + venta.getSubtotal() + ")";

        try {
		getGestionConexion().getStatement().executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void eliminarVenta(int id) {
	String query = "delete from venta where id = '"+ id + "'";

        try {
		getGestionConexion().getStatement().executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void eliminarVentas(){
	String query = "delete from venta";

        try {
		getGestionConexion().getStatement().executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    
    }

}
