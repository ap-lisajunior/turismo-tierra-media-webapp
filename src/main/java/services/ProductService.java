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

public class ProductService {
	
	AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
	PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
	
	
	
	public LinkedList<Producto> listProductos(Usuario usuario) {
		LinkedList<Producto> productos = new LinkedList<Producto>();
		productos.addAll(atraccionDAO.createAtracciones());
		productos.addAll(promocionDAO.createPromociones(atraccionDAO.createAtracciones()));
		TurismoTierraMedia.ordenarProductos(productos, usuario.getTipoAtraccion());
		return productos;
	}
	
	public LinkedList<Producto> listAtracciones() {
		return TurismoTierraMedia.getAtracciones();
	}
	public LinkedList<Producto> listPromociones() {
		return TurismoTierraMedia.getPromociones(TurismoTierraMedia.getAtracciones());
	}

}
