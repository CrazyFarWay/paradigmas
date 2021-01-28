package modelo;

import java.sql.*;
import java.util.ArrayList;

public class BaseDeDatos {

    Connection conexion;
    String url = "jdbc:mysql://localhost/drugstore";
    String usuario = "root";
    String contraseña = "";

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
            PreparedStatement statement = conexion.prepareStatement("insert into proveedores (nombre, rubro, telefono, correoElectronico, direccion) values (?, ?, ?, ?, ?)");

            statement.setString(1, proveedor.getNombre());
            statement.setString(2, proveedor.getRubro());
            statement.setString(3, proveedor.getTelefono());
            statement.setString(4, proveedor.getCorreoElectronico());
            statement.setString(5, proveedor.getDireccion());

            statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void eliminarProveedor(int codigo) {
        PreparedStatement statement;

        try {
		statement = conexion.prepareStatement("DELETE FROM PROVEEDORES WHERE codigo = ?");
		statement.setInt(1, codigo);
		statement.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void modificarProveedor(Proveedor proveedor) {

        try {
            PreparedStatement statement = conexion.prepareStatement("update proveedores set nombre=?, rubro=?, telefono=?, correoElectronico=?, direccion=? where codigo=?");

            statement.setString(1, proveedor.getNombre());
            statement.setString(2, proveedor.getRubro());
            statement.setString(3, proveedor.getTelefono());
            statement.setString(4, proveedor.getCorreoElectronico());
            statement.setString(5, proveedor.getDireccion());
            statement.setInt(6, proveedor.getCodigo());

            statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public ArrayList<Proveedor> obtenerProveedores() {

        ArrayList<Proveedor> proveedores = new ArrayList<>();

        try {
            Statement statement = conexion.createStatement();
            ResultSet resultado = statement.executeQuery("select * from proveedores order by codigo");

            while (resultado.next()) {
                Proveedor proveedor = new Proveedor(resultado.getInt("codigo"),
                        resultado.getString("nombre"),
                        resultado.getString("rubro"),
                        resultado.getString("telefono"),
                        resultado.getString("correoElectronico"),
                        resultado.getString("direccion"));

                proveedores.add(proveedor);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return proveedores;
    }

    public ArrayList<Usuario> obtenerUsuarios(){
        
        ArrayList<Usuario> usuarios = new ArrayList<>();

        try {
            Statement statement = conexion.createStatement();
            ResultSet resultado = statement.executeQuery("select * from usuarios order by usuario");

            while (resultado.next()) {
                Usuario usuario = new Usuario(resultado.getString("usuario"),
                        resultado.getString("contraseña"));

                usuarios.add(usuario);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return usuarios;
    
    }
}
