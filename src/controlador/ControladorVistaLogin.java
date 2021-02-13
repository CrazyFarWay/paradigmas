package controlador;

import java.util.ArrayList;
import modelo.*;
import vistas.*;


public class ControladorVistaLogin {
	private static VistaLogin vista = new VistaLogin();
        public static int returnStatus = 0;

    public static int getReturnStatus() {
        return returnStatus;
    }

    public static void setReturnStatus(int returnStatus) {
        ControladorVistaLogin.returnStatus = returnStatus;
    }
	
	public static void mostrar(){
		vista.getUsuario().setText("");
		vista.getContraseña().setText("");
		vista.getLabelUsuarioContraseñaIncorrecto().setVisible(false);
		vista.setVisible(true);
	}
	
	public static void ingresar(){
            returnStatus = 1;
            //vista.setVisible(false);
	}
        
        public static void mostrarErrorDatosIncorrectos() {
            vista.getLabelUsuarioContraseñaIncorrecto().setVisible(true);
        }
        
        public static void salir() {
            vista.setVisible(false);
            System.exit(0);
        }
        
        public static String getUsuario() {
            return vista.getContraseña().getText();
        }
        
        public static String getContraseña() {
            return vista.getUsuario().getText();
        }

        static void ocultar() {
            vista.setVisible(false);
        }
}
