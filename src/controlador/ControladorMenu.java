package controlador;

import javax.swing.JDialog;
import javax.swing.JFrame;
import modelo.*;

public class ControladorMenu {
	private static Producto producto;
	private static Proveedor proveedor;
	private static LineaDeVenta venta;

	public static void abrirProveedores(JFrame vista) {
		vista.dispose();
		ControladorVistaProveedores.setProveedor(proveedor);
		ControladorVistaProveedores.mostrar();
	}

	public static void abrirABM(JFrame vista) {
		vista.dispose();
		ControladorVistaABMProductos.setProducto(producto);
		ControladorVistaABMProductos.mostrar();
	}

	public static void salirDelPrograma() {
		System.exit(0);
	}

	public static void abrirVentas(JFrame vista) {
		vista.dispose();
		ControladorVistaVenta.setProducto(producto);
		ControladorVistaVenta.setLineaDeVenta(venta);
		ControladorVistaVenta.mostrar();
	}
        
        public static void abrirActualizarPrecios(JFrame vista){
                vista.dispose();
		ControladorVistaActualizarPrecios.setProducto(producto);
                ControladorVistaActualizarPrecios.mostrar();
        }

	public static void cerrarSesion(JFrame vista) {
            vista.dispose();
       //     GestionConexion.cerrarSesion();
            
            try {
         //       GestionConexion.inicializar();
            } catch (Exception ex) {
                System.out.println(ex);
            }
	}
        
	public static void cerrarSesion(JDialog vista) {
            vista.dispose();
       //     GestionConexion.cerrarSesion();
            
            try {
         //       GestionConexion.inicializar();
            } catch (Exception ex) {
                System.out.println(ex);
            }
	}

	public static void abrirProveedoresDesdeInicio() {
		
		ControladorVistaProveedores.setProveedor(proveedor);
		ControladorVistaProveedores.mostrar();
	}

	public static void abrirABMDesdeInicio() {
		
		ControladorVistaABMProductos.setProducto(producto);
		ControladorVistaABMProductos.mostrar();
	}

	public static void abrirVentasDesdeInicio() {
		
		ControladorVistaVenta.setProducto(producto);
		ControladorVistaVenta.setLineaDeVenta(venta);
		ControladorVistaVenta.mostrar();
	}
        
        public static void abrirActualizarPreciosDesdeInicio(){
                
		ControladorVistaActualizarPrecios.setProducto(producto);
                ControladorVistaActualizarPrecios.mostrar();
        }

	public static Producto getProducto() {
		return producto;
	}

	public static void setProducto(Producto aProducto) {
		producto = aProducto;
	}

	public static Proveedor getProveedor() {
		return proveedor;
	}

	public static void setProveedor(Proveedor aProveedor) {
		proveedor = aProveedor;
	}

	public static LineaDeVenta getVenta() {
		return venta;
	}

	public static void setVenta(LineaDeVenta aVenta) {
		venta = aVenta;
	}
    
}
