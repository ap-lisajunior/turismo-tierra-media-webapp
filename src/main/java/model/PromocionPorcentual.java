package model;

import java.util.HashMap;
import java.util.LinkedList;

public class PromocionPorcentual extends Promocion {
	
	private double porcentajeDescuento;
	private TipoPromocion tipoPromocion = TipoPromocion.PORCENTUAL;

	public PromocionPorcentual(String nombre, LinkedList<Atraccion> atracciones, TipoAtraccion tipoAtraccion, Double porcentajeDescuento, Boolean activo) {
		super(nombre, atracciones, tipoAtraccion);
		this.porcentajeDescuento = porcentajeDescuento;
		super.activo = activo;
		calcularCostoFinal();
	}

	@Override
	protected void calcularCostoFinal() {
		int aux = 0;
		for(Atraccion atraccion : atracciones) {
			aux += atraccion.getCosto();
		}
		super.setCosto((aux - aux * this.porcentajeDescuento));
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
	public double getDescuento() {
		return this.porcentajeDescuento;
	}
	
	@Override
	public void setDescuento(Double porcentajeDescuento) {
		this.porcentajeDescuento = porcentajeDescuento;
	}

	@Override
	public void setTipoPromocion(TipoPromocion tipoPromocion) {
		this.tipoPromocion = tipoPromocion;
		
	}

}
