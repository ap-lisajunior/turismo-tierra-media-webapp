package services;

import java.util.LinkedList;
import java.util.List;

import model.Atraccion;
import model.Producto;
import model.TipoAtraccion;
import persistence.AtraccionDAO;
import persistence.commons.DAOFactory;

public class AttractionTypeService {

	public LinkedList<TipoAtraccion> list() {
		return DAOFactory.getTipoAtraccion().createTiposAtraccion();
	}
}
