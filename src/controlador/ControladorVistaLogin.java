package controlador;

import java.util.ArrayList;
import modelo.*;
import vistas.*;


import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.GestionConexion;
import modelo.Usuario;



public class ControladorVistaLogin {
	
    private static GestionConexion conexion;
    private static Producto producto;
    private static Proveedor proveedor;
    private static Venta venta;
    private static VistaInicial vistaInicial;
    private static ArrayList<Usuario> usuarios = new ArrayList<>();

    public static void inicializar() throws Exception {
        vistaInicial = new VistaInicial();
        vistaInicial.setVisible(true);
        conectar();
    }

    public static void conectar() throws SQLException, ClassNotFoundException, Exception {
        VistaLogin vistaLogin = new VistaLogin(vistaInicial, true);
        vistaLogin.setVisible(true);
        while (true) {
            conexion = new GestionConexion("MYSQL", "localhost", "drugstore", vistaLogin.getUsuario().getText(), vistaLogin.getContraseña().getText());
            if (vistaLogin.getReturnStatus() == 1) {
                try {
                    vistaLogin.setVisible(false);
                    conexion.conectar();
		    producto = new Producto(conexion, "");
		    proveedor = new Proveedor(conexion, "");
		    venta = new Venta(conexion, "");
			ControladorMenu.setProducto(producto);
			ControladorMenu.setProveedor(proveedor);
			ControladorMenu.setVenta(venta);
                    //vistaInicial.getUsuario().setText(vistaLogin.getUsuario().getText().toString());
                    //System.out.println("Conecto con mensajeria satisfractoriamente ...");
                    //usuario = new Usuario(conexion, "");
                    //vistaInicial.getUsuario().setText(usuario.buscar(vistaLogin.getUsuario().getText()));
                    //usuario.setNombre(vistaLogin.getUsuario().getText());
                    //usuario.setClave(vistaLogin.getClave().getText());
                    //usuario.enLinea();
                    //muestraUsuarios(vistaInicial);
                    break;
                } catch (SQLException | ClassNotFoundException e) {
                    //vistaInicial.getUsuario().setText("Acceso Incorrecto");
                    //int n = JOptionPane.showConfirmDialog(vistaInicial, "Desea reingresar datos ?", "Sin Acceso al Sistema", JOptionPane.YES_NO_OPTION);
                    /*if (n == JOptionPane.YES_OPTION) {
                        vistaLogin.setVisible(true);
                    } else {
                        System.exit(0);
                    }*/
		    System.out.println("ERROR EN CONECTAR");
                }
            } else {
                ControladorVistaLogin.salir(vistaInicial);
            }
        }
    }

    public static void salir(VistaInicial vista) {
        vista.dispose();
        //usuario.desconecta();
    }


   
    
    
    
    
	/*
	public static void mostrar(){
		vistaInicial.getUsuario().setText("");
		vistaInicial.getContraseña().setText("");
		vistaInicial.getLabelUsuarioContraseñaIncorrecto().setVisible(false);
		vistaInicial.setVisible(true);
	}
	
	public static void ingresar(){
            returnStatus = 1;
            //vista.setVisible(false);
	}
        
        public static void mostrarErrorDatosIncorrectos() {
            vistaInicial.getLabelUsuarioContraseñaIncorrecto().setVisible(true);
        }
        
        public static void salir() {
            vistaInicial.setVisible(false);
            System.exit(0);
        }
        
        public static String getUsuario() {
            return vistaInicial.getContraseña().getText();
        }
        
        public static String getContraseña() {
            return vistaInicial.getUsuario().getText();
        }

        static void ocultar() {
            vistaInicial.setVisible(false);
        }*/
}
