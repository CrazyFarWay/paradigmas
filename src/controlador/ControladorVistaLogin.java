package controlador;

import java.util.ArrayList;
import modelo.*;
import vistas.VistaLogin;


public class ControladorVistaLogin {
	VistaLogin ventanaLogin = new VistaLogin();
	
	public void mostrar(){
		ventanaLogin.getLabelUsuarioContraseñaIncorrecto().setVisible(false);
		ventanaLogin.setVisible(true);
	}
	
	public void ingresar(){
		BaseDeDatos baseDeDatos = new BaseDeDatos();
		Usuario usuarioIngresado = new Usuario();
		
		
	}
}
