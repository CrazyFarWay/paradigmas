/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.BaseDeDatos;
import modelo.Producto;
import modelo.Proveedor;
import modelo.Usuario;
import modelo.Venta;
import vistas.*;

/**
 *
 * @author CrazyFarWay
 */
public class GestionConexion {
    private static BaseDeDatos baseDeDatos;
    private static String usuario;
    private static String contraseña;

    public static void inicializar() throws Exception {
        conectar();
    }

    public static void conectar() throws SQLException, ClassNotFoundException, Exception {
        ControladorVistaLogin.mostrar();
        
        while (true) {
            
            System.out.println("Esperando loguear");
            
            if (ControladorVistaLogin.getReturnStatus() == 1) {
                
                baseDeDatos = new BaseDeDatos(ControladorVistaLogin.getUsuario(), ControladorVistaLogin.getContraseña());
               
                try {
                    baseDeDatos.conectar();
                    System.out.println("Conecto con sistema drugstore satisfractoriamente ...");
                    ControladorVistaLogin.ocultar();
                    ControladorVistaInicial.mostrar();
                    break;
                } catch (SQLException | ClassNotFoundException e) {
                    ControladorVistaLogin.mostrarErrorDatosIncorrectos();
                    ControladorVistaLogin.setReturnStatus(0);
                }
            }
        }
    }
    
    
    public static void agregarProducto(Producto producto) {
        baseDeDatos.agregarProducto(producto);
    }

    public static void eliminarProducto(int id) {
        baseDeDatos.eliminarProducto(id);
    }

    public static void modificarProducto(Producto producto) {
        baseDeDatos.modificarProducto(producto);
    }

    public static ArrayList<Producto> obtenerProductos() {
        ArrayList<Producto> productos = baseDeDatos.obtenerProductos();
        return productos;
    }

    public static void agregarProveedor(Proveedor proveedor) {
        baseDeDatos.agregarProveedor(proveedor);
    }

    public static void eliminarProveedor(int codigo) {
        baseDeDatos.eliminarProveedor(codigo);
    }

    public static void modificarProveedor(Proveedor proveedor) {
        baseDeDatos.modificarProveedor(proveedor);
    }

    public static ArrayList<Proveedor> obtenerProveedores() {
        ArrayList<Proveedor> proveedores = baseDeDatos.obtenerProveedores();
        return proveedores;
    }

    public static ArrayList<Venta> obtenerVenta() {
        ArrayList<Venta> ventas = baseDeDatos.obtenerVenta();
        return ventas;
    }

    public static void agregarVenta(Venta venta) {
        baseDeDatos.agregarVenta(venta);
    }

    public static void eliminarVenta(int id) {
        baseDeDatos.eliminarVenta(id);
    }
    
    public static void eliminarVentas(){
        baseDeDatos.eliminarVentas();
    }
    
    public static ArrayList<Producto> obtenerProductosFiltrados(String precio, String rubro) {
        ArrayList<Producto> productos = baseDeDatos.obtenerProductosFiltrados(precio, rubro);
        return productos;
    }
    
    public static ArrayList<Producto> obtenerProductosFiltrados(String rubro) {
        ArrayList<Producto> productos = baseDeDatos.obtenerProductosFiltrados(rubro);
        return productos;
    }
    
     public static ArrayList<Proveedor> obtenerProveedoresFiltrados(String rubro) {
        ArrayList<Proveedor> proveedores = baseDeDatos.obtenerProveedoresFiltrados(rubro);
        return proveedores;
    }

    public static ArrayList<String> obtenerFiltrosRubroProveedores() {
        ArrayList<String> filtros = baseDeDatos.obtenerFiltrosRubroProveedores();
        return filtros;
    }
    
    public static ArrayList<String> obtenerFiltrosRubroProductos() {
        ArrayList<String> filtros = baseDeDatos.obtenerFiltrosRubroProductos();
        return filtros;
    }
    
    public static void aumentarPrecios(String opcion, double cantidad) {
        baseDeDatos.aumentarPrecios(opcion, cantidad);
    }
    public static void disminuirPrecios(String opcion, double cantidad) {
        baseDeDatos.disminuirPrecios(opcion, cantidad);
    }
    
    public static void cerrarSesion() {
        baseDeDatos.cerrarSesion();
        baseDeDatos = null;
    }
}
