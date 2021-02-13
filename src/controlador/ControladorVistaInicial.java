/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import vistas.VistaInicial;

/**
 *
 * @author CrazyFarWay
 */
public class ControladorVistaInicial {
    private static VistaInicial vista = new VistaInicial();
    
    public static void mostrar() {
        vista.setVisible(true);
    }
}
