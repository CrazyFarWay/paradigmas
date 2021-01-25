package controlador;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import modelo.BaseDeDatos;
import modelo.Producto;
import vistas.VistaInventario;


public class ControladorVistaInventario {
    
    static VistaInventario vista = new VistaInventario();
    
    public static void mostrar(){
        vista.setVisible(true);
        
        BaseDeDatos baseDeDatos = new BaseDeDatos();
        ArrayList<Producto> productos = baseDeDatos.obtenerProductos();
        
     //   DefaultTableModel model = (DefaultTableModel) vista.getTablaInventario().getModel();
     //   model.setNumRows(0);
        
        
        
    }
    
    
}
