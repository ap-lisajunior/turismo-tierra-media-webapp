package services;

import java.util.LinkedList;

import model.Itinerario;
import model.Producto;
import model.Usuario;
import persistence.commons.DAOFactory;

public class ItineraryService {
	
	public Itinerario findByUsuario(Usuario usuario, LinkedList<Producto> productos) {
		return DAOFactory.getItinerarioDAO().findByUsuario(usuario, productos);
	}


}
