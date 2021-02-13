package controlador;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultComboBoxModel;
import modelo.BaseDeDatos;
import modelo.Proveedor;
import vistas.VistaProveedores;

public class ControladorVistaProveedores {
    private static VistaProveedores vista = new VistaProveedores();
    private static ArrayList<Proveedor> proveedores;
    private static ArrayList<String> filtros;
    
    public static void mostrar(){
        vista.setVisible(true);
	proveedores = GestionConexion.obtenerProveedores();
        
        filtros = GestionConexion.obtenerFiltrosRubroProveedores();    
        
	DefaultTableModel modeloTabla = (DefaultTableModel) vista.getTablaProveedores().getModel();
        DefaultComboBoxModel modeloComboBox = (DefaultComboBoxModel) vista.getFiltroRubro().getModel();

	modeloTabla.setNumRows(0);
	
	for(Proveedor proveedor:proveedores){
		Object[] fila = new Object[6];
		
		fila[0] = proveedor.getCodigo();
		fila[1] = proveedor.getNombre();
		fila[2] = proveedor.getRubro();
		fila[3] = proveedor.getTelefono();
		fila[4] = proveedor.getCorreoElectronico();
		fila[5] = proveedor.getDireccion();
		
		modeloTabla.addRow(fila);
	}
       
        for(String filtro: filtros) {
            if (modeloComboBox.getIndexOf(filtro) == -1) {
                    modeloComboBox.addElement(filtro);
            }
        }
    }

    public static void limpiarTextFields(){
	vista.getCodigo().setText("");
	vista.getNombre().setText("");
	vista.getRubro().setText("");
	vista.getTelefono().setText("");
	vista.getCorreoElectronico().setText("");
	vista.getDireccion().setText("");
    }
    
    public static void agregarProveedor(){
	Proveedor proveedor = new Proveedor(
		vista.getNombre().getText(),
		vista.getRubro().getText().toUpperCase(),
		vista.getTelefono().getText(),
		vista.getCorreoElectronico().getText(),
		vista.getDireccion().getText()  
	);
	
	GestionConexion.agregarProveedor(proveedor);
	mostrar();
    }
    
    public static void eliminarProveedor(){
	GestionConexion.eliminarProveedor(Integer.parseInt(vista.getCodigo().getText()));
	mostrar();
    }
    
    public static void modificarProveedor(){
	Proveedor proveedor = new Proveedor(
		Integer.parseInt(vista.getCodigo().getText()),
		vista.getNombre().getText(),
		vista.getRubro().getText().toUpperCase(),
		vista.getTelefono().getText(),
		vista.getCorreoElectronico().getText(),
		vista.getDireccion().getText()
	);
	
	GestionConexion.modificarProveedor(proveedor);
	mostrar();
    }
    
    public static void hacerUnPedido(){
	String mail = vista.getCorreoElectronico().getText();
	String asunto = "Pedido de "+ vista.getRubro().getText() + " por parte del Drugstore";

	ControladorVistaPedidos.rellenarDatos(mail, asunto);

	vista.dispose();
	ControladorVistaPedidos.mostrar();
    }

    public static void seleccionarProveedor(){
	int filaSeleccionada = vista.getTablaProveedores().getSelectedRow();
	DefaultTableModel modelo = (DefaultTableModel) vista.getTablaProveedores().getModel();

	if(filaSeleccionada >= 0){
		vista.getCodigo().setText(modelo.getValueAt(filaSeleccionada, 0).toString());
		vista.getNombre().setText(modelo.getValueAt(filaSeleccionada, 1).toString());
		vista.getRubro().setText(modelo.getValueAt(filaSeleccionada, 2).toString());
		vista.getDireccion().setText(modelo.getValueAt(filaSeleccionada, 3).toString());
		vista.getCorreoElectronico().setText(modelo.getValueAt(filaSeleccionada, 4).toString());
		vista.getTelefono().setText(modelo.getValueAt(filaSeleccionada, 5).toString());
	}
    }
    
    public static void filtrarProveedores() {
        ArrayList<Proveedor> proveedoresFiltrados = GestionConexion.obtenerProveedoresFiltrados(
                vista.getFiltroRubro().getSelectedItem().toString()
        );
                
        
        DefaultTableModel model = (DefaultTableModel) vista.getTablaProveedores().getModel();
        model.setNumRows(0);

        for (Proveedor proveedor : proveedoresFiltrados) {
            Object[] fila = new Object[6];

            fila[0] = proveedor.getCodigo();
            fila[1] = proveedor.getNombre();
            fila[2] = proveedor.getRubro();
            fila[3] = proveedor.getTelefono();
            fila[4] = proveedor.getCorreoElectronico();
            fila[5] = proveedor.getDireccion();
            model.addRow(fila);
        }
    }
}
