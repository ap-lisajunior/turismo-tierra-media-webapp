package persistence;

import java.util.LinkedList;
import model.Producto;
import model.TipoAtraccion;
import model.TipoPromocion;
import persistence.commons.GenericDAO;
import model.Atraccion;

public interface TipoPromocionDAO extends GenericDAO<TipoPromocion> {
	
	public abstract LinkedList<TipoPromocion> createTiposPromocion();

}
