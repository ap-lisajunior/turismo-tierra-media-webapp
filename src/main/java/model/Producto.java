package model;

import java.util.LinkedList;

public abstract class Producto {
	
	private String nombre;
	private int costo;
	private double tiempo;
	TipoAtraccion tipoAtraccion;
	protected Boolean comprado = false;
	
	public Producto(String nombre, TipoAtraccion tipoAtraccion) {
		this.setNombre(nombre);
		this.tipoAtraccion = tipoAtraccion;
	}
	
	public Producto(String nombre) {
		// TODO Auto-generated constructor stub
	}

	public String getNombre() {
		return this.nombre;
	}

	public Integer getCosto() {
		return this.costo;
	}

	public Double getTiempo() {
		return this.tiempo;
	}
	
	public TipoAtraccion getTipoAtraccion() {
		return this.tipoAtraccion;
	}

	protected Boolean fueComprado() {
		return this.comprado;
	}
	
	abstract protected void setComprado(Boolean estado);
	
	abstract protected void reducirCupo();
	
	abstract protected Boolean tieneCupo();
	
	abstract protected Boolean esUnaPromocion();

	abstract protected LinkedList<Atraccion> getAtracciones();
	
	public void setCosto(int costoFinal){
		this.costo = costoFinal;
	}

	public void setTiempo(double tiempoFinal) {
		this.tiempo = tiempoFinal;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.getNombre() == null) ? 0 : getNombre().hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Producto other = (Producto) obj;
		if (getNombre() == null) {
			if (other.getNombre() != null)
				return false;
		} else if (!getNombre().equals(other.getNombre()))
			return false;
		return true;
	}

	public abstract int getCupo();

	protected void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
