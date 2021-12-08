package model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Atraccion extends Producto {

	private int cupo;
	
	private Map<String, String> errores;
	
	public Atraccion(String nombre, int costo, double tiempo, int cupo, TipoAtraccion tipoAtraccion) {
		super(nombre, tipoAtraccion);
		super.setCosto(costo);
		super.setTiempo(tiempo);
		this.cupo = cupo;
	}
	
	public Atraccion(String nombre) {
		super(nombre);
	}

	public boolean esValida() {
		validate();
		return errores.isEmpty();
	}
	
	public void validate() {
		errores = new HashMap<String, String>();

		if (super.getCosto() <= 0) {
			errores.put("costo", "Debe ser positivo");
		}
		if (super.getTiempo() <= 0) {
			errores.put("duracion", "Debe ser positivo");
		}
		if (getCupo()<= 0) {
			errores.put("capacidad", "Debe ser positivo");
		}
	}
	
	@Override
	public void setNombre(String nombre) {
		super.setNombre(nombre);
	}
	
	
	public void setCupo(int cupo) {
		this.cupo = cupo;
	}
	
	public int getCupo() {
		return this.cupo;
	}
	
	@Override
	protected void reducirCupo() {
		this.cupo--;
	}

	@Override
	protected Boolean esUnaPromocion() {
		return false;
	}


	@Override
	public Boolean tieneCupo() {
		return this.cupo > 0;
	}		
	
	@Override
	protected void setComprado(Boolean estado) {
		super.comprado = estado;
	}

	@Override
	protected LinkedList<Atraccion> getAtracciones() {
		return null;
	}
	

	@Override
	public String toString() {
		return "Esta es una atraccion del tipo: " + this.getTipoAtraccion().getDescripcion() 
				+ "\nCosto: " + this.getCosto() + " monedas."
				+ "\nTiempo de permanencia: " + this.getTiempo() + " horas.";
	}
	
}
