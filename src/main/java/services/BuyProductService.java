package services;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import model.Atraccion;
import model.Usuario;
import model.Itinerario;
import model.Producto;
import model.Promocion;
import model.TurismoTierraMedia;
import persistence.AtraccionDAO;
import persistence.ItinerarioDAO;
import persistence.PromocionDAO;
import persistence.UsuarioDAO;
import persistence.commons.DAOFactory;

public class BuyProductService {

	LinkedList<Producto> productos = TurismoTierraMedia.getProductos();
	LinkedList<Producto> atracciones = TurismoTierraMedia.getAtracciones();
	PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
	AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
	UsuarioDAO usuarioDAO = DAOFactory.getUsuarioDAO();
	ItinerarioDAO itinerarioDAO = DAOFactory.getItinerarioDAO();

	public Map<String, String> buy(String string, String nombreProducto) {
		Map<String, String> errores = new HashMap<String, String>();

		Usuario usuario = usuarioDAO.findByUsername(string);
		Itinerario itinerario = itinerarioDAO.findByUsuario(usuario, productos);
		
		if(nombreProducto.contains("Pack")) {
			Producto promocion = promocionDAO.findByName(nombreProducto, atracciones);
			
			if(promocion.tieneCupo() == false) {
				errores.put("promocion", "No hay cupo disponible");
			}
			if(usuario.puedeComprar(promocion) == false) {
				errores.put("usuario", "No tienes dinero suficiente");
			}
			if(itinerario.getPromociones().contains(promocion)) {
				errores.put("itinerario", "El producto ya se encuentra en tu itinerario");
			}
			
			if (errores.isEmpty()) {
				usuario.aceptarOferta(promocion);
				usuarioDAO.update(usuario);
				itinerarioDAO.insertProducto(usuario, promocion);
				promocion.reducirCupo();
				for(Atraccion atraccion : promocion.getAtracciones()) {
					atraccionDAO.updateCupo(atraccion);
				}
				itinerario.agregarProducto(promocion);
				itinerario.setCosto(promocion.getCosto());
				itinerario.setTiempo(promocion.getTiempo());
			}
			
		} else {
			Producto atraccion = atraccionDAO.findByName(nombreProducto);
			
			if(atraccion.tieneCupo() == false) {
				errores.put("atraccion", "No hay cupo disponible");
			}
			if(usuario.puedeComprar(atraccion) == false) {
				errores.put("usuario", "No tienes dinero suficiente");
			}
			if(itinerario.getAtracciones().contains(atraccion)) {
				errores.put("itinerario", "El producto ya se encuentra en tu itinerario");
			}
			
			if (errores.isEmpty()) {
				usuario.aceptarOferta(atraccion);
				usuarioDAO.update(usuario);
				itinerarioDAO.insertProducto(usuario, atraccion);
				atraccion.reducirCupo();
				atraccionDAO.update(atraccion);
				itinerario.agregarProducto(atraccion);
				itinerario.setCosto(atraccion.getCosto());
				itinerario.setTiempo(atraccion.getTiempo());
			}
		}

		return errores;

	}

}
