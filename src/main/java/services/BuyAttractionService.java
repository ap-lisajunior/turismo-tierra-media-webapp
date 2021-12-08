package services;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import model.Atraccion;
import model.Usuario;
import model.Itinerario;
import model.Producto;
import model.TurismoTierraMedia;
import persistence.AtraccionDAO;
import persistence.ItinerarioDAO;
import persistence.PromocionDAO;
import persistence.UsuarioDAO;
import persistence.commons.DAOFactory;

public class BuyAttractionService {

	LinkedList<Producto> productos = TurismoTierraMedia.getProductos();
	PromocionDAO promociondao = DAOFactory.getPromocionDAO();
	AtraccionDAO atracciondao = DAOFactory.getAtraccionDAO();
	UsuarioDAO usuariodao = DAOFactory.getUsuarioDAO();
	ItinerarioDAO itinerariodao = DAOFactory.getItinerarioDAO();

	public Map<String, String> buy(String string, String nombreProducto) {
		Map<String, String> errores = new HashMap<String, String>();

		Usuario usuario = usuariodao.findByUsername(string);
		
		// !! hacer algo que chequee si recibe el producto que recibo 
		// es atraccion o promocion. Hay que chequear los servlet, etc
		// el ejemplo de los profesores solo maneja atracciones
		Atraccion atraccion = atracciondao.findByName(nombreAtraccion);
		Itinerario itinerario = itinerariodao.findByUsuario(usuario, productos);

		if (!atraccion.tieneCupo()) {
			errores.put("atraccion", "No hay cupo disponible");
		}
		if (!usuario.puedeComprar(atraccion)) {
			errores.put("usuario", "No tienes dinero suficiente");
		}
		if (!itinerario.getAtracciones().contains(atraccion))

		if (errores.isEmpty()) {
			usuario.addToItinerary(atraccion);
			atraccion.set

			// no grabamos para no afectar la base de pruebas
			attractionDAO.update(attraction);
			usuarioDAO.update(usuario);
		}

		return errors;

	}

}
