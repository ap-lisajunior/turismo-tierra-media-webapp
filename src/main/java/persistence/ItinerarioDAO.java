package persistence;

import java.util.LinkedList;

import model.Itinerario;
import model.Producto;
import model.Usuario;
import persistence.commons.GenericDAO;

public interface ItinerarioDAO extends GenericDAO<Itinerario> {

	public abstract int insertProducto(Usuario usuario, Producto producto);
	
	public Itinerario findByUsuario(Usuario usuario, LinkedList<Producto> productos);

}
