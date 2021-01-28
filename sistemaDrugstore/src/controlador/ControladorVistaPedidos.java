/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import vistas.VistaPedidos;

/**
 *
 * @author CrazyFarWay
 */
public class ControladorVistaPedidos {
	private static VistaPedidos vista = new VistaPedidos();
	
	public static void mostrar(){
		//limpiarTextFields();
		vista.setVisible(true);
	}

	public static void enviarPedido(){

		vista.dispose();
		ControladorVistaProveedores.mostrar();
	}

	public static void limpiarTextFields(){
		vista.getDestinatario().setText("");
		vista.getAsunto().setText("");
		vista.getPedido().setText("");
	}

	public static void rellenarDatos(String destinatario, String asunto){
		vista.getDestinatario().setText(destinatario);
		vista.getAsunto().setText(asunto);
	}
}
