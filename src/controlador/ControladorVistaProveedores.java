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
		Object[] fila = new Object[4];
		
		fila[0] = proveedor.getCodigo();
		fila[1] = proveedor.getNombre();
		fila[2] = proveedor.getRubro();
		fila[3] = proveedor.getTelefono();
		fila[4] = proveedor.getCorreoElectronico();
		fila[5] = proveedor.getDireccion();
		
		modelo.addRow(fila);
	}
    }
    
    public static void agregarProveedor(){
	BaseDeDatos baseDeDatos = new BaseDeDatos();
	Proveedor proveedor = new Proveedor(
		Integer.parseInt(vista.getCodigo().getText()),
		vista.getNombre().getText(),
		vista.getRubro().getText(),
		vista.getTelefono().getText(),
		vista.getCorreoElectronico().getText(),
		vista.getDireccion().getText()  
	);
	
	baseDeDatos.agregarProveedor(proveedor);
    }
    
    public static void eliminarProveedor(){
	    BaseDeDatos baseDeDatos = new BaseDeDatos();
	    baseDeDatos.eliminarProveedor(Integer.parseInt(vista.getCodigo().getText()));
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
    }
    
    public static void hacerUnPedido(){
	    vista.dispose();
	    ControladorVistaPedidos.mostrar();
    }
}
