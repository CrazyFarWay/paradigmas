package controlador;

//import static controlador.ControladorVistaActualizarPrecios.vista;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
//import modelo.BaseDeDatos;
import modelo.Producto;
import vistas.VistaABMProductos;

public class ControladorVistaABMProductos {
    private static VistaABMProductos vista = new VistaABMProductos();
    private static ArrayList<String> filtros;
	private static Producto producto;

    public static void mostrar() {
        vista.setVisible(true);

        ArrayList<Producto> productos = producto.obtenerProductos();

        filtros = producto.obtenerFiltrosRubroProductos();   
        
        DefaultTableModel model = (DefaultTableModel) vista.getTablaProductos().getModel();
        DefaultComboBoxModel modeloComboBox = (DefaultComboBoxModel) vista.getFiltroRubro().getModel();
        
        model.setNumRows(0);

        for (Producto productoEnLista : productos) {
            Object[] fila = new Object[6];

            fila[0] = productoEnLista.getId();
            fila[1] = productoEnLista.getNombre();
            fila[2] = productoEnLista.getMarca();
            fila[3] = productoEnLista.getRubro();
            fila[4] = productoEnLista.getPrecio();
            fila[5] = productoEnLista.getCantidad();
            model.addRow(fila);
        }
        
        for(String filtro: filtros) {
            if (modeloComboBox.getIndexOf(filtro) == -1) {
                    modeloComboBox.addElement(filtro);
            }
        }
    }

    public static void agregarProducto() {
	Producto productoNuevo = new Producto(
		vista.getNombre().getText(),
		vista.getMarca().getText(),
                vista.getRubro().getText().toUpperCase(),
		Double.parseDouble(vista.getPrecio().getText()),
		Integer.parseInt(vista.getCantidad().getText())
	);
	    
	producto.agregarProducto(productoNuevo);
	mostrar();
    }

    public static void modificarProducto() {
	Producto productoNuevo = new Producto(
		Integer.parseInt(vista.getId().getText()),
		vista.getNombre().getText(),
		vista.getMarca().getText(),
                vista.getRubro().getText().toUpperCase(),
		Double.parseDouble(vista.getPrecio().getText()),
		Integer.parseInt(vista.getCantidad().getText())
	);
	    
	producto.modificarProducto(productoNuevo);
	mostrar();
    }

    public static void eliminarProducto() {

	producto.eliminarProducto(Integer.parseInt(vista.getId().getText()));
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
	int filaSeleccionada = vista.getTablaProductos().getSelectedRow();
	DefaultTableModel modelo = (DefaultTableModel) vista.getTablaProductos().getModel();

	if(filaSeleccionada >= 0){
		vista.getId().setText(modelo.getValueAt(filaSeleccionada, 0).toString());
		vista.getNombre().setText(modelo.getValueAt(filaSeleccionada, 1).toString());
		vista.getMarca().setText(modelo.getValueAt(filaSeleccionada, 2).toString());
                vista.getRubro().setText(modelo.getValueAt(filaSeleccionada, 3).toString());
		vista.getPrecio().setText(modelo.getValueAt(filaSeleccionada, 4).toString());
		vista.getCantidad().setText(modelo.getValueAt(filaSeleccionada, 5).toString());
	}
    }
    
    public static void actualizarPrecios() {
        vista.dispose();
        ControladorVistaActualizarPrecios.mostrar();
    }

    public static void filtrarProductos() {
       
        ArrayList<Producto> productos = producto.obtenerProductosFiltrados(
              vista.getFiltroPrecio().getSelectedItem().toString(),
               vista.getFiltroRubro().getSelectedItem().toString()
        );
        
                
        
        DefaultTableModel model = (DefaultTableModel) vista.getTablaProductos().getModel();
        model.setNumRows(0);

        for (Producto productoEnLista : productos) {
            Object[] fila = new Object[6];

            fila[0] = productoEnLista.getId();
            fila[1] = productoEnLista.getNombre();
            fila[2] = productoEnLista.getMarca();
            fila[3] = productoEnLista.getRubro();
            fila[4] = productoEnLista.getPrecio();
            fila[5] = productoEnLista.getCantidad();
            model.addRow(fila);
        }
    }

	public static VistaABMProductos getVista() {
		return vista;
	}

	public static void setVista(VistaABMProductos aVista) {
		vista = aVista;
	}

	public static ArrayList<String> getFiltros() {
		return filtros;
	}

	public static void setFiltros(ArrayList<String> aFiltros) {
		filtros = aFiltros;
	}

	public static Producto getProducto() {
		return producto;
	}

	public static void setProducto(Producto aProducto) {
		producto = aProducto;
	}
}
