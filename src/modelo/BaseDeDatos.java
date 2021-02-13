package modelo;

import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class BaseDeDatos {
    
    private Statement statement;
    private String driver;
    private String servidor;
    private Connection conexion;
    private String url = "jdbc:mysql://localhost/drugstore";
    private String usuario;
    private String contraseña;

    public BaseDeDatos() {
        System.out.println("Constructor Conexion por defecto a MySql.");
        this.driver = "com.mysql.cj.jdbc.Driver";
        this.servidor = "10.0.0.4";
        this.usuario = "root";
        this.contraseña = "";
    }

    public BaseDeDatos(String usuario, String contraseña) {
        this.driver = "com.mysql.cj.jdbc.Driver";
        this.servidor = "10.0.0.4";
        this.usuario = usuario;
        this.contraseña = contraseña;
    }
    
    public boolean isDriver() {
        try {
            Class.forName(getDriver());
        } catch (ClassNotFoundException ex) {
            return false;
        }
        return true;
    }
    
    public void conectar() throws SQLException, ClassNotFoundException {
        this.usuario = usuario;
        this.contraseña = contraseña;
        
        System.out.println("Conectando ...");
        
        if (isDriver()) {
            try {
                setConnection(DriverManager.getConnection(url, getUsuario(), getContraseña()));
                setStatement(getConexion().createStatement());
                
            } catch (SQLException ex) {
                System.err.println("No puede conectar a la Base de Datos.");
                System.err.println(ex.getMessage());
                throw (ex);
            }
        } else {
                System.out.println("chau, desde el driver nulo");
            System.err.println("No encuentra el Driver.");
            JOptionPane.showMessageDialog(null,
                    "Error Driver Base de Datos...", "ADVERTENCIA",
                    JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }
    
    public void setConnection(Connection conexion) {
        this.conexion = conexion;
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

    public ArrayList<Usuario> obtenerUsuarios() {

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
        else if (precio.equals("MENOR A MAYOR")) {
            precio = " precio DESC";
            orderBy = "ORDER BY";
        }
        else if (precio.equals("MAYOR A MENOR")) {
            precio = " precio ASC";
            orderBy = "ORDER BY";
        }
        
        //System.out.println("select * from productos "+ rubro + orderBy + precio + cantidad);
        
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
    
     public ArrayList<Proveedor> obtenerProveedoresFiltrados(String rubro) {
        ArrayList<Proveedor> proveedores = new ArrayList<>();
        
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
                    "select * from proveedores "+ rubro);

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

    public ArrayList<String> obtenerFiltrosRubroProveedores() {
        ArrayList<String> filtros = new ArrayList<>();
        
        try {
            Statement statement = conexion.createStatement();
            ResultSet resultado = statement.executeQuery(
                    "select distinct rubro from proveedores");

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

    public Statement getStatement() {
        return statement;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getServidor() {
        return servidor;
    }

    public void setServidor(String servidor) {
        this.servidor = servidor;
    }

    public Connection getConexion() {
        return conexion;
    }

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
    
    public void cerrarSesion() {
        try {
            conexion.close();
        } catch (SQLException ex) {
            System.err.println("No puede cerrar conexion a la Base de Datos.");
            System.err.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error Cerrar BD...",
                    "ADVERTENCIA", JOptionPane.ERROR_MESSAGE);
        }
    }
}
