package model;

import java.util.LinkedList;

public class PromocionAbsoluta extends Promocion {
	
	private int costoFinal;

	public PromocionAbsoluta(String nombre, LinkedList<Atraccion> atracciones, TipoAtraccion tipoAtraccion, int costoFinal) {
		super(nombre, atracciones, tipoAtraccion);
		this.costoFinal = costoFinal;
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

}
