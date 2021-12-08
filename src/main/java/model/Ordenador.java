package model;

import java.util.Comparator;

public class Ordenador implements Comparator<Producto> {

	private TipoAtraccion tipoAtraccionUsuario;
	
	public Ordenador(TipoAtraccion tipoAtraccion) {
		this.tipoAtraccionUsuario = tipoAtraccion;
	}
	
	@Override
	public int compare(Producto producto, Producto otroProducto) {
		int aux;
		aux = producto.getTipoAtraccion().equals(tipoAtraccionUsuario) && !otroProducto.getTipoAtraccion().equals(tipoAtraccionUsuario) ? -1 
				: !producto.getTipoAtraccion().equals(tipoAtraccionUsuario) && otroProducto.getTipoAtraccion().equals(tipoAtraccionUsuario) ? 1 : 0;
		if(aux == 0) {
			aux = -producto.esUnaPromocion().compareTo(otroProducto.esUnaPromocion());
		}
		if(aux == 0) {
			aux = -producto.getCosto().compareTo(otroProducto.getCosto());
		} if (aux == 0) {
			aux = -producto.getTiempo().compareTo(otroProducto.getTiempo());
		}
		return aux;
	}
}

	

