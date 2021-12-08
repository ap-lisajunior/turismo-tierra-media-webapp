package model;

import java.util.LinkedList;

public class PromocionAxB extends Promocion {
	
	private Atraccion bonificada;

	public PromocionAxB(String nombre, LinkedList<Atraccion> atracciones, TipoAtraccion tipoAtraccion) {
		super(nombre, atracciones, tipoAtraccion);
		this.bonificada = atracciones.getLast();
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

}
