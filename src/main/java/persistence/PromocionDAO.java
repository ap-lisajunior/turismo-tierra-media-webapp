package persistence;

import java.util.LinkedList;
import model.Producto;
import model.Promocion;
import persistence.commons.GenericDAO;

public interface PromocionDAO extends GenericDAO<Producto>{
	
	public abstract LinkedList<Producto> createPromociones(LinkedList<Producto> atracciones);

	public abstract Promocion findByName(String nombreProducto, LinkedList<Producto> productos);
	
}
