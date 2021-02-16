package modelo;

import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class LineaDeVenta extends Entidad {

    int id, cantidad, descuento;
    private Producto producto;
    double subtotal;
    
    public LineaDeVenta(GestionConexion conexion){
	    super("LineaDeVenta", conexion);

	    producto = new Producto(conexion, "");
    }

    public LineaDeVenta(GestionConexion conexion, int id, Producto producto, int cantidad, int descuento, double subtotal) {
	   super("Venta", conexion);
        this.id = id;
	this.producto = producto;
        this.cantidad = cantidad;
        this.descuento = descuento;
        this.subtotal = subtotal;
    }
    
    public LineaDeVenta(int id, Producto producto, int cantidad, int descuento, double subtotal) {
        this.id = id;
	this.producto = producto;
        this.cantidad = cantidad;
        this.descuento = descuento;
        this.subtotal = subtotal;
    }

    public LineaDeVenta() {
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

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
    
    
     public ArrayList<LineaDeVenta> obtenerLineasDeVenta() {
        ArrayList<LineaDeVenta> lineasDeVenta = new ArrayList<>();
	String query = "select * from venta order by id";

        try {
            ResultSet resultado = getGestionConexion().getStatement().executeQuery(query);

            while (resultado.next()) {
		Producto productoCorrespondiente = producto.obtenerProducto(resultado.getInt("id"));

                LineaDeVenta lineaDeVenta = new LineaDeVenta(
			resultado.getInt("id"),
			productoCorrespondiente,
                        resultado.getInt("cantidad"),
                        resultado.getInt("descuento"),
                        resultado.getInt("subtotal")
                        );

                lineasDeVenta.add(lineaDeVenta);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return lineasDeVenta;

    }

    public void agregarLineaDeVenta(LineaDeVenta venta) {
	String query = "insert into venta (idProducto, cantidad, descuento, subtotal) values (" + producto.getId() + ", " + venta.getCantidad() + ", " + venta.getDescuento() + ", " + venta.getSubtotal() + ")";

        try {
		getGestionConexion().getStatement().executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void eliminarLineaDeVenta(int id) {
	String query = "delete from venta where id = '"+ id + "'";

        try {
		getGestionConexion().getStatement().executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void eliminarLineasDeVenta(){
	String query = "delete from venta";

        try {
		getGestionConexion().getStatement().executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    
    }

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

}
