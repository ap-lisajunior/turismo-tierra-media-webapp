package model;

import java.util.HashMap;
import java.util.LinkedList;

public class PromocionAxB extends Promocion {
	
	private Atraccion bonificada;
	private Double descuento;
	private TipoPromocion tipoPromocion = TipoPromocion.AXB;

	public PromocionAxB(String nombre, LinkedList<Atraccion> atracciones, TipoAtraccion tipoAtraccion, Boolean activo) {
		super(nombre, atracciones, tipoAtraccion);
		this.bonificada = atracciones.getLast();
		super.activo = activo;
		calcularCostoFinal();
	}

	@Override
	protected void calcularCostoFinal() {
		int aux = 0;
		for(Atraccion atraccion : atracciones) {
			aux += atraccion.getCosto();
		}
		super.setCosto(aux - this.bonificada.getCosto());	
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
		return 0;
	}

	@Override
	public void setDescuento(Double descuento) {
		this.descuento = descuento;		
	}
	
}
