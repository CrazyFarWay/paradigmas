package controlador;

import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import modelo.BaseDeDatos;
import modelo.Producto;
import vistas.VistaActualizarPrecios;

public class ControladorVistaActualizarPrecios {
    
    static VistaActualizarPrecios vista = new VistaActualizarPrecios();
    private static ArrayList<String> filtros = new ArrayList<>();
    
    public static void mostrar(){
        
        vista.setVisible(true);
        
        ArrayList<Producto> productos = GestionConexion.obtenerProductos();
        
        filtros.clear();
        filtros.addAll(GestionConexion.obtenerFiltrosRubroProductos());   

        DefaultTableModel model = (DefaultTableModel) vista.getTablaProductos().getModel();
        DefaultComboBoxModel modeloComboBox = (DefaultComboBoxModel) vista.getFiltroRubro().getModel();
        
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
        
        for(String filtro: filtros) {
            if (modeloComboBox.getIndexOf(filtro) == -1) {
                    modeloComboBox.addElement(filtro);
            }
        }
    }
        
    public static void filtrarProductos() {
        ArrayList<Producto> productosFiltrados = GestionConexion.obtenerProductosFiltrados(vista.getFiltroRubro().getSelectedItem().toString());
                
        
        DefaultTableModel model = (DefaultTableModel) vista.getTablaProductos().getModel();
        model.setNumRows(0);

        for (Producto producto : productosFiltrados) {
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
    
    public static void aumentarPrecios() {
        DefaultComboBoxModel modeloComboBox = (DefaultComboBoxModel) vista.getTipoCambio().getModel();
        
        if (modeloComboBox.getSelectedItem().equals("PORCENTAJE")) {
            double porcentaje = Double.parseDouble(vista.getCantidad().getText());
            
            GestionConexion.aumentarPrecios("PORCENTAJE", porcentaje);
            
        } else if (modeloComboBox.getSelectedItem().equals("VALOR")) {
            double valor = Double.parseDouble(vista.getCantidad().getText());
            
            GestionConexion.aumentarPrecios("VALOR", valor);
        }
        
        mostrar();
    }
    
    public static void disminuirPrecios() {
        DefaultComboBoxModel modeloComboBox = (DefaultComboBoxModel) vista.getTipoCambio().getModel();
        
        if (modeloComboBox.getSelectedItem().equals("PORCENTAJE")) {
            double porcentaje = Double.parseDouble(vista.getCantidad().getText());
            
            GestionConexion.disminuirPrecios("PORCENTAJE", porcentaje);
            
        } else if (modeloComboBox.getSelectedItem().equals("VALOR")) {
            double valor = Double.parseDouble(vista.getCantidad().getText());
            
            GestionConexion.disminuirPrecios("VALOR", valor);
        }
        
        mostrar();
    }
    
}
