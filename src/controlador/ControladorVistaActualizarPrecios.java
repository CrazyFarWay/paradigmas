package controlador;

import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
//import modelo.BaseDeDatos;
import modelo.Producto;
import vistas.VistaActualizarPrecios;

public class ControladorVistaActualizarPrecios {
    
    private static VistaActualizarPrecios vista = new VistaActualizarPrecios();
    private static ArrayList<String> filtros = new ArrayList<>();
	private static Producto producto;
    
    public static void mostrar(){
        
        vista.setVisible(true);
        
        ArrayList<Producto> productos = producto.obtenerProductos();
        
        filtros.clear();
        filtros.addAll(producto.obtenerFiltrosRubroProductos());   

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
        
    public static void filtrarProductos() {
        ArrayList<Producto> productosFiltrados = producto.obtenerProductosFiltrados(vista.getFiltroRubro().getSelectedItem().toString());
                
        
        DefaultTableModel model = (DefaultTableModel) vista.getTablaProductos().getModel();
        model.setNumRows(0);

        for (Producto productoEnLista : productosFiltrados) {
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
    
    public static void aumentarPrecios() {
        DefaultComboBoxModel modeloComboBox = (DefaultComboBoxModel) vista.getTipoCambio().getModel();
        
        if (modeloComboBox.getSelectedItem().equals("PORCENTAJE")) {
            double porcentaje = Double.parseDouble(vista.getCantidad().getText());
            
            producto.aumentarPrecios("PORCENTAJE", porcentaje, vista.getFiltroRubro().getSelectedItem().toString());
            
        } else if (modeloComboBox.getSelectedItem().equals("VALOR")) {
            double valor = Double.parseDouble(vista.getCantidad().getText());
            
            producto.aumentarPrecios("VALOR", valor, vista.getFiltroRubro().getSelectedItem().toString());
        }
        
        mostrar();
    }
    
    public static void disminuirPrecios() {
        DefaultComboBoxModel modeloComboBox = (DefaultComboBoxModel) vista.getTipoCambio().getModel();
        
        if (modeloComboBox.getSelectedItem().equals("PORCENTAJE")) {
            double porcentaje = Double.parseDouble(vista.getCantidad().getText());
            
            producto.disminuirPrecios("PORCENTAJE", porcentaje, vista.getFiltroRubro().getSelectedItem().toString());
            
        } else if (modeloComboBox.getSelectedItem().equals("VALOR")) {
            double valor = Double.parseDouble(vista.getCantidad().getText());
            
            producto.disminuirPrecios("VALOR", valor, vista.getFiltroRubro().getSelectedItem().toString());
        }
        
        mostrar();
    }

	public static VistaActualizarPrecios getVista() {
		return vista;
	}

	public static void setVista(VistaActualizarPrecios aVista) {
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
