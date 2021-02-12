package controlador;

import javax.swing.JFrame;

public class ControladorMenu {

	public static void abrirProveedores(JFrame vista) {
		vista.dispose();
		ControladorVistaProveedores.mostrar();
	}

	public static void abrirABM(JFrame vista) {
		vista.dispose();
		ControladorVistaABM.mostrar();
	}

	public static void salirDelPrograma() {
		System.exit(0);
	}

	public static void abrirVentas(JFrame vista) {
		vista.dispose();
		ControladorVistaVenta.mostrar();
	}
        
        public static void abrirActualizacionPrecios(JFrame vista){
                vista.dispose();
                ControladorVistaActualizacionPrecios.mostrar();
        }

	public static void cerrarSesion(JFrame vista) {
		vista.dispose();
		ControladorVistaLogin.mostrar();
	}
    
}
