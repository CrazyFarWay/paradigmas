package controlador;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import modelo.BaseDeDatos;
import modelo.Producto;
import vistas.VistaInventario;

public class ControladorVistaInventario {

    static VistaInventario vista = new VistaInventario();

    public static void mostrar() {
        vista.setVisible(true);

        BaseDeDatos baseDeDatos = new BaseDeDatos();
        ArrayList<Producto> productos = baseDeDatos.obtenerProductos();

        DefaultTableModel model = (DefaultTableModel) vista.getTablaInventario().getModel();
        model.setNumRows(0);

        for (Producto producto : productos) {
            Object[] fila = new Object[3];

            fila[0] = producto.getId();
            fila[1] = producto.getNombre();
            fila[2] = producto.getMarca();
            fila[3] = producto.getPrecio();
            fila[4] = producto.getCantidad();
            model.addRow(fila);
        }
    }

    public static void botonAgregar() {

        int id = Integer.parseInt(vista.getId().getText());
        String nombre = vista.getNombre().getText();
        String marca = vista.getMarca().getText();
        double precio = Double.parseDouble(vista.getPrecio().getText());
        int cantidad = Integer.parseInt(vista.getCantidad().getText());

        Producto prod = new Producto();
        prod.setId(id);
        prod.setNombre(nombre);
        prod.setMarca(marca);
        prod.setPrecio(precio);
        prod.setCantidad(cantidad);

        BaseDeDatos baseDeDatos = new BaseDeDatos();

        baseDeDatos.agregarProducto(prod);
        mostrar();

    }

    public static void botonModificar() {

        int id = Integer.parseInt(vista.getId().getText());
        String nombre = vista.getNombre().getText();
        String marca = vista.getMarca().getText();
        double precio = Double.parseDouble(vista.getPrecio().getText());
        int cantidad = Integer.parseInt(vista.getCantidad().getText());

        Producto prod = new Producto();
        prod.setId(id);
        prod.setNombre(nombre);
        prod.setMarca(marca);
        prod.setPrecio(precio);
        prod.setCantidad(cantidad);

        BaseDeDatos baseDeDatos = new BaseDeDatos();

        baseDeDatos.modificarProducto(prod);
        mostrar();
    }

    public static void botonEliminar() {
        
        int id = Integer.parseInt(vista.getId().getText());

        BaseDeDatos baseDeDatos = new BaseDeDatos();

        baseDeDatos.eliminarProducto(id);
        mostrar();

    }

}
