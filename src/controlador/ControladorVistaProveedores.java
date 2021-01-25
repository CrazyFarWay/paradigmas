package controlador;

import vistas.VistaProveedores;

public class ControladorVistaProveedores {
    static VistaProveedores vista = new VistaProveedores();
    
    public static void mostrar(){
        vista.setVisible(true);
    }
}
