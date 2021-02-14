package controlador;

import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
//import modelo.BaseDeDatos;
import modelo.Producto;
import vistas.VistaActualizarPrecios;

public class ControladorVistaActualizarPrecios {
    
    static VistaActualizarPrecios vista = new VistaActualizarPrecios();
    private static ArrayList<String> filtros = new ArrayList<>();
    
    public static void mostrar(){
        
        vista.setVisible(true);
        
	Producto producto = new Producto();
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
	Producto producto = new Producto();
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
	Producto producto = new Producto();
        
        if (modeloComboBox.getSelectedItem().equals("PORCENTAJE")) {
            double porcentaje = Double.parseDouble(vista.getCantidad().getText());
            
            producto.aumentarPrecios("PORCENTAJE", porcentaje);
            
        } else if (modeloComboBox.getSelectedItem().equals("VALOR")) {
            double valor = Double.parseDouble(vista.getCantidad().getText());
            
            producto.aumentarPrecios("VALOR", valor);
        }
        
        mostrar();
    }
    
    public static void disminuirPrecios() {
        DefaultComboBoxModel modeloComboBox = (DefaultComboBoxModel) vista.getTipoCambio().getModel();
	Producto producto = new Producto();
        
        if (modeloComboBox.getSelectedItem().equals("PORCENTAJE")) {
            double porcentaje = Double.parseDouble(vista.getCantidad().getText());
            
            producto.disminuirPrecios("PORCENTAJE", porcentaje);
            
        } else if (modeloComboBox.getSelectedItem().equals("VALOR")) {
            double valor = Double.parseDouble(vista.getCantidad().getText());
            
            producto.disminuirPrecios("VALOR", valor);
        }
        
        mostrar();
    }
    
}
