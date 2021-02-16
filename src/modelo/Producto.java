package modelo;

import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;


public class Producto extends Entidad {

    String nombre, marca, rubro;
    double precio;
    int id, cantidad;
    
    public Producto(GestionConexion conexion,String nombre) {
	    super("Producto", conexion);
	    this.nombre = nombre;
    }

    public Producto(GestionConexion conexion, int id, String nombre, String marca, String rubro, double precio, int cantidad) {
        super("Producto", conexion);
        this.id = id;
        this.nombre = nombre;
        this.marca = marca;
        this.rubro = rubro;
        this.precio = precio;
        this.cantidad = cantidad;
    }
    
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

    
    public void agregarProducto(Producto producto) {
	String query;

	query = "insert into productos (nombre, marca, rubro, precio, cantidad) values ('"+ producto.getNombre() + "', '" +
producto.getMarca() + "', '" + producto.getRubro() + "', " + producto.getPrecio() + ", " + producto.getCantidad() + ")";

        try {
	    getGestionConexion().getStatement().executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void eliminarProducto(int id) {
	String query;

	query = "delete from productos where id='" + id + "'";

        try {
	    getGestionConexion().getStatement().executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void modificarProducto(Producto producto) {
	String query;

	query = "update productos set nombre = '" + producto.getNombre() + "', marca = '" + producto.getMarca() + "', rubro = '" + producto.getRubro() + "', precio = '" + producto.getPrecio() + "', cantidad = '" + producto.getCantidad() + "' where id = '" + producto.getId() + "'";

        try {
	    getGestionConexion().getStatement().executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public ArrayList<Producto> obtenerProductos() {
        ArrayList<Producto> productos = new ArrayList<>();
        ResultSet resultado;
	String query = "select * from productos order by id";
	

        try {
		resultado = getGestionConexion().getStatement().executeQuery(query);
	    	while (resultado.next()) {
                	Producto producto = new Producto(getGestionConexion(),"");
			producto.setId(resultado.getInt("id"));
			producto.setNombre(resultado.getString("nombre"));
			producto.setMarca(resultado.getString("marca"));
			producto.setRubro(resultado.getString("rubro"));
			producto.setPrecio(resultado.getDouble("precio"));
			producto.setCantidad(resultado.getInt("cantidad"));

			productos.add(producto);
		    }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return productos;
    }
    
    public ArrayList<Producto> obtenerProductosFiltrados(String precio, String rubro) {
        ArrayList<Producto> productos = new ArrayList<>();
        String orderBy = "";
        
        if (rubro.equals("TODOS")) {
            rubro = "";
        }
        else {
            rubro = "WHERE rubro = '"+rubro+"'";
        }
        
        if (precio.equals("TODOS")) {
            precio = "";
        }
        else if (precio.equals("MAYOR A MENOR")) {
            precio = " precio DESC";
            orderBy = "ORDER BY";
        }
        else if (precio.equals("MENOR A MAYOR")) {
            precio = " precio ASC";
            orderBy = "ORDER BY";
        }

	String query = "select * from productos "+ rubro + orderBy + precio;
        ResultSet resultado;
        
        try {
		resultado = getGestionConexion().getStatement().executeQuery(query);
	    	while (resultado.next()) {
                	Producto producto = new Producto(getGestionConexion(),"");
			producto.setId(resultado.getInt("id"));
			producto.setNombre(resultado.getString("nombre"));
			producto.setMarca(resultado.getString("marca"));
			producto.setRubro(resultado.getString("rubro"));
			producto.setPrecio(resultado.getDouble("precio"));
			producto.setCantidad(resultado.getInt("cantidad"));

			productos.add(producto);
		    }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return productos;
    }
    
    public ArrayList<Producto> obtenerProductosFiltrados(String rubro) {
        ArrayList<Producto> productos = new ArrayList<>();
        
        if (rubro.equals("TODOS")) {
            rubro = "";
        }
        else {
            rubro = "WHERE rubro = '"+rubro+"'";
        }
        
        //System.out.println("select * from productos "+ rubro + orderBy + precio + cantidad);
	String query = "select * from productos "+ rubro;
        ResultSet resultado;
        
        try {
		resultado = getGestionConexion().getStatement().executeQuery(query);
	    	while (resultado.next()) {
                	Producto producto = new Producto(getGestionConexion(),"");
			producto.setId(resultado.getInt("id"));
			producto.setNombre(resultado.getString("nombre"));
			producto.setMarca(resultado.getString("marca"));
			producto.setRubro(resultado.getString("rubro"));
			producto.setPrecio(resultado.getDouble("precio"));
			producto.setCantidad(resultado.getInt("cantidad"));

			productos.add(producto);
		    }


        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return productos;
    }
    
    public ArrayList<String> obtenerFiltrosRubroProductos() {
        ArrayList<String> filtros = new ArrayList<>();
	ResultSet resultado;
	String query = "select distinct rubro from productos";
        
        try {
	    resultado = getGestionConexion().getStatement().executeQuery(query);

            while (resultado.next()) {
                String filtro;
                
                filtro = resultado.getString("rubro");
                filtros.add(filtro);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return filtros;
    }
    
    public void aumentarPrecios(String opcion, double cantidad) {
        if (opcion.equals("PORCENTAJE")) {
		Double porcentaje = cantidad/100;
		String query = "update productos set precio = precio + precio * " + porcentaje;
            
            try {
		getGestionConexion().getStatement().executeUpdate(query);
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            
        } else if (opcion.equals("VALOR")) {
		Double valor = cantidad;
		String query = "update productos set precio = precio + " + valor;
            
            try {
		getGestionConexion().getStatement().executeUpdate(query);
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    	public void disminuirPrecios(String opcion, double cantidad) {
		if (opcion.equals("PORCENTAJE")) {
			Double porcentaje = cantidad/100;
			String query = "update productos set precio = precio - precio * " + porcentaje;
		    
		    try {
			getGestionConexion().getStatement().executeUpdate(query);
		    } catch (SQLException ex) {
			System.out.println(ex.getMessage());
		    }
		    
		} else if (opcion.equals("VALOR")) {
			Double valor = cantidad;
			String query = "update productos set precio = precio - " + valor;
		    
		    try {
			getGestionConexion().getStatement().executeUpdate(query);
		    } catch (SQLException ex) {
			System.out.println(ex.getMessage());
		    }
		}
	}
}
