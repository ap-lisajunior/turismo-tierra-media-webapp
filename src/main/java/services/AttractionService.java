package services;

import java.util.List;

import model.Atraccion;
import model.Producto;
import model.TipoAtraccion;
import persistence.AtraccionDAO;
import persistence.commons.DAOFactory;

public class AttractionService {

	public List<Producto> list() {
		return DAOFactory.getAtraccionDAO().createAtracciones();
	}

	public Atraccion create(String nombre, Integer costo, Double tiempo, Integer cupo, TipoAtraccion tipoAtraccion, Boolean activo) {

		Atraccion atraccion = new Atraccion(nombre, costo, tiempo, cupo, tipoAtraccion, activo);

		if (atraccion.esValida()) {
			AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
			atraccionDAO.insert(atraccion);
			// XXX: si no devuelve "1", es que hubo más errores
		}
		return atraccion;
	}

	public Atraccion update(String nombre, Integer costo, Double tiempo, Integer cupo, Boolean activo) {

		AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
		Atraccion atraccion = atraccionDAO.findByName(nombre);

		atraccion.setNombre(nombre);
		atraccion.setCosto(costo);
		atraccion.setTiempo(tiempo);
		atraccion.setCupo(cupo);
		atraccion.setActivo(activo);

		if (atraccion.esValida()) {
			atraccionDAO.update(atraccion);
			// XXX: si no devuelve "1", es que hubo más errores
		}

		return atraccion;
	}

	public void delete(String nombre) {
		Atraccion atraccion = new Atraccion(nombre);

		AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
		atraccionDAO.delete(atraccion);
	}

	public Atraccion findByName(String nombre) {
		AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
		return atraccionDAO.findByName(nombre);
	}

}
