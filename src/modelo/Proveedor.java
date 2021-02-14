package modelo;

import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Proveedor {

    int codigo;
    String nombre, rubro, telefono, correoElectronico, direccion;
    
    private Statement statement;
    private Connection conexion;
    

    public Proveedor(int codigo, String nombre, String rubro, String telefono, String correoElectronico, String direccion) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.rubro = rubro;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
        this.direccion = direccion;
    }

    public Proveedor(String nombre, String rubro, String telefono, String correoElectronico, String direccion) {
        this.nombre = nombre;
        this.rubro = rubro;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
        this.direccion = direccion;
    }

    public Proveedor() {
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRubro() {
        return rubro;
    }

    public void setRubro(String rubro) {
        this.rubro = rubro;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
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

}
