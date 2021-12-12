package persistence;

import java.util.LinkedList;
import model.Producto;
import model.TipoAtraccion;
import persistence.commons.GenericDAO;
import model.Atraccion;

public interface TipoAtraccionDAO extends GenericDAO<TipoAtraccion> {
	
	public abstract LinkedList<TipoAtraccion> createTiposAtraccion();

}
