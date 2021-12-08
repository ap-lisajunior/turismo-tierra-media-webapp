package model;

import java.util.LinkedList;

public class PromocionPorcentual extends Promocion {
	
	private double porcentajeDescuento;

	public PromocionPorcentual(String nombre, LinkedList<Atraccion> atracciones, TipoAtraccion tipoAtraccion, double porcentajeDescuento) {
		super(nombre, atracciones, tipoAtraccion);
		this.porcentajeDescuento = porcentajeDescuento;
		calcularCostoFinal();
	}

	@Override
	protected void calcularCostoFinal() {
		int aux = 0;
		for(Atraccion atraccion : atracciones) {
			aux += atraccion.getCosto();
		}
		super.setCosto((int) (aux - aux * this.porcentajeDescuento));
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
	
}
