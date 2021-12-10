package model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.stream.Collectors;

public abstract class Promocion extends Producto{
	
	LinkedList<Atraccion> atracciones;

	public Promocion(String nombre, LinkedList<Atraccion> atracciones, TipoAtraccion tipoAtraccion) {
		super(nombre, tipoAtraccion);
		this.atracciones = atracciones;
		calcularTiempoTotal();
	}
	
	abstract protected void calcularCostoFinal();
	
	public abstract boolean esPromoValida(TipoPromocion tipoPromocion);
	
	public abstract void validatePromo(TipoPromocion tipoPromocion);
	
	public abstract void setTipoPromocion(TipoPromocion tipoPromocion);
	
	protected void calcularTiempoTotal() {
		double aux = 0;
		for(Atraccion atraccion : atracciones) {
			aux += atraccion.getTiempo();
		}
		super.setTiempo(aux);
	}
	
	@Override
	public LinkedList<Atraccion> getAtracciones() {
		return atracciones;
	}
	
	@Override
	public Boolean tieneCupo() {
		for(Producto atraccion : atracciones) {
			if(atraccion.getCupo() == 0) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public void reducirCupo() {
		for(Atraccion atraccion : atracciones) {
			atraccion.reducirCupo();
		}
	}
	
	@Override
	protected void setComprado(Boolean estado) {
		for (Atraccion atraccion : atracciones) {
			atraccion.comprado = estado;
		}
		this.comprado = estado;
	}
	
	@Override
	public void setActivo(Boolean activo) {
		super.activo = activo;		
	}


	@Override
	public Boolean esUnaPromocion() {
		return true;
	}
	

	@Override
	public String toString() {
		return "Este producto es una promoci�n del tipo " + this.getTipoAtraccion().getDescripcion() 
				+ " que abarca las siguientes atracciones: " + this.getAtracciones().stream().map(Producto::getNombre).collect(Collectors.toList()) + "." 
				+ "\nCosto total: " + this.getCosto() + " monedas." 
				+ "\nTiempo de permanencia total: " + this.getTiempo() + " horas.";
	}

	public abstract TipoPromocion getTipoPromocion();

	public abstract double getDescuento();

	public abstract void setDescuento(Double descuento);
	
}
