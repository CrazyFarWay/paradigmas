package controlador;

import vistas.VistaVentaPrincipal;

public class ControladorVistaVentaPrincipal {
    
    static VistaVentaPrincipal vista = new VistaVentaPrincipal();
    
    public static void mostrar(){
        vista.setVisible(true);
    }
}
