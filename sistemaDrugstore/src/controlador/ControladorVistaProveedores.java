package controlador;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import modelo.BaseDeDatos;
import modelo.Proveedor;
import vistas.VistaProveedores;

public class ControladorVistaProveedores {
    static VistaProveedores vista = new VistaProveedores();
    
    public static void mostrar(){
        vista.setVisible(true);
	BaseDeDatos baseDeDatos = new BaseDeDatos();
	ArrayList<Proveedor> proveedores = baseDeDatos.obtenerProveedores();
	DefaultTableModel modelo = (DefaultTableModel) vista.getTablaProveedores().getModel();

	modelo.setNumRows(0);
	
	for(Proveedor proveedor:proveedores){
		Object[] fila = new Object[6];
		
		fila[0] = proveedor.getCodigo();
		fila[1] = proveedor.getNombre();
		fila[2] = proveedor.getRubro();
		fila[3] = proveedor.getTelefono();
		fila[4] = proveedor.getCorreoElectronico();
		fila[5] = proveedor.getDireccion();
		
		modelo.addRow(fila);
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
	BaseDeDatos baseDeDatos = new BaseDeDatos();
	Proveedor proveedor = new Proveedor(
		vista.getNombre().getText(),
		vista.getRubro().getText(),
		vista.getTelefono().getText(),
		vista.getCorreoElectronico().getText(),
		vista.getDireccion().getText()  
	);
	
	baseDeDatos.agregarProveedor(proveedor);
	ControladorVistaProveedores.mostrar();
    }
    
    public static void eliminarProveedor(){
	BaseDeDatos baseDeDatos = new BaseDeDatos();
	baseDeDatos.eliminarProveedor(Integer.parseInt(vista.getCodigo().getText()));
	ControladorVistaProveedores.mostrar();
    }
    
    public static void modificarProveedor(){
	BaseDeDatos baseDeDatos = new BaseDeDatos();
	Proveedor proveedor = new Proveedor(
		Integer.parseInt(vista.getCodigo().getText()),
		vista.getNombre().getText(),
		vista.getRubro().getText(),
		vista.getTelefono().getText(),
		vista.getCorreoElectronico().getText(),
		vista.getDireccion().getText()
	);
	    
	baseDeDatos.modificarProveedor(proveedor);
	ControladorVistaProveedores.mostrar();
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
}
