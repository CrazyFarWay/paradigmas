/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import vistas.VistaActualizarProductos;

/**
 *
 * @author CrazyFarWay
 */
public class ControladorVistaActualizarProductos {
    private static VistaActualizarProductos vista = new VistaActualizarProductos();
    
    public static void mostrar() {
        vista.setVisible(true);
    }
}
