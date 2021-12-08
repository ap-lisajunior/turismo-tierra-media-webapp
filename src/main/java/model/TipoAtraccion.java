package model;

public enum TipoAtraccion {
	AVENTURA(1,"Aventura"),
	PAISAJE(3,"Paisaje"),
	DEGUSTACION(2,"Degustacion");
	
	private int id_tipo_atraccion;
	private String descripcion;
	
	private TipoAtraccion(int id_tipo_atraccion, String descripcion) {
		this.descripcion = descripcion;
	}
	
	public int getID() {
		return this.id_tipo_atraccion;
	}
	
	protected String getDescripcion() {
		return this.descripcion;
	}
	
	
}
