package controlador;

import javax.swing.JFrame;

public class ControladorMenu {

	public static void abrirProveedores(JFrame vista) {
		vista.dispose();
		ControladorVistaProveedores.mostrar();
	}

	public static void abrirABM(JFrame vista) {
		vista.dispose();
		ControladorVistaABMProductos.mostrar();
	}

	public static void salirDelPrograma() {
		System.exit(0);
	}

	public static void abrirVentas(JFrame vista) {
		vista.dispose();
		ControladorVistaVenta.mostrar();
	}
        
        public static void abrirActualizarPrecios(JFrame vista){
                vista.dispose();
                ControladorVistaActualizarPrecios.mostrar();
        }

	public static void cerrarSesion(JFrame vista) {
            GestionConexion.cerrarSesion();
	}
        
	public static void abrirProveedoresDesdeInicio() {
		
		ControladorVistaProveedores.mostrar();
	}

	public static void abrirABMDesdeInicio() {
		
		ControladorVistaABMProductos.mostrar();
	}

	public static void abrirVentasDesdeInicio() {
		
		ControladorVistaVenta.mostrar();
	}
        
        public static void abrirActualizarPreciosDesdeInicio(){
                
                ControladorVistaActualizarPrecios.mostrar();
        }
    
}
