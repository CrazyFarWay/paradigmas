package controlador;

import vistas.VistaInventario;


public class ControladorVistaInventario {
    
    static VistaInventario vista = new VistaInventario();
    
    public static void mostrar(){
        vista.setVisible(true);
    }
}
