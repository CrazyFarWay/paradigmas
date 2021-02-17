package controlador;

import java.util.ArrayList;
import modelo.*;
import vistas.*;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import modelo.GestionConexion;
import modelo.Usuario;

public class ControladorVistaLogin {

    private static GestionConexion conexion;
    private static Producto producto;
    private static Proveedor proveedor;
    private static LineaDeVenta lineaDeVenta;
    private static Cliente cliente;
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
                    conexion.conectar();
                    producto = new Producto(conexion, "");
                    proveedor = new Proveedor(conexion, "");
                    lineaDeVenta = new LineaDeVenta(conexion);
                    cliente = new Cliente(conexion);
                    ControladorMenu.setProducto(producto);
                    ControladorMenu.setProveedor(proveedor);
                    ControladorMenu.setVenta(lineaDeVenta);
                    ControladorMenu.setCliente(cliente);
                    vistaLogin.setVisible(false);

                    break;
                } catch (SQLException | ClassNotFoundException e) {
                    System.out.println("ERROR EN CONECTAR");
                   // vistaLogin.setReturnStatus(0);
                    
                    int n = JOptionPane.showConfirmDialog(vistaInicial, "Desea reingresar datos ?", "Sin Acceso al Sistema", JOptionPane.YES_NO_OPTION);
                    if (n == JOptionPane.YES_OPTION) {
                        vistaLogin.setVisible(true);
                    } else {
                        System.exit(0);
                    }
                }
            } else {
                //ControladorVistaLogin.salir(vistaInicial);
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
        
    */
        public static void salir() {
            vistaInicial.setVisible(false);
            System.exit(0);
        }

	public static void cerrarSesion() {
		conexion.cerrar();
	}

	/*
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
