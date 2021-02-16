package controlador;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
//import modelo.BaseDeDatos;
import modelo.Producto;
import modelo.Venta;
import vistas.VistaVentaConfirmada;
import vistas.VistaVentaPrincipal;

public class ControladorVistaVenta {
    private static VistaVentaPrincipal vistaVentaPrincipal = new VistaVentaPrincipal();
    private static VistaVentaConfirmada vistaVentaConfirmada = new VistaVentaConfirmada();
	private static Producto producto = new Producto();
	private static Venta venta = new Venta();
    
    public static void mostrar() {
        vistaVentaPrincipal.setVisible(true);
        vistaVentaPrincipal.getLabelMontoInsuficiente().setVisible(false);
        ArrayList<Producto> productos = producto.obtenerProductos();

        DefaultTableModel modelo1 = (DefaultTableModel) vistaVentaPrincipal.getTablaProductos().getModel();
        modelo1.setNumRows(0);

        for (Producto productoEnLista : productos) {
            Object[] fila = new Object[6];

            fila[0] = productoEnLista.getId();
            fila[1] = productoEnLista.getNombre();
            fila[2] = productoEnLista.getMarca();
            fila[3] = productoEnLista.getRubro();
            fila[4] = productoEnLista.getPrecio();
            fila[5] = productoEnLista.getCantidad();
            modelo1.addRow(fila);
        }

        ArrayList<Venta> ventas = venta.obtenerVenta();

        DefaultTableModel modelo2 = (DefaultTableModel) vistaVentaPrincipal.getTablaVentas().getModel();
        modelo2.setNumRows(0);

        for (Venta ventaEnLista : ventas) {
            Object[] fila = new Object[7];

            fila[0] = ventaEnLista.getId();
            fila[1] = ventaEnLista.getNombre();
            fila[2] = ventaEnLista.getMarca();
            fila[3] = ventaEnLista.getCantidad();
            fila[4] = ventaEnLista.getPrecioUnidad();
            fila[5] = ventaEnLista.getDescuento();
            fila[6] = ventaEnLista.getSubtotal();
            modelo2.addRow(fila);
        }

    }

    public static void agregarVenta() {
        int cantidad, descuento;
        double precioUnidad, subtotal, total = 0;
        
        cantidad = Integer.parseInt(vistaVentaPrincipal.getCantidad().getText());
        precioUnidad = Double.parseDouble(vistaVentaPrincipal.getPrecioUnidad().getText());
        descuento = (Integer.parseInt(vistaVentaPrincipal.getDescuento().getText()));
      
        Venta ventaNueva = new Venta(
                Integer.parseInt(vistaVentaPrincipal.getId().getText()),
                vistaVentaPrincipal.getNombre().getText(),
                vistaVentaPrincipal.getMarca().getText(),
                Integer.parseInt(vistaVentaPrincipal.getCantidad().getText()),
                Double.parseDouble(vistaVentaPrincipal.getPrecioUnidad().getText()),
                Integer.parseInt(vistaVentaPrincipal.getDescuento().getText()),
                subtotal = precioUnidad * cantidad - (descuento * precioUnidad) / 100
        );
        venta.agregarVenta(ventaNueva); 
        
        mostrar();
       
    }

    public static void eliminarVenta() {
        double total = 0;
        
        venta.eliminarVenta(Integer.parseInt(vistaVentaPrincipal.getId().getText()));
        
        mostrar();
    
    }

    public static void calculoTotal() {

        double total = 0;

        if (vistaVentaPrincipal.getTablaVentas().getRowCount() > 0) {
            for (int i = 0; i < vistaVentaPrincipal.getTablaVentas().getRowCount(); i++) {
                total = total + Double.parseDouble(vistaVentaPrincipal.getTablaVentas().getValueAt(i, 6).toString());
            }
        }

        vistaVentaPrincipal.getTotal().setText(Double.toString(total));
    }

    public static void calculoVuelto() {

        double montoEntregado, total, vuelto = 0;

        montoEntregado = Double.parseDouble(vistaVentaPrincipal.getMontoEntregado().getText());
        total = Double.parseDouble(vistaVentaPrincipal.getTotal().getText());

        if (montoEntregado >= total) {
            vuelto = montoEntregado - total;
            vistaVentaPrincipal.getLabelMontoInsuficiente().setVisible(false);
            vistaVentaPrincipal.getVuelto().setText(Double.toString(vuelto));
            mostrarConfirmacion();
        } else {
            vistaVentaPrincipal.getLabelMontoInsuficiente().setVisible(true);
        }


    }
    
    public static void mostrarConfirmacion(){
        vistaVentaConfirmada.setVisible(true);
        
        String total, vuelto;
        
        ArrayList<Venta> ventas = venta.obtenerVenta();

        DefaultTableModel modelo = (DefaultTableModel) vistaVentaConfirmada.getTablaVentas().getModel();
        modelo.setNumRows(0);

        for (Venta ventaEnLista : ventas) {
            Object[] fila = new Object[7];

            fila[0] = ventaEnLista.getId();
            fila[1] = ventaEnLista.getNombre();
            fila[2] = ventaEnLista.getMarca();
            fila[3] = ventaEnLista.getCantidad();
            fila[4] = ventaEnLista.getPrecioUnidad();
            fila[5] = ventaEnLista.getDescuento();
            fila[6] = ventaEnLista.getSubtotal();
            modelo.addRow(fila);
        }
        
        total = vistaVentaPrincipal.getTotal().getText();
        vuelto = vistaVentaPrincipal.getVuelto().getText();
        
        vistaVentaConfirmada.getTotal().setText(total);
        vistaVentaConfirmada.getVuelto().setText(vuelto);
        
        
    }

    public static void ConfirmarDeshacerVenta(){
        venta.eliminarVentas();
        vistaVentaConfirmada.setVisible(false);
        mostrar();
    }
    
    
    
    public static void seleccionarProducto() {
        int filaSeleccionada = vistaVentaPrincipal.getTablaProductos().getSelectedRow();
        DefaultTableModel modelo = (DefaultTableModel) vistaVentaPrincipal.getTablaProductos().getModel();

        if (filaSeleccionada >= 0) {
            vistaVentaPrincipal.getId().setText(modelo.getValueAt(filaSeleccionada, 0).toString());
            vistaVentaPrincipal.getNombre().setText(modelo.getValueAt(filaSeleccionada, 1).toString());
            vistaVentaPrincipal.getMarca().setText(modelo.getValueAt(filaSeleccionada, 2).toString());
            vistaVentaPrincipal.getPrecioUnidad().setText(modelo.getValueAt(filaSeleccionada, 4).toString());
        }
    }

    public static void seleccionarVenta() {
        int filaSeleccionada = vistaVentaPrincipal.getTablaVentas().getSelectedRow();
        DefaultTableModel modelo = (DefaultTableModel) vistaVentaPrincipal.getTablaVentas().getModel();

        if (filaSeleccionada >= 0) {
            vistaVentaPrincipal.getId().setText(modelo.getValueAt(filaSeleccionada, 0).toString());
            vistaVentaPrincipal.getNombre().setText(modelo.getValueAt(filaSeleccionada, 1).toString());
            vistaVentaPrincipal.getMarca().setText(modelo.getValueAt(filaSeleccionada, 2).toString());
            vistaVentaPrincipal.getCantidad().setText(modelo.getValueAt(filaSeleccionada, 3).toString());
            vistaVentaPrincipal.getPrecioUnidad().setText(modelo.getValueAt(filaSeleccionada, 4).toString());
            vistaVentaPrincipal.getDescuento().setText(modelo.getValueAt(filaSeleccionada, 5).toString());
        }
    }

    public static void limpiarTextFields() {
        vistaVentaPrincipal.getId().setText("");
        vistaVentaPrincipal.getNombre().setText("");
        vistaVentaPrincipal.getMarca().setText("");
        vistaVentaPrincipal.getCantidad().setText("");
        vistaVentaPrincipal.getPrecioUnidad().setText("");
        vistaVentaPrincipal.getDescuento().setText("");

        vistaVentaPrincipal.getTotal().setText("");
        vistaVentaPrincipal.getMontoEntregado().setText("");
        vistaVentaPrincipal.getVuelto().setText("");
    }

	public static VistaVentaPrincipal getVistaVentaPrincipal() {
		return vistaVentaPrincipal;
	}

	public static void setVistaVentaPrincipal(VistaVentaPrincipal aVistaVentaPrincipal) {
		vistaVentaPrincipal = aVistaVentaPrincipal;
	}

	public static VistaVentaConfirmada getVistaVentaConfirmada() {
		return vistaVentaConfirmada;
	}

	public static void setVistaVentaConfirmada(VistaVentaConfirmada aVistaVentaConfirmada) {
		vistaVentaConfirmada = aVistaVentaConfirmada;
	}

	public static Producto getProducto() {
		return producto;
	}

	public static void setProducto(Producto aProducto) {
		producto = aProducto;
	}

	public static Venta getVenta() {
		return venta;
	}

	public static void setVenta(Venta aVenta) {
		venta = aVenta;
	}
}
