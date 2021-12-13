package model;

import java.util.HashMap;
import java.util.LinkedList;

public class PromocionAbsoluta extends Promocion {
	
	private Double costoFinal;
	private TipoPromocion tipoPromocion = TipoPromocion.ABSOLUTA;

	public PromocionAbsoluta(String nombre, LinkedList<Atraccion> atracciones, TipoAtraccion tipoAtraccion, Double costoFinal, Boolean activo) {
		super(nombre, atracciones, tipoAtraccion);
		this.costoFinal = costoFinal;
		super.activo = activo;
		calcularCostoFinal();
	}

	@Override
	protected void calcularCostoFinal() {
		super.setCosto(costoFinal);
	}

	@Override
	public int getCupo() {
		int auxMaxCupo = 0;
		for(Atraccion atraccion : atracciones) {
			if(auxMaxCupo < atraccion.getCupo()) {
				auxMaxCupo = atraccion.getCupo();
			}
		}
		return auxMaxCupo;
	}
	

	@Override
	public boolean esPromoValida(TipoPromocion tipoPromocion) {
		validatePromo(tipoPromocion);
		return super.errores.isEmpty();
	}
	
	@Override
	public void validatePromo(TipoPromocion tipoPromocion) {
		errores = new HashMap<String, String>();

		if (tipoPromocion != this.tipoPromocion) {
			errores.put("tipoPromocion", "El Tipo de Promocion no coincide");
		}

	}
	
	@Override
	public TipoPromocion getTipoPromocion() {
		return this.tipoPromocion;
	}
	
	@Override
	public void setTipoPromocion(TipoPromocion tipoPromocion) {
		this.tipoPromocion = tipoPromocion;
		
	}


	@Override
	public double getDescuento() {
		return costo;
	}

	@Override
	public void setDescuento(Double descuento) {
		this.costoFinal = descuento;
	}

}
