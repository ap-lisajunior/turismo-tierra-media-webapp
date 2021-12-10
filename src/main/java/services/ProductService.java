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

}
