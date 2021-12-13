package services;

import java.util.LinkedList;
import java.util.List;

import model.Atraccion;
import model.Producto;
import model.TipoAtraccion;
import model.TipoPromocion;
import persistence.AtraccionDAO;
import persistence.commons.DAOFactory;

public class PromoTypeService {

	public LinkedList<TipoPromocion> list() {
		return DAOFactory.getTipoPromocion().createTiposPromocion();
	}
}
