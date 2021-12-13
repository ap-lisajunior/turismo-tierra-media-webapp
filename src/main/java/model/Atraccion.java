package model;

import java.util.HashMap;
import java.util.LinkedList;

public class Atraccion extends Producto {

	private int cupo;
	
	public Atraccion(String nombre, Double costo, double tiempo, int cupo, TipoAtraccion tipoAtraccion, Boolean activo) {
		super(nombre, tipoAtraccion);
		super.setCosto(costo);
		super.setTiempo(tiempo);
		this.cupo = cupo;
		super.activo = activo;
	}
	
	public Atraccion(String nombre) {
		super(nombre);
	}
	
	public String getTipo() {
		return super.tipoAtraccion.toString();
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
	public void reducirCupo() {
		this.cupo--;
	}

	@Override
	public Boolean esUnaPromocion() {
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
	public void setActivo(Boolean activo) {
		super.activo = activo;		
	}

	@Override
	public LinkedList<Atraccion> getAtracciones() {
		return null;
	}
	

	@Override
	public String toString() {
		return "Esta es una atraccion del tipo: " + this.getTipoAtraccion().getDescripcion() 
				+ "\n. Costo: " + this.getCosto() + " monedas."
				+ "\nTiempo de permanencia: " + this.getTiempo() + " horas.";
	}

	public boolean esValida() {
		validate();
		return super.errores.isEmpty();
	}
	
	public void validate() {
		errores = new HashMap<String, String>();

		if (this.getCosto() <= 0) {
			errores.put("costo", "Debe ser positivo");
		}
		if (this.getTiempo() <= 0) {
			errores.put("tiempo", "Debe ser positivo");
		}
		if (this.getCupo()<= 0) {
			errores.put("cupo", "Debe ser positivo");
		}
	}

	
}
