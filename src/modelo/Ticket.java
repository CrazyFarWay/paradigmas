/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.*;

/**
 *
 * @author CrazyFarWay
 */
public class Ticket extends Entidad {
	private Fecha fecha = new Fecha();
	private int id;
	private char tipo;
	
	public Ticket(GestionConexion conexion) {
		super("Ticket", conexion);
	}

	public Ticket() {

	}

	public void mostrar(Venta venta) {

		if (venta.getCliente().getTipo().equals("RESPONSABLE INSCRIPTO")) {
			this.tipo = 'A';
		}
		else if (venta.getCliente().getTipo().equals("CONSUMIDOR FINAL")) {
			this.tipo = 'B';
		}
		else if (venta.getCliente().getTipo().equals("MONOTRIBUTISTA")) {
			this.tipo = 'C';
		}
		else {
			this.tipo = 'X';
		}

		System.out.println("----------------------------------------------------");
		System.out.println("NUMERO DE TICKET: " + id);
		System.out.println("Fecha: " + fecha.getHoy().toString());
		System.out.println("Hora: " + fecha.getHora().toString());
		System.out.println("TICKET FACTURA "+ tipo);

		System.out.println("----------------------------------------------------");

		System.out.println("CLIENTE:" + venta.getCliente().getNombre());
		System.out.println("DNI:" + venta.getCliente().getDni());
		System.out.println("DIRECCION:" + venta.getCliente().getDireccion());
		System.out.println("A " + venta.getCliente().getTipo());

		System.out.println("----------------------------------------------------");

		System.out.println(" ID Prod.            Cant  Precio  Desct.   Subtotal");
		for(LineaDeVenta lineaDeVenta: venta.getLineasDeVenta()) {
			//System.out.println(lineaDeVenta.getId() + " \t " + lineaDeVenta.getProducto().getNombre() + " \t " + lineaDeVenta.getCantidad() + " \t\t\t " +  lineaDeVenta.getProducto().getPrecio() + " \t\t\t " + lineaDeVenta.getDescuento() + " \t\t " + lineaDeVenta.getSubtotal());
			System.out.printf(" %2d %-15s  %4d  %6.2f    %3d      %6.2f \n", lineaDeVenta.getId(), lineaDeVenta.getProducto().getNombre(), lineaDeVenta.getCantidad(), lineaDeVenta.getProducto().getPrecio(), lineaDeVenta.getDescuento(), lineaDeVenta.getSubtotal());
			
        //System.out.println("Id  Descripción      Precio  Cnt   Total");
            //System.out.printf("%2d  %-15s  %6.2f   %2d  %6.2f \n", pro.getId(), pro.getDescripcion(), pro.getPrecio(), pro.getCantidad(), pro.getCantidad() * pro.getPrecio());
		}

		System.out.println("----------------------------------------------------");
        //System.out.printf("               Total -->          %6.2f \n", total);
	System.out.printf("                        Total de la venta --> %6.2f \n", venta.calcularTotal());
		//System.out.println("Total de la venta = " + venta.calcularTotal());
		System.out.println("----------------------------------------------------");
	}

	    public void agregarTicket(Ticket ticket) {
		String query;

		query = "insert into tickets (tipo, dia, hora) values ('" + ticket.getTipo() + "', '" + ticket.getFecha().getHoy() + "', '" + ticket.getFecha().getHora() + "')";

		try {
		    getGestionConexion().getStatement().executeUpdate(query);
		} catch (SQLException ex) {
		    System.out.println(ex.getMessage());
		}
	    }

	    public void eliminarTicket(int id) {
		String query;

		query = "delete from tickets where id='" + id + "'";

		try {
		    getGestionConexion().getStatement().executeUpdate(query);
		} catch (SQLException ex) {
		    System.out.println(ex.getMessage());
		}

	    }

	public Fecha getFecha() {
		return fecha;
	}

	public void setFecha(Fecha fecha) {
		this.fecha = fecha;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public char getTipo() {
		return tipo;
	}

	public void setTipo(char tipo) {
		this.tipo = tipo;
	}
    } 