package controlador;

import java.util.ArrayList;
import modelo.*;
import vistas.*;


public class ControladorVistaLogin {
	private static VistaLogin ventanaLogin = new VistaLogin();
	
	public static void mostrar(){
		ventanaLogin.getUsuario().setText("");
		ventanaLogin.getContraseña().setText("");
		ventanaLogin.getLabelUsuarioContraseñaIncorrecto().setVisible(false);
		ventanaLogin.setVisible(true);
	}
	
	public static void ingresar(){
		BaseDeDatos baseDeDatos = new BaseDeDatos();
		ArrayList<Usuario> usuarios = baseDeDatos.obtenerUsuarios();
		
		String usuarioIngresado = ventanaLogin.getUsuario().getText();
		String contraseñaIngresada = ventanaLogin.getContraseña().getText();
		
		boolean bandera = false;
		
		for(Usuario usuario:usuarios){
			if(usuarioIngresado.equals(usuario.getUsuario()) && contraseñaIngresada.equals(usuario.getContraseña())){
				bandera = true;
			}
		}
		
		if (bandera){
			ventanaLogin.setVisible(false);
			ControladorVistaVenta.mostrar();
		}
		else{
			ventanaLogin.getLabelUsuarioContraseñaIncorrecto().setVisible(true);
		}
	}
}
