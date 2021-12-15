package persistence;

import java.util.LinkedList;
import model.Producto;
import persistence.commons.GenericDAO;
import model.Atraccion;

public interface AtraccionDAO extends GenericDAO<Producto> {
	
	public abstract LinkedList<Producto> createAtracciones();

	public abstract Atraccion findByName(String nombreAtraccion);

	int updateCupo(Producto atraccion);




}
