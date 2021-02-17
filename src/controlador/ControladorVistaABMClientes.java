package controlador;

/**
 *
 * @author ValeS
 */

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import modelo.Cliente;
import vistas.VistaABMClientes;


public class ControladorVistaABMClientes {

    private static VistaABMClientes vista = new VistaABMClientes();
    private static Cliente cliente;

    public static void mostrar() {
        vista.setVisible(true);

        ArrayList<Cliente> clientes = cliente.obtenerClientes();

        DefaultTableModel modelo = (DefaultTableModel) vista.getTablaClientes().getModel();

        modelo.setNumRows(0);

        for (Cliente clienteEnLista : clientes) {
            Object[] fila = new Object[6];

            fila[0] = clienteEnLista.getId();
            fila[1] = clienteEnLista.getNombre();
            fila[2] = clienteEnLista.getDni();
            fila[3] = clienteEnLista.getTipo();
            fila[4] = clienteEnLista.getTelefono();
            fila[5] = clienteEnLista.getDireccion();
            modelo.addRow(fila);
        }

    }

    public static void agregarCliente() {
        Cliente clienteNuevo = new Cliente(
                vista.getNombre().getText(),
                vista.getDni().getText(),
                vista.getTipo().getText().toUpperCase(),
                vista.getTelefono().getText(),
                vista.getDireccion().getText()
        );

        cliente.agregarCliente(clienteNuevo);
        mostrar();
    }

    public static void modificarCliente() {
        Cliente clienteNuevo = new Cliente(
                vista.getNombre().getText(),
                vista.getDni().getText(),
                vista.getTipo().getText().toUpperCase(),
                vista.getTelefono().getText(),
                vista.getDireccion().getText()
        );

        cliente.modificarCliente(clienteNuevo);
        mostrar();
    }

    public static void eliminarCliente() {

        cliente.eliminarCliente(Integer.parseInt(vista.getId().getText()));
        mostrar();

    }

    public static void limpiarTextFields() {
        vista.getId().setText("");
        vista.getNombre().setText("");
        vista.getDni().setText("");
        vista.getTipo().setText("");
        vista.getTelefono().setText("");
        vista.getDireccion().setText("");
    }

    public static void seleccionarCliente() {
        int filaSeleccionada = vista.getTablaClientes().getSelectedRow();
        DefaultTableModel modelo = (DefaultTableModel) vista.getTablaClientes().getModel();

        if (filaSeleccionada >= 0) {
            vista.getId().setText(modelo.getValueAt(filaSeleccionada, 0).toString());
            vista.getNombre().setText(modelo.getValueAt(filaSeleccionada, 1).toString());
            vista.getDni().setText(modelo.getValueAt(filaSeleccionada, 2).toString());
            vista.getTipo().setText(modelo.getValueAt(filaSeleccionada, 3).toString());
            vista.getTelefono().setText(modelo.getValueAt(filaSeleccionada, 4).toString());
            vista.getDireccion().setText(modelo.getValueAt(filaSeleccionada, 5).toString());
        }
    }

    public static VistaABMClientes getVista() {
        return vista;
    }

    public static void setVista(VistaABMClientes aVista) {
        vista = aVista;
    }

    public static Cliente getCliente() {
        return cliente;
    }

    public static void setCliente(Cliente aCliente) {
        cliente = aCliente;
    }

    
    
}
