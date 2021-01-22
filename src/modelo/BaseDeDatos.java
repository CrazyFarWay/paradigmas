package modelo;

import java.sql.*;
import java.util.ArrayList;

public class BaseDeDatos {

    Connection conexion;
    String url = "jdbc:mysql://localhost/drugstore";
    String usuario = "root";
    String contraseña = "valentinasaccone";

    public BaseDeDatos() {

        try {

            conexion = DriverManager.getConnection(url, usuario, contraseña);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void agregarProducto(Producto producto) {

        try {
            PreparedStatement statement = conexion.prepareStatement("insert into productos (nombre, marca, precio, cantidad) values (?, ?, ?, ?)");

            statement.setString(1, producto.getNombre());
            statement.setString(2, producto.getMarca());
            statement.setDouble(3, producto.getPrecio());
            statement.setInt(4, producto.getCantidad());

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
            PreparedStatement statement = conexion.prepareStatement("update productos set nombre=?, marca=?, precio=?, cantidad=? where id=?");

            statement.setString(1, producto.getNombre());
            statement.setString(2, producto.getMarca());
            statement.setDouble(3, producto.getPrecio());
            statement.setInt(4, producto.getCantidad());
            statement.setInt(5, producto.getId());

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
                        resultado.getDouble("precio"),
                        resultado.getInt("cantidad"));

                productos.add(producto);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return productos;

    }

    public void agregarProveedor(Proveedor proveedor) {
        
        try {
                PreparedStatement statement = conexion.prepareStatement("insert into proveedores (nombre, rubro, telefono, correoElectronico, direccion) values (?, ?, ?, ?)");

            statement.setString(1, producto.getNombre());
            statement.setString(2, producto.getMarca());
            statement.setDouble(3, producto.getPrecio());
            statement.setInt(4, producto.getCantidad());

            statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void eliminarProveedor() {
    }

    public void modificarProveedor() {
    }

    // public ArrayList<Proveedor> obtenerProveedores(){}
    // public ArrayList<Usuario> obtenerUsuarios(){}
}
