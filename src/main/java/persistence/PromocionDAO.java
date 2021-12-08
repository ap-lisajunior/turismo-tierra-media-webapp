package persistence;

import java.util.LinkedList;
import model.Producto;
import persistence.commons.GenericDAO;

public interface PromocionDAO extends GenericDAO<Producto>{
	
	public abstract LinkedList<Producto> createPromociones(LinkedList<Producto> atracciones);
	
}
