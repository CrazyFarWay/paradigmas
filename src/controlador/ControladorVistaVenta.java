package controlador;

import java.util.ArrayList;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
//import modelo.BaseDeDatos;
import modelo.*;
import vistas.VistaVentaConfirmada;
import vistas.VistaVentaPrincipal;

public class ControladorVistaVenta {

    private static VistaVentaPrincipal vistaVentaPrincipal = new VistaVentaPrincipal();
    private static VistaVentaConfirmada vistaVentaConfirmada = new VistaVentaConfirmada();
    private static Producto producto = new Producto();
    private static LineaDeVenta lineaDeVenta = new LineaDeVenta();
    private static Venta venta = new Venta();
    private static Cliente cliente = new Cliente();

    public static void mostrar() {
        vistaVentaPrincipal.setVisible(true);
        vistaVentaPrincipal.getLabelMontoInsuficiente().setVisible(false);
        ArrayList<Producto> productos = producto.obtenerProductos();
        ArrayList<Cliente> clientes = cliente.obtenerClientes();

        DefaultComboBoxModel modeloComboBox = (DefaultComboBoxModel) vistaVentaPrincipal.getComboBoxCliente().getModel();

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

        ArrayList<LineaDeVenta> lineasDeVenta = lineaDeVenta.obtenerLineasDeVenta();

        DefaultTableModel modelo2 = (DefaultTableModel) vistaVentaPrincipal.getTablaVentas().getModel();
        modelo2.setNumRows(0);

        for (LineaDeVenta ventaEnLista : lineasDeVenta) {
            Object[] fila = new Object[7];

            fila[0] = ventaEnLista.getId();
            fila[1] = ventaEnLista.getProducto().getNombre();
            fila[2] = ventaEnLista.getProducto().getMarca();
            fila[3] = ventaEnLista.getCantidad();
            fila[4] = ventaEnLista.getProducto().getPrecio();
            fila[5] = ventaEnLista.getDescuento();
            fila[6] = ventaEnLista.getSubtotal();
            modelo2.addRow(fila);
        }

        for (Cliente clienteCombo : clientes) {
            if (modeloComboBox.getIndexOf("id: " + clienteCombo.getId() + " - nombre: " + clienteCombo.getNombre()) == -1) {
                modeloComboBox.addElement("id: " + clienteCombo.getId() + " - nombre: " + clienteCombo.getNombre());
            }
        }

    }

    public static void agregarLineaDeVenta() {
        int cantidad, descuento;
        double precioUnidad, subtotal, total = 0;
        Producto productoEnLineaDeVenta = ControladorVistaVenta.obtenerProductoDeTabla(Integer.parseInt(vistaVentaPrincipal.getId().getText()));

        cantidad = Integer.parseInt(vistaVentaPrincipal.getCantidad().getText());
        precioUnidad = productoEnLineaDeVenta.getPrecio();
        descuento = (Integer.parseInt(vistaVentaPrincipal.getDescuento().getText()));
        subtotal = precioUnidad * cantidad - (descuento * precioUnidad) / 100;

        LineaDeVenta lineaDeVentaNueva = new LineaDeVenta(
                Integer.parseInt(vistaVentaPrincipal.getId().getText()),
                productoEnLineaDeVenta,
                Integer.parseInt(vistaVentaPrincipal.getCantidad().getText()),
                Integer.parseInt(vistaVentaPrincipal.getDescuento().getText()),
                subtotal
        );

        System.out.println("LINEA A AGREGAR");
        System.out.println(lineaDeVentaNueva);

        lineaDeVenta.agregarLineaDeVenta(lineaDeVentaNueva);

        mostrar();
        ControladorVistaVenta.calculoTotal();

    }

    public static void eliminarLineaDeVenta() {
        double total = 0;

        lineaDeVenta.eliminarLineaDeVenta(Integer.parseInt(vistaVentaPrincipal.getId().getText()));

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
        Venta ventaNueva = new Venta();
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

    public static void mostrarConfirmacion() {
        vistaVentaConfirmada.setVisible(true);

        String total, vuelto;

        ArrayList<LineaDeVenta> ventas = lineaDeVenta.obtenerLineasDeVenta();

        DefaultTableModel modelo = (DefaultTableModel) vistaVentaConfirmada.getTablaVentas().getModel();
        modelo.setNumRows(0);

        for (LineaDeVenta ventaEnLista : ventas) {
            Object[] fila = new Object[7];

            fila[0] = ventaEnLista.getId();
            fila[1] = ventaEnLista.getProducto().getNombre();
            fila[2] = ventaEnLista.getProducto().getMarca();
            fila[3] = ventaEnLista.getCantidad();
            fila[4] = ventaEnLista.getProducto().getPrecio();
            fila[5] = ventaEnLista.getDescuento();
            fila[6] = ventaEnLista.getSubtotal();
            modelo.addRow(fila);
        }

        total = vistaVentaPrincipal.getTotal().getText();
        vuelto = vistaVentaPrincipal.getVuelto().getText();

        vistaVentaConfirmada.getTotal().setText(total);
        vistaVentaConfirmada.getVuelto().setText(vuelto);
    }

    public static void deshacerVenta() {
        lineaDeVenta.eliminarLineasDeVenta();
        vistaVentaConfirmada.setVisible(false);
        mostrar();
    }

    public static void confirmarVenta() {

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

    public static void seleccionarLineaDeVenta() {
        int filaSeleccionada = vistaVentaPrincipal.getTablaLineasDeVenta().getSelectedRow();

        System.out.println("FILA SELECCIONADA");
        System.out.println(filaSeleccionada);
        DefaultTableModel modelo = (DefaultTableModel) vistaVentaPrincipal.getTablaLineasDeVenta().getModel();

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

    public static Producto obtenerProductoDeTabla(int id) {
        ArrayList<Producto> productos = ControladorVistaVenta.obtenerProductosDeTabla();

        for (Producto productoEnFila : productos) {
            if (productoEnFila.getId() == id) {
                return productoEnFila;
            }
        }

        return (new Producto());
    }

    public static ArrayList<Producto> obtenerProductosDeTabla() {
        ArrayList<Producto> productos = new ArrayList<>();
        DefaultTableModel modelo = (DefaultTableModel) vistaVentaPrincipal.getTablaProductos().getModel();

        for (int i = 0; i < modelo.getRowCount(); i++) {
            Producto productoDeFila;
            productoDeFila = new Producto(
                    producto.getGestionConexion(),
                    (Integer) modelo.getValueAt(i, 0),
                    (String) modelo.getValueAt(i, 1),
                    (String) modelo.getValueAt(i, 2),
                    (String) modelo.getValueAt(i, 3),
                    (Double) modelo.getValueAt(i, 4),
                    (Integer) modelo.getValueAt(i, 5)
            );

            productos.add(productoDeFila);
        }

        return productos;
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

    public static LineaDeVenta getLineaDeVenta() {
        return lineaDeVenta;
    }

    public static void setLineaDeVenta(LineaDeVenta aVenta) {
        lineaDeVenta = aVenta;
    }

    public static Cliente getCliente() {
        return cliente;
    }

    public static void setCliente(Cliente cliente) {
        ControladorVistaVenta.cliente = cliente;
    }

}
