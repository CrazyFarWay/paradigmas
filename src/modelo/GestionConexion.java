/**
 *
 * @author ValeS
 */
package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class GestionConexion {
    
    private Connection conexion;
    private Statement statement;
    private String driver;
    private String protocolo;
    private String servidor;
    private String database;
    private String usuario;
    private String contraseña;
    
   // private String url = "jdbc:mysql://localhost/drugstore";

    public GestionConexion() {
        System.out.println("Constructor Conexion por defecto a MySql.");
        this.driver = "com.mysql.cj.jdbc.Driver";
        this.protocolo = "jdbc:mysql";
        this.servidor = "10.0.0.4"; //???
        this.database = "drugstore";
        this.usuario = "root";
        this.contraseña = "";
    }

    public GestionConexion(String dbms, String servidor, String database, String usuario,
            String contraseña) {
        if (dbms.toUpperCase().equals("MYSQL")) {
            this.driver = "com.mysql.cj.jdbc.Driver";
            this.protocolo = "jdbc:mysql";
        } else if (dbms.toUpperCase().equals("POSTGRES")) {
            this.driver = "org.postgresql.Driver";
            this.protocolo = "jdbc:postgresql";
        }
        this.servidor = servidor;
        this.database = database;
        this.usuario = usuario;
        this.contraseña = contraseña;
    }

    public Connection getConexion() {
        return conexion;
    }

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
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

    public String getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(String protocolo) {
        this.protocolo = protocolo;
    }

    public String getServidor() {
        return servidor;
    }

    public void setServidor(String servidor) {
        this.servidor = servidor;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
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
    

    public boolean isDriver() {
        try {
            Class.forName(getDriver());
        } catch (ClassNotFoundException ex) {
            return false;
        }
        return true;
    }

    public void conectar() throws SQLException, ClassNotFoundException {
        System.out.println("Conectando ...");
        if (isDriver()) {
            String url = getProtocolo() + "://" + getServidor() + "/" + getDatabase();
            try {
                setConexion(DriverManager.getConnection(url, getUsuario(), getContraseña()));
                setStatement(getConexion().createStatement());
            } catch (SQLException ex) {
                System.err.println("No puede conectar a la Base de Datos.");
                System.err.println(ex.getMessage());
                throw (ex);
            }
        } else {
            System.err.println("No encuentra el Driver.");
            JOptionPane.showMessageDialog(null,
                    "Error Driver Base de Datos...", "ADVERTENCIA",
                    JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }

    public void cerrar() {
        try {
            conexion.close();
        } catch (SQLException ex) {
            System.err.println("No puede cerrar conexion a la Base de Datos.");
            System.err.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error Cerrar BD...",
                    "ADVERTENCIA", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void cambiarClave(String usuario, String nuevaClave) throws SQLException, ClassNotFoundException{
        String sql = 
                "ALTER USER '" + usuario + "'@'127.0.0.1' IDENTIFIED BY '" + nuevaClave + "'";
        getStatement().executeUpdate(sql);
        sql = "FLUSH PRIVILEGES";
        getStatement().executeUpdate(sql);
    }
}
