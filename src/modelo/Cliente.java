package modelo;

import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author CrazyFarWay
 */
public class Cliente extends Entidad {

    private String nombre, telefono, direccion, tipo;
    private String dni;
    private int id;

    public Cliente(GestionConexion conexion) {
        super("Cliente", conexion);
    }

    public Cliente(GestionConexion conexion, int id, String nombre, String dni, String tipo, String telefono, String direccion) {
        this.id = id;
        this.nombre = nombre;
        this.dni = dni;
        this.tipo = tipo;
        this.telefono = telefono;
        this.direccion = direccion;      
        
    }

    public Cliente(int id, String nombre, String dni, String tipo, String telefono, String direccion) {
        this.id = id;
        this.nombre = nombre;
        this.dni = dni;
        this.tipo = tipo;
        this.telefono = telefono;
        this.direccion = direccion;        
    }

    public Cliente(String nombre, String dni, String tipo, String telefono, String direccion) {
        this.nombre = nombre;
        this.dni = dni;
        this.tipo = tipo;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    public Cliente() {
    }
    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void agregarCliente(Cliente cliente) {
        String query;

        query = "insert into clientes (nombre, dni, tipo, telefono, direccion) values ('"+ cliente.getNombre() + "', '" +
cliente.getDni() + "', '" + cliente.getTipo() + "', '" + cliente.getTelefono() + "', '" + cliente.getDireccion() + "')";

        try {
            getGestionConexion().getStatement().executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void eliminarCliente(int id) {
        String query;

        query = "delete from clientes where id='" + id + "'";

        try {
            getGestionConexion().getStatement().executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void modificarCliente(Cliente cliente) {
        String query;

        query = "update clientes set nombre = '" + cliente.getNombre() + "', dni = '" + cliente.getDni() + "', tipo = '" + cliente.getTipo() + "', telefono = '" + cliente.getTelefono() + "', direccion = '" + cliente.getDireccion() + "' where id = " + cliente.getId();

        try {
            getGestionConexion().getStatement().executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public ArrayList<Cliente> obtenerClientes() {
        ArrayList<Cliente> clientes = new ArrayList<>();
        ResultSet resultado;
        String query = "select * from clientes order by id";

        try {
            resultado = getGestionConexion().getStatement().executeQuery(query);
            while (resultado.next()) {
                Cliente cliente = new Cliente(getGestionConexion());
                cliente.setId(resultado.getInt("id"));
                cliente.setNombre(resultado.getString("nombre"));
                cliente.setDni(resultado.getString("dni"));
                cliente.setTipo(resultado.getString("tipo"));
                cliente.setTelefono(resultado.getString("telefono"));
                cliente.setDireccion(resultado.getString("direccion"));

                clientes.add(cliente);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return clientes;
    }
    
     public Cliente obtenerClientesPorId(int id) {
        ResultSet resultado;
        String query = "select * from clientes where id=" +id;
        Cliente cliente = new Cliente(getGestionConexion());
        
        try {
            resultado = getGestionConexion().getStatement().executeQuery(query);
            
                cliente.setId(resultado.getInt("id"));
                cliente.setNombre(resultado.getString("nombre"));
                cliente.setDni(resultado.getString("dni"));
                cliente.setTipo(resultado.getString("tipo"));
                cliente.setTelefono(resultado.getString("telefono"));
                cliente.setDireccion(resultado.getString("direccion"));

                
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return cliente;
    }

    @Override
    public String toString() {
        return "Cliente{" + "nombre=" + nombre + ", telefono=" + telefono + ", direccion=" + direccion + ", tipo=" + tipo + ", dni=" + dni + ", id=" + id + '}';
    }
     
     

}
