package model;

import utils.Crypt;

public class Usuario {

	private String nombre;
	private Double presupuesto;
	private double tiempo;
	private TipoAtraccion tipoAtraccion;
	private Itinerario itinerario;
	private String password;
	private Boolean admin;
	private Boolean activo;


	public Usuario(String nombre, String password, Double presupuesto, double tiempo, TipoAtraccion tipoAtraccion, Boolean admin) {
		this.nombre = nombre;
		this.password = password;
		this.presupuesto = presupuesto;
		this.tiempo = tiempo;
		this.tipoAtraccion = tipoAtraccion;
		this.admin = admin;
	}

	public String getNombre() {
		return nombre;
	}

	public Double getPresupuesto() {
		return presupuesto;
	}


	public void setPresupuesto(Double presupuesto) {
		this.presupuesto = presupuesto;
	}

	public void setTiempo(double tiempo) {
		this.tiempo = tiempo;
	}

	public double getTiempo() {
		return tiempo;
	}

	public TipoAtraccion getTipoAtraccion() {
		return tipoAtraccion;
	}

	public Boolean esAdmin() {
		return this.admin;
	}

	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(String password) {
		this.password = Crypt.hash(password);
	}

	public boolean isNull() {
		return false;
	}
	
	public String getTipoAtraccionToString() {
		return tipoAtraccion.toString().toUpperCase();
	}


	public boolean puedeComprar(Producto producto) {
		return this.getPresupuesto() >= producto.getCosto() && this.getTiempo() >= producto.getTiempo();
	}

	public void aceptarOferta(Producto producto) {
		this.setPresupuesto(this.getPresupuesto() - producto.getCosto());
		this.setTiempo(this.getTiempo() - producto.getTiempo());
	}
	
	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}


	@Override
	public String toString() {
		return "Nombre de usuario: " + this.getNombre() + ", Tipo de atraccion preferida: "
				+ this.getTipoAtraccion().getDescripcion();
	}
	
	public boolean checkPassword(String password) {
		// this.password en realidad es el hash del password
		return Crypt.match(password, this.password);
	}

	public Itinerario getItinerario() {
		return itinerario;
	}

}
