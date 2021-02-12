package controlador;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import modelo.BaseDeDatos;
import modelo.Producto;
import vistas.VistaActualizacionPrecios;

public class ControladorVistaActualizacionPrecios {
    
    static VistaActualizacionPrecios vista = new VistaActualizacionPrecios();
    
    public static void mostrar(){
        
        vista.setVisible(true);
        
        BaseDeDatos baseDeDatos = new BaseDeDatos();
        ArrayList<Producto> productos = baseDeDatos.obtenerProductos();

        DefaultTableModel model = (DefaultTableModel) vista.getTablaProductos().getModel();
        model.setNumRows(0);

        for (Producto producto : productos) {
            Object[] fila = new Object[6];

            fila[0] = producto.getId();
            fila[1] = producto.getNombre();
            fila[2] = producto.getMarca();
            fila[3] = producto.getRubro();
            fila[4] = producto.getPrecio();
            fila[5] = producto.getCantidad();
            model.addRow(fila);
        }
    }
    
    
    
}
