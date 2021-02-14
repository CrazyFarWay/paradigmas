/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author CrazyFarWay
 */
public class Fecha {
        private String hoy;
        private String hora;

    public Fecha() {
        Date fecha = new Date();
        SimpleDateFormat ff = new SimpleDateFormat("yyy-MM-dd");
        hoy = ff.format(fecha); 
        fecha.getTime();
        SimpleDateFormat ft = new SimpleDateFormat("HH:mm:ss");
        hora = ft.format(fecha.getTime());
    }

    public String getHoy() {
        return hoy;
    }

    public void setHoy(String hoy) {
        this.hoy = hoy;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
        
}
