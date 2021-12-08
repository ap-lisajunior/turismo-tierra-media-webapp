package model;

import utils.Crypt;

public class Usuario {

	private String nombre;
	private int presupuesto;
	private double tiempo;
	private TipoAtraccion tipoAtraccion;
	private Itinerario itinerario;
	private String password;
	private Boolean admin;


	public Usuario(String nombre, String password, int presupuesto, double tiempo, TipoAtraccion tipoAtraccion, Boolean admin) {
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

	public int getPresupuesto() {
		return presupuesto;
	}


	public void setPresupuesto(int presupuesto) {
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

	public Boolean getAdmin() {
		return this.admin;
	}

	public String getPassword() {
		return password;
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

	protected void aceptarOferta(Producto producto) {
		this.setPresupuesto(this.getPresupuesto() - producto.getCosto());
		this.setTiempo(this.getTiempo() - producto.getTiempo());
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
