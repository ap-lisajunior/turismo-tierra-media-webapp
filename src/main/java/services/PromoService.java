package services;

import java.util.LinkedList;
import java.util.List;

import model.Atraccion;
import model.Producto;
import model.Promocion;
import model.PromocionAbsoluta;
import model.PromocionAxB;
import model.PromocionPorcentual;
import model.TipoAtraccion;
import model.TipoPromocion;
import model.TurismoTierraMedia;
import model.Usuario;
import persistence.AtraccionDAO;
import persistence.PromocionDAO;
import persistence.commons.DAOFactory;

public class PromoService {
	
	LinkedList<Producto> productos = TurismoTierraMedia.getProductos();
	LinkedList<Producto> atracciones = TurismoTierraMedia.getAtracciones();
	LinkedList<Producto> promociones = TurismoTierraMedia.getPromociones(atracciones);
	
	public LinkedList<Producto> listProductos(Usuario usuario) {
		TurismoTierraMedia.ordenarProductos(productos, usuario.getTipoAtraccion());
		return productos;
	}
	public LinkedList<Producto> listAtracciones() {
		return atracciones;
	}
	public LinkedList<Producto> listPromociones() {
		return promociones;
	}


	public Promocion findByName(String nombre) {
		PromocionDAO getPromocionDAO = DAOFactory.getPromocionDAO();
		return getPromocionDAO.findByName(nombre, atracciones);
	}
	
	// OVERLOAD DE METODOS PROMOCION. MOTIVO: DIFERENTES TIPOS DE PROMOCION
	// AL TENER DIFERENTES TIPOS DE DESCUENTO (O INCLUSIVE NO TENER UN DESCUENTO)
	// LOS TIPOS DE VALORES DE DESCUENTO DIFIEREN UNO DE OTROS
	// EN FRONTEND SE DECIDE QUE TIPO DE PROMOCION DAR DE ALTA
	// EN ESTA CAPA VALIDO QUE NO PASE DATA INCORRECTA
	public Promocion createPromoPorcentual(String nombre, String[] nombreAtracciones, String tipoAtraccion, String tipoPromocion, Double descuento, Boolean activo) {

		LinkedList<Atraccion> atraccionesPromocion = new LinkedList<Atraccion>();
		
		for(String nombreAtraccion : nombreAtracciones) {
			for(Producto atraccion : atracciones) {
				if(atraccion.getNombre().equals(nombreAtraccion)) {
					atraccionesPromocion.add((Atraccion) atraccion);
				}
			}
		}
		
		Promocion promocion = new PromocionPorcentual(nombre, atraccionesPromocion, TipoAtraccion.valueOf(tipoAtraccion), descuento, activo);
		
		
		if (promocion.esPromoValida(TipoPromocion.valueOf(tipoPromocion))) {
			PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
			promocionDAO.insert(promocion);
			// XXX: si no devuelve "1", es que hubo más errores
		}

		return promocion;
	}
	
	public Promocion createPromoAbsoluta(String nombre, String[] nombreAtracciones, String tipoAtraccion, String tipoPromocion, Integer precioFinal, Boolean activo) {

		LinkedList<Atraccion> atraccionesPromocion = new LinkedList<Atraccion>();
		
		for(String nombreAtraccion : nombreAtracciones) {
			for(Producto atraccion : atracciones) {
				if(atraccion.getNombre().equals(nombreAtraccion)) {
					atraccionesPromocion.add((Atraccion) atraccion);
				}
			}
		}
		
		Promocion promocion = new PromocionAbsoluta(nombre, atraccionesPromocion, TipoAtraccion.valueOf(tipoAtraccion), precioFinal, activo);
		
		if (promocion.esPromoValida(TipoPromocion.valueOf(tipoPromocion))) {
			PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
			promocionDAO.insert(promocion);
			// XXX: si no devuelve "1", es que hubo más errores
		}

		return promocion;
	}
	
	public Promocion createPromoAxB(String nombre, String[] nombreAtracciones, String tipoAtraccion, String tipoPromocion, Boolean activo) {

		LinkedList<Atraccion> atraccionesPromocion = new LinkedList<Atraccion>();
		
		for(String nombreAtraccion : nombreAtracciones) {
			for(Producto atraccion : atracciones) {
				if(atraccion.getNombre().equals(nombreAtraccion)) {
					atraccionesPromocion.add((Atraccion) atraccion);
				}
			}
		}
		
		Promocion promocion = new PromocionAxB(nombre, atraccionesPromocion, TipoAtraccion.valueOf(tipoAtraccion), activo);
		
		if (promocion.esPromoValida(TipoPromocion.valueOf(tipoPromocion))) {
			PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
			promocionDAO.insert(promocion);
			// XXX: si no devuelve "1", es que hubo más errores
		}

		return promocion;
	}


	public Promocion update(String nombre, String tipoAtraccion, String tipoPromocion, Double descuento, Boolean activo) {

		PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
		Promocion promocion = promocionDAO.findByName(nombre, atracciones);

		promocion.setNombre(nombre);
		promocion.setTipoPromocion(TipoPromocion.valueOf(tipoPromocion));
		promocion.setTipoAtraccion(TipoAtraccion.valueOf(tipoPromocion));
		promocion.setDescuento(descuento);
		promocion.setActivo(activo);
		
		if (promocion.esPromoValida(TipoPromocion.valueOf(tipoPromocion))) {
			promocionDAO.update(promocion);
			// XXX: si no devuelve "1", es que hubo más errores
		}

		return promocion;
	}


	public void delete(String nombre) {
		PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
		Promocion promocion = promocionDAO.findByName(nombre, atracciones);
		promocionDAO.delete(promocion);
	}

}
