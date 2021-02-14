package modelo;

import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Proveedor extends Entidad {

    int codigo;
    String nombre, rubro, telefono, correoElectronico, direccion;
    
    private Statement statement;
    private Connection conexion;
    

    public Proveedor(GestionConexion conexion, String nombre){
	    super("Proveedor", conexion);
	    this.nombre = nombre;
    }

    public Proveedor(GestionConexion conexion, int codigo, String nombre, String rubro, String telefono, String correoElectronico, String direccion) {
	    super("Proveedor", conexion);
        this.codigo = codigo;
        this.nombre = nombre;
        this.rubro = rubro;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
        this.direccion = direccion;
    }

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
	ResultSet resultado;
	String query = "insert into proveedores (nombre, rubro, telefono, correoElectronico, direcion) vaules ('"+ proveedor.getNombre() + "', '" +
proveedor.getRubro() + "', '" + proveedor.getTelefono() + "', '" + proveedor.getCorreoElectronico() + "', '" + proveedor.getDireccion() + "')";

        try {
		resultado = getGestionConexion().getStatement().executeQuery(query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void eliminarProveedor(int codigo) {
	    ResultSet resultado;
	    String query = "delete from proveedores where codigo = '" + codigo + "'";

        try {
		resultado = getGestionConexion().getStatement().executeQuery(query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void modificarProveedor(Proveedor proveedor) {
	String query;

	query = "update proveedores set nombre = '" + proveedor.getNombre() + "', rubro = '" + proveedor.getRubro() + "', telefono = '" + proveedor.getTelefono() + "', correoElectronico = '" + proveedor.getCorreoElectronico() + "', direccion = '" + proveedor.getDireccion() + "' where codigo = '" + proveedor.getCodigo() + "'";

        try {
	    getGestionConexion().getStatement().executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public ArrayList<Proveedor> obtenerProveedores() {
        ArrayList<Proveedor> proveedores = new ArrayList<>();
        ResultSet resultado;
	String query = "select * from proveedores order by codigo";
	

        try {
		resultado = getGestionConexion().getStatement().executeQuery(query);
	    	while (resultado.next()) {
                	Proveedor proveedor = new Proveedor(getGestionConexion(),"");
			proveedor.setCodigo(resultado.getInt("codigo"));
			proveedor.setNombre(resultado.getString("nombre"));
			proveedor.setTelefono(resultado.getString("telefono"));
			proveedor.setCorreoElectronico(resultado.getString("correoElectronico"));
			proveedor.setDireccion(resultado.getString("direccion"));

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
        
        //System.out.println("select * from proveedores "+ rubro + orderBy + precio + cantidad);
	String query = "select * from proveedores "+ rubro;
        ResultSet resultado;
        
        try {
		resultado = getGestionConexion().getStatement().executeQuery(query);
	    	while (resultado.next()) {
                	Proveedor proveedor = new Proveedor(getGestionConexion(),"");
			proveedor.setCodigo(resultado.getInt("codigo"));
			proveedor.setNombre(resultado.getString("nombre"));
			proveedor.setTelefono(resultado.getString("telefono"));
			proveedor.setCorreoElectronico(resultado.getString("correoElectronico"));
			proveedor.setDireccion(resultado.getString("direccion"));

			proveedores.add(proveedor);
		    }


        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return proveedores;
    }

    public ArrayList<String> obtenerFiltrosRubroProveedores() {
        ArrayList<String> filtros = new ArrayList<>();
	ResultSet resultado;
	String query = "select distinct rubro from proveedores";
        
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

}
