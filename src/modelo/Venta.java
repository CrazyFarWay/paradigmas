package modelo;

import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Venta {

    int id, cantidad, descuento;
    String nombre, marca;
    double precioUnidad, subtotal;
    
    private Statement statement;
    private Connection conexion;

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

        try {
            Statement statement = conexion.createStatement();
            ResultSet resultado = statement.executeQuery("select * from venta order by id");

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

        try {
            PreparedStatement statement = conexion.prepareStatement("insert into venta (nombre, marca, cantidad, precioUnidad, descuento, subtotal) values (?, ?, ?, ?, ?, ?)");

            statement.setString(1, venta.getNombre());
            statement.setString(2, venta.getMarca());
            statement.setInt(3, venta.getCantidad());
            statement.setDouble(4, venta.getPrecioUnidad());
            statement.setInt(5, venta.getDescuento());
            statement.setDouble(6, venta.getSubtotal());

            statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void eliminarVenta(int id) {
        PreparedStatement statement;

        try {
            statement = conexion.prepareStatement("delete from venta where id = ?");
            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void eliminarVentas(){
        PreparedStatement statement;

        try {
            statement = conexion.prepareStatement("delete from venta");
            statement.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    
    }

}
