 package model;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;

import persistence.AtraccionDAO;
import persistence.commons.DAOFactory;
import persistence.PromocionDAO;
import persistence.UsuarioDAO;

public class TurismoTierraMedia {
	
	// DECLARACION LISTAS DE OBJETOS
	
	static LinkedList<Producto> atracciones = new LinkedList<Producto>();
	static LinkedList<Producto> promociones = new LinkedList<Producto>();
	static LinkedList<Usuario> usuarios = new LinkedList<Usuario>();
	static LinkedList<Producto> productos = new LinkedList<Producto>();
	
	
	// OBTENGO LISTA DE USUARIOS DESDE BASE DE DATOS
	
	public static LinkedList<Usuario> getUsuarios() {
		UsuarioDAO usuarioDAO = DAOFactory.getUsuarioDAO();
		usuarios = usuarioDAO.createUsuarios();
		return usuarios;
	}
	
	// OBTENGO LISTA DE ATRACCIONES DESDE BASE DE DATOS
	
	public static LinkedList<Producto> getAtracciones(){
		AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
		atracciones = atraccionDAO.createAtracciones();		
		return atracciones;
	}
	
	// OBTENGO LISTA DE PROMOCIONES DESDE BASE DE DATOS
	
	public static LinkedList<Producto> getPromociones(LinkedList<Producto> atracciones){
		PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
		promociones = promocionDAO.createPromociones(atracciones);
		return promociones;
	}
	
	// CREO LISTA DE PRODUCTOS 
	
	public static LinkedList<Producto> getProductos(){
		productos.addAll(getAtracciones());
		productos.addAll(getPromociones(atracciones));
		return productos;
	}
	
	// METODO PARA ORDENAR LOS PRODUCTOS EN BASE A LA CONSIGNA DADA
	
	public static void ordenarProductos(LinkedList<Producto> productos, TipoAtraccion tipoAtraccionPreferida) {
		Collections.sort(productos, new Ordenador(tipoAtraccionPreferida));
	}
	
	// METODO MAIN
	
	public static void main(String[] args) throws IOException {

		//Ofertador.sugerirItineriario();
		
	}

}
