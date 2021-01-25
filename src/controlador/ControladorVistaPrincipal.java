package controlador;

import vistas.VistaPrincipal;

public class ControladorVistaPrincipal {
    
    static VistaPrincipal vista = new VistaPrincipal();
  
    public static void mostrar(){
	    vista.setVisible(true);
    }
    
    public static void botonInventario(){
        vista.dispose();
        ControladorVistaInventario.mostrar();
    }
    
    public static void botonVenta(){
        vista.dispose();
        ControladorVistaVentaPrincipal.mostrar();
        
    }
    
    public static void botonProveedores(){
        vista.dispose();
        ControladorVistaProveedores.mostrar();
    }
    
}
