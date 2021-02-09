package controlador;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import modelo.BaseDeDatos;
import modelo.Producto;
import vistas.VistaABM;

public class ControladorVistaABM {

    static VistaABM vista = new VistaABM();

    public static void mostrar() {
        vista.setVisible(true);

        BaseDeDatos baseDeDatos = new BaseDeDatos();
        ArrayList<Producto> productos = baseDeDatos.obtenerProductos();

        DefaultTableModel model = (DefaultTableModel) vista.getTablaInventario().getModel();
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

    public static void agregarProducto() {

        BaseDeDatos baseDeDatos = new BaseDeDatos();
	Producto producto = new Producto(
		Integer.parseInt(vista.getId().getText()),
		vista.getNombre().getText(),
		vista.getMarca().getText(),
                vista.getRubro().getText(),
		Double.parseDouble(vista.getPrecio().getText()),
		Integer.parseInt(vista.getCantidad().getText())
	);
	    
	baseDeDatos.agregarProducto(producto);
	mostrar();
    }

    public static void modificarProducto() {


        BaseDeDatos baseDeDatos = new BaseDeDatos();
	Producto producto = new Producto(
		Integer.parseInt(vista.getId().getText()),
		vista.getNombre().getText(),
		vista.getMarca().getText(),
                vista.getRubro().getText(),
		Double.parseDouble(vista.getPrecio().getText()),
		Integer.parseInt(vista.getCantidad().getText())
	);
	    
	baseDeDatos.modificarProducto(producto);
	mostrar();
    }

    public static void eliminarProducto() {
       
        
        BaseDeDatos baseDeDatos = new BaseDeDatos();
	baseDeDatos.eliminarProducto(Integer.parseInt(vista.getId().getText()));
	mostrar();

    }
    
    public static void limpiarTextFields(){
	vista.getId().setText("");
	vista.getNombre().setText("");
	vista.getMarca().setText("");
        vista.getRubro().setText("");
	vista.getPrecio().setText("");
	vista.getCantidad().setText("");
    }
    
    public static void seleccionarProducto(){
	int filaSeleccionada = vista.getTablaInventario().getSelectedRow();
	DefaultTableModel modelo = (DefaultTableModel) vista.getTablaInventario().getModel();

	if(filaSeleccionada >= 0){
		vista.getId().setText(modelo.getValueAt(filaSeleccionada, 0).toString());
		vista.getNombre().setText(modelo.getValueAt(filaSeleccionada, 1).toString());
		vista.getMarca().setText(modelo.getValueAt(filaSeleccionada, 2).toString());
                vista.getRubro().setText(modelo.getValueAt(filaSeleccionada, 3).toString());
		vista.getPrecio().setText(modelo.getValueAt(filaSeleccionada, 4).toString());
		vista.getCantidad().setText(modelo.getValueAt(filaSeleccionada, 5).toString());
	}
    }
    
    public static void actualizarProductos() {
        vista.dispose();
        ControladorVistaActualizarProductos.mostrar();
    }

    public static void filtrarProductos() {
        BaseDeDatos baseDeDatos = new BaseDeDatos();
       
        ArrayList<Producto> productos = baseDeDatos.obtenerProductosFiltrados(
                vista.getFiltroCantidad().getSelectedItem().toString(),
                vista.getFiltroPrecio().getSelectedItem().toString(),
                vista.getFiltroRubro().getSelectedItem().toString()
        );
        
                
        
        DefaultTableModel model = (DefaultTableModel) vista.getTablaInventario().getModel();
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
