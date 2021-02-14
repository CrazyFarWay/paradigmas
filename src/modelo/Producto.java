package modelo;

import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;


public class Producto {

    String nombre, marca, rubro;
    double precio;
    int id, cantidad;

    private Statement statement;
    private Connection conexion;
    
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

        try {
            PreparedStatement statement = conexion.prepareStatement("insert into productos (nombre, marca, rubro, precio, cantidad) values (?, ?, ?, ?, ?)");

            statement.setString(1, producto.getNombre());
            statement.setString(2, producto.getMarca());
            statement.setString(3, producto.getRubro());
            statement.setDouble(4, producto.getPrecio());
            statement.setInt(5, producto.getCantidad());

            statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void eliminarProducto(int id) {
        Statement statement;

        try {
            statement = conexion.createStatement();
            statement.executeUpdate("delete from productos where id=" + id);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void modificarProducto(Producto producto) {

        try {
            PreparedStatement statement = conexion.prepareStatement("update productos set nombre=?, marca=?, rubro=?, precio=?, cantidad=? where id=?");

            statement.setString(1, producto.getNombre());
            statement.setString(2, producto.getMarca());
            statement.setString(3, producto.getRubro());
            statement.setDouble(4, producto.getPrecio());
            statement.setInt(5, producto.getCantidad());
            statement.setInt(6, producto.getId());

            statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public ArrayList<Producto> obtenerProductos() {
        ArrayList<Producto> productos = new ArrayList<>();

        try {
            Statement statement = conexion.createStatement();
            ResultSet resultado = statement.executeQuery("select * from productos order by id");

            while (resultado.next()) {
                Producto producto = new Producto(resultado.getInt("id"),
                        resultado.getString("nombre"),
                        resultado.getString("marca"),
                        resultado.getString("rubro"),
                        resultado.getDouble("precio"),
                        resultado.getInt("cantidad"));

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
        
        
        try {
            Statement statement = conexion.createStatement();
            ResultSet resultado = statement.executeQuery(
                    "select * from productos "+ rubro + orderBy + precio);

            while (resultado.next()) {
                Producto producto = new Producto(resultado.getInt("id"),
                        resultado.getString("nombre"),
                        resultado.getString("marca"),
                        resultado.getString("rubro"),
                        resultado.getDouble("precio"),
                        resultado.getInt("cantidad"));

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
        
        try {
            Statement statement = conexion.createStatement();
            ResultSet resultado = statement.executeQuery(
                    "select * from productos "+ rubro);

            while (resultado.next()) {
                Producto producto = new Producto(resultado.getInt("id"),
                        resultado.getString("nombre"),
                        resultado.getString("marca"),
                        resultado.getString("rubro"),
                        resultado.getDouble("precio"),
                        resultado.getInt("cantidad"));

                productos.add(producto);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return productos;
    }
    
    public ArrayList<String> obtenerFiltrosRubroProductos() {
        ArrayList<String> filtros = new ArrayList<>();
        
        try {
            Statement statement = conexion.createStatement();
            ResultSet resultado = statement.executeQuery(
                    "select distinct rubro from productos");

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
            
            try {
                PreparedStatement statement = conexion.prepareStatement("update productos set precio = precio + precio * ?");

                statement.setDouble(1, cantidad/100);

                System.out.println(statement.toString());
                statement.executeUpdate();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            
        } else if (opcion.equals("VALOR")) {
            
            try {
                PreparedStatement statement = conexion.prepareStatement("update productos set precio = precio + ?");

                statement.setDouble(1, cantidad);
                
                System.out.println(statement.toString());
                statement.executeUpdate();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            
        }
    }
    public void disminuirPrecios(String opcion, double cantidad) {
        if (opcion.equals("PORCENTAJE")) {
            
            try {
                PreparedStatement statement = conexion.prepareStatement("update productos set precio = precio - precio * ?");

                statement.setDouble(1, cantidad/100);

                System.out.println(statement.toString());
                statement.executeUpdate();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            
        } else if (opcion.equals("VALOR")) {
            
            try {
                PreparedStatement statement = conexion.prepareStatement("update productos set precio = precio - ?");

                statement.setDouble(1, cantidad);

                System.out.println(statement.toString());
                statement.executeUpdate();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            
        }
        
    }
}
