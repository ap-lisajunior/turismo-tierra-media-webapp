package model;

import java.util.LinkedList;
import java.util.stream.Collectors;

public class Itinerario {

	private LinkedList<Producto> atracciones = new LinkedList<Producto>();
	private LinkedList<Producto> promociones = new LinkedList<Producto>();
	private Double costo = 0.0;
	private Double tiempo = 0.0;

	public Itinerario() {

	}

	public LinkedList<Producto> getAtracciones() {
		return atracciones;
	}
	
	public LinkedList<Producto> getPromociones(){
		return promociones;
	}

	public void agregarProducto(Producto producto) {
		if(producto.esUnaPromocion()) {
			promociones.add(producto);
			for(Producto atraccion : promociones.getLast().getAtracciones()) {
				atracciones.add(atraccion);
			}
		} else {
			atracciones.add(producto);
		}
	}

	public Double getCosto() {
		return costo;
	}

	public void setCosto(Double costo) {
		this.costo += costo;
	}

	public Double getTiempo() {
		return tiempo;
	}

	public void setTiempo(Double tiempo) {
		this.tiempo += tiempo;
	}

	@Override
	public String toString() {
		return "Atracciones a visitar: "
				+ this.atracciones.stream().map(Producto::getNombre).collect(Collectors.toList()) + "\nCosto total: "
				+ this.costo + " monedas." + "\nTiempo de permanencia total: " + this.tiempo + " horas.";
	}

}