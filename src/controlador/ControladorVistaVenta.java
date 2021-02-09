package controlador;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import modelo.BaseDeDatos;
import modelo.Producto;
import modelo.Venta;
import vistas.VistaVentaPrincipal;

public class ControladorVistaVenta {

    static VistaVentaPrincipal vista = new VistaVentaPrincipal();

    public static void mostrar() {
        vista.setVisible(true);

        BaseDeDatos baseDeDatos = new BaseDeDatos();
        ArrayList<Producto> productos = baseDeDatos.obtenerProductos();

        DefaultTableModel model = (DefaultTableModel) vista.getTablaProductos().getModel();
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

        ArrayList<Venta> ventas = baseDeDatos.obtenerVenta();

        DefaultTableModel model2 = (DefaultTableModel) vista.getTablaVentas().getModel();
        model2.setNumRows(0);

        for (Venta venta : ventas) {
            Object[] fila = new Object[7];

            fila[0] = venta.getId();
            fila[1] = venta.getNombre();
            fila[2] = venta.getMarca();
            fila[3] = venta.getCantidad();
            fila[4] = venta.getPrecioUnidad();
            fila[5] = venta.getDescuento();
            fila[6] = venta.getSubtotal();
            model2.addRow(fila);

        }

    }

    public static void agregarVenta() {
        int cantidad, descuento;
        Double precioUnidad, subtotal;

        cantidad = Integer.parseInt(vista.getCantidad().getText());
        precioUnidad = Double.parseDouble(vista.getPrecioUnidad().getText());
        descuento = (Integer.parseInt(vista.getDescuento().getText()));

        BaseDeDatos baseDeDatos = new BaseDeDatos();
        Venta venta = new Venta(
                Integer.parseInt(vista.getId().getText()),
                vista.getNombre().getText(),
                vista.getMarca().getText(),
                Integer.parseInt(vista.getCantidad().getText()),
                Double.parseDouble(vista.getPrecioUnidad().getText()),
                Integer.parseInt(vista.getDescuento().getText()),
                subtotal = precioUnidad * cantidad - (descuento*precioUnidad)/100
        );

        baseDeDatos.agregarVenta(venta);
        mostrar();
    }

    public static void eliminarVenta() {

        BaseDeDatos baseDeDatos = new BaseDeDatos();
        baseDeDatos.eliminarVenta(Integer.parseInt(vista.getId().getText()));
        mostrar();

    }
    
    public static void calculoTotalYVuelto(){
        //ver como limpiar los jtextfields
        double montoEntregado, total, vuelto;
        
        montoEntregado = Double.parseDouble(vista.getMontoEntregado().getText());
        
        int filaSeleccionada = vista.getTablaVentas().getSelectedRow();
        DefaultTableModel modelo = (DefaultTableModel) vista.getTablaVentas().getModel();
        
        //for each recorrer tabla y sumar el subtotal. Ese seria el total.
        //vuelto = monto entregado - total;
        //vista.getVuelto().setText(vuelto); toString?
        
    }

    public static void seleccionarProducto() {
        int filaSeleccionada = vista.getTablaProductos().getSelectedRow();
        DefaultTableModel modelo = (DefaultTableModel) vista.getTablaProductos().getModel();

        if (filaSeleccionada >= 0) {
            vista.getId().setText(modelo.getValueAt(filaSeleccionada, 0).toString());
            vista.getNombre().setText(modelo.getValueAt(filaSeleccionada, 1).toString());
            vista.getMarca().setText(modelo.getValueAt(filaSeleccionada, 2).toString());
            vista.getPrecioUnidad().setText(modelo.getValueAt(filaSeleccionada, 3).toString());
        }
    }

    public static void seleccionarVenta() {
        int filaSeleccionada = vista.getTablaVentas().getSelectedRow();
        DefaultTableModel modelo = (DefaultTableModel) vista.getTablaVentas().getModel();

        if (filaSeleccionada >= 0) {
            vista.getId().setText(modelo.getValueAt(filaSeleccionada, 0).toString());
            vista.getNombre().setText(modelo.getValueAt(filaSeleccionada, 1).toString());
            vista.getMarca().setText(modelo.getValueAt(filaSeleccionada, 2).toString());
            vista.getCantidad().setText(modelo.getValueAt(filaSeleccionada, 3).toString());
            vista.getPrecioUnidad().setText(modelo.getValueAt(filaSeleccionada, 4).toString());
            vista.getDescuento().setText(modelo.getValueAt(filaSeleccionada, 5).toString());
        }
    }

    public static void limpiarTextFields() {
        vista.getId().setText("");
        vista.getNombre().setText("");
        vista.getMarca().setText("");
        vista.getCantidad().setText("");
        vista.getPrecioUnidad().setText("");
        vista.getDescuento().setText("");
    }
}
