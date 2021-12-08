package services;

import java.util.List;

import model.Atraccion;
import model.Producto;
import model.TipoAtraccion;
import persistence.AtraccionDAO;
import persistence.commons.DAOFactory;

public class AttractionService {

	public List<Producto> list() {
		return DAOFactory.getAtraccionDAO().findAll();
	}

	public Atraccion create(String nombre, Integer costo, Double tiempo, Integer cupo, TipoAtraccion tipoAtraccion) {

		Atraccion atraccion = new Atraccion(nombre, costo, tiempo, cupo, tipoAtraccion);

		if (atraccion.esValida()) {
			AtraccionDAO atracciondao = DAOFactory.getAtraccionDAO();
			atracciondao.insert(atraccion);
			// XXX: si no devuelve "1", es que hubo más errores
		}

		return atraccion;
	}

	public Atraccion update(String nombre, Integer costo, Double tiempo, Integer cupo) {

		AtraccionDAO atracciondao = DAOFactory.getAtraccionDAO();
		Atraccion atraccion = atracciondao.findByName(nombre);

		atraccion.setNombre(nombre);
		atraccion.setCosto(costo);
		atraccion.setTiempo(tiempo);
		atraccion.setCupo(cupo);

		if (atraccion.esValida()) {
			atracciondao.update(atraccion);
			// XXX: si no devuelve "1", es que hubo más errores
		}

		return atraccion;
	}

	public void delete(String nombre) {
		Atraccion atraccion = new Atraccion(nombre);

		AtraccionDAO atracciondao = DAOFactory.getAtraccionDAO();
		atracciondao.delete(atraccion);
	}

	public Atraccion findByName(String nombre) {
		AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
		return atraccionDAO.findByName(nombre);
	}

}
