/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;

/**
 *
 * @author CrazyFarWay
 */
public class Venta extends Entidad {
	private Ticket ticket = new Ticket();
	private ArrayList<LineaDeVenta> lineasDeVenta = new ArrayList<>();
	private Cliente cliente;

	public Venta (GestionConexion conexion) {
		super("Venta", conexion);
	}

	public Venta(Ticket ticket, ArrayList<LineaDeVenta> lineasDeVenta, Cliente cliente) {
		this.ticket = ticket;
		this.lineasDeVenta = lineasDeVenta;
		this.cliente = cliente;
	}

	public Venta(GestionConexion conexion, Ticket ticket, ArrayList<LineaDeVenta> lineasDeVenta, Cliente cliente) {
		super("Venta", conexion);
		this.ticket = ticket;
		this.lineasDeVenta = lineasDeVenta;
		this.cliente = cliente;
	}

	public Venta() {
	}

	public double calcularTotal() {
		double total = 0;

		for(LineaDeVenta lineaDeVenta: lineasDeVenta) {
			total += lineaDeVenta.getSubtotal();
		}

		return total;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	public ArrayList<LineaDeVenta> getLineasDeVenta() {
		return lineasDeVenta;
	}

	public void setLineasDeVenta(ArrayList<LineaDeVenta> lineasDeVenta) {
		this.lineasDeVenta = lineasDeVenta;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	
}
