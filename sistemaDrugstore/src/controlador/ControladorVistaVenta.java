package controlador;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import modelo.BaseDeDatos;
import modelo.Producto;
import modelo.Venta;
import vistas.VistaVentaConfirmada;
import vistas.VistaVentaPrincipal;

public class ControladorVistaVenta {

    static VistaVentaPrincipal vistaVentaPrincipal = new VistaVentaPrincipal();
    static VistaVentaConfirmada vistaVentaConfirmada = new VistaVentaConfirmada();
    
    public static void mostrar() {
        vistaVentaPrincipal.setVisible(true);
        vistaVentaPrincipal.getLabelMontoInsuficiente().setVisible(false);

        BaseDeDatos baseDeDatos = new BaseDeDatos();
        ArrayList<Producto> productos = baseDeDatos.obtenerProductos();

        DefaultTableModel modelo1 = (DefaultTableModel) vistaVentaPrincipal.getTablaProductos().getModel();
        modelo1.setNumRows(0);

        for (Producto producto : productos) {
            Object[] fila = new Object[6];

            fila[0] = producto.getId();
            fila[1] = producto.getNombre();
            fila[2] = producto.getMarca();
            fila[3] = producto.getRubro();
            fila[4] = producto.getPrecio();
            fila[5] = producto.getCantidad();
            modelo1.addRow(fila);
        }

        ArrayList<Venta> ventas = baseDeDatos.obtenerVenta();

        DefaultTableModel modelo2 = (DefaultTableModel) vistaVentaPrincipal.getTablaVentas().getModel();
        modelo2.setNumRows(0);

        for (Venta venta : ventas) {
            Object[] fila = new Object[7];

            fila[0] = venta.getId();
            fila[1] = venta.getNombre();
            fila[2] = venta.getMarca();
            fila[3] = venta.getCantidad();
            fila[4] = venta.getPrecioUnidad();
            fila[5] = venta.getDescuento();
            fila[6] = venta.getSubtotal();
            modelo2.addRow(fila);
        }

    }

    public static void agregarVenta() {
        int cantidad, descuento;
        double precioUnidad, subtotal, total = 0;
        
        cantidad = Integer.parseInt(vistaVentaPrincipal.getCantidad().getText());
        precioUnidad = Double.parseDouble(vistaVentaPrincipal.getPrecioUnidad().getText());
        descuento = (Integer.parseInt(vistaVentaPrincipal.getDescuento().getText()));
      
        
        BaseDeDatos baseDeDatos = new BaseDeDatos();
        Venta venta = new Venta(
                Integer.parseInt(vistaVentaPrincipal.getId().getText()),
                vistaVentaPrincipal.getNombre().getText(),
                vistaVentaPrincipal.getMarca().getText(),
                Integer.parseInt(vistaVentaPrincipal.getCantidad().getText()),
                Double.parseDouble(vistaVentaPrincipal.getPrecioUnidad().getText()),
                Integer.parseInt(vistaVentaPrincipal.getDescuento().getText()),
                subtotal = precioUnidad * cantidad - (descuento * precioUnidad) / 100
        );
        baseDeDatos.agregarVenta(venta); 
        
        //calculoTotal();
        mostrar();
       
    }

    public static void eliminarVenta() {
        
        double total = 0;
        
        BaseDeDatos baseDeDatos = new BaseDeDatos();
        baseDeDatos.eliminarVenta(Integer.parseInt(vistaVentaPrincipal.getId().getText()));
        
       // calculoTotal();
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
        } else {
            vistaVentaPrincipal.getLabelMontoInsuficiente().setVisible(true);
        }

        vistaVentaPrincipal.getVuelto().setText(Double.toString(vuelto));
        mostrarConfirmacion();

    }
    
    public static void mostrarConfirmacion(){
        vistaVentaConfirmada.setVisible(true);
        
        String total, vuelto;
        
        BaseDeDatos baseDeDatos = new BaseDeDatos();
        ArrayList<Venta> ventas = baseDeDatos.obtenerVenta();

        DefaultTableModel modelo = (DefaultTableModel) vistaVentaConfirmada.getTablaVentas().getModel();
        modelo.setNumRows(0);

        for (Venta venta : ventas) {
            Object[] fila = new Object[7];

            fila[0] = venta.getId();
            fila[1] = venta.getNombre();
            fila[2] = venta.getMarca();
            fila[3] = venta.getCantidad();
            fila[4] = venta.getPrecioUnidad();
            fila[5] = venta.getDescuento();
            fila[6] = venta.getSubtotal();
            modelo.addRow(fila);
        }
        
        total = vistaVentaPrincipal.getTotal().getText();
        vuelto = vistaVentaPrincipal.getVuelto().getText();
        
        vistaVentaConfirmada.getTotal().setText(total);
        vistaVentaConfirmada.getVuelto().setText(vuelto);
        
        
    }

    public static void ConfirmarDeshacerVenta(){
        
        BaseDeDatos baseDeDatos = new BaseDeDatos();
        baseDeDatos.eliminarVentas();
        
        mostrarConfirmacion();
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
}
