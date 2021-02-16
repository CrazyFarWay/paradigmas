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
	   super("LineaDeVenta", conexion);
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

    public LineaDeVenta(int id, int cantidad, int descuento, double subtotal) {
        this.id = id;
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
    
    
     public ArrayList<LineaDeVenta> obtenerLineasDeVenta() throws SQLException {
        ArrayList<LineaDeVenta> lineasDeVenta = new ArrayList<>();
	String query = "select * from lineasdeventa order by idProducto";

        try {
	Statement statement = getGestionConexion().getConexion().createStatement();
	ResultSet otroResultado;
            otroResultado = statement.executeQuery(query);

            while (otroResultado.next()) {
		    System.out.println("ANTES DE ASIGNAR UNA VENTA");
                LineaDeVenta lineaDeVenta = new LineaDeVenta(
			otroResultado.getInt("idProducto"),
                        otroResultado.getInt("cantidad"),
                        otroResultado.getInt("descuento"),
                        otroResultado.getDouble("subtotal")
                        );

                lineasDeVenta.add(lineaDeVenta);
		    System.out.println(lineaDeVenta);
            }

	    for (LineaDeVenta lineaDeVenta: lineasDeVenta) {
		    System.out.println("ANTES DE ASIGNAR EL PRODUCTO");
		Producto productoCorrespondiente = producto.obtenerProducto(lineaDeVenta.getId());
		//Producto productoCorrespondiente = new Producto();
		    System.out.println(productoCorrespondiente);

		    System.out.println("DESPUES DE ASIGNAR EL PRODUCTO");
		lineaDeVenta.setProducto(productoCorrespondiente);
		    System.out.println(lineaDeVenta);
	    }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return lineasDeVenta;

    }

	@Override
	public String toString() {
		return "LineaDeVenta{" + "id=" + id + ", cantidad=" + cantidad + ", descuento=" + descuento + ", producto=" + producto + ", subtotal=" + subtotal + '}';
	}

    public void agregarLineaDeVenta(LineaDeVenta lineaDeVenta) {
	String query = "insert into lineasdeventa (idProducto, cantidad, descuento, subtotal) values (" + producto.getId() + ", " + lineaDeVenta.getCantidad() + ", " + lineaDeVenta.getDescuento() + ", " + lineaDeVenta.getSubtotal() + ")";

        try {
		getGestionConexion().getStatement().executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void eliminarLineaDeVenta(int id) {
	String query = "delete from lineasdeventa where idProducto = '"+ id + "'";

        try {
		getGestionConexion().getStatement().executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void eliminarLineasDeVenta(){
	String query = "delete from lineasdeventa";

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
