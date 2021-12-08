package persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import jdbc.ConnectionProvider;
import model.Atraccion;
import model.Producto;
import model.Promocion;
import model.PromocionAbsoluta;
import model.PromocionAxB;
import model.PromocionPorcentual;
import model.TipoAtraccion;
import model.TipoPromocion;
import persistence.PromocionDAO;
import persistence.commons.MissingDataException;

public class PromocionDAOImpl implements PromocionDAO {

	@Override
	public LinkedList<Producto> createPromociones(LinkedList<Producto> atracciones) {
		try {
			String sql = "SELECT promociones.nombre, group_concat(atracciones.nombre, ';') AS 'lista_atracciones', promociones.tipo_atraccion, promociones.tipo_promocion, promociones.descuento \r\n"
					+ "FROM promociones\r\n"
					+ "JOIN atraccion_promocion ON atraccion_promocion.nombre_promocion = promociones.nombre\r\n"
					+ "JOIN atracciones ON atracciones.nombre = atraccion_promocion.nombre_atraccion\r\n"
					+ "GROUP BY promociones.nombre";
			
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();
			
			LinkedList<Producto> promociones = new LinkedList<Producto>();
			while(resultados.next()) {
				promociones.add(toPromocion(resultados, atracciones));
			}
			
			return promociones;
			
		} catch(Exception e){
			throw new MissingDataException(e);
		}
	}
	
	private Promocion toPromocion(ResultSet resultados, LinkedList<Producto> atracciones) throws SQLException {
		String nombre = resultados.getString("nombre");
		String[] nombreAtracciones = resultados.getString("lista_atracciones").split(";");
		TipoAtraccion tipoAtraccion = TipoAtraccion.valueOf(resultados.getString("tipo_atraccion"));
		TipoPromocion tipoPromocion = TipoPromocion.valueOf(resultados.getString("tipo_promocion"));
		
		LinkedList<Atraccion> atraccionesPromocion = new LinkedList<Atraccion>();
		LinkedList<Producto> atraccionesTotales = atracciones;
		
		for(String nombreAtraccion : nombreAtracciones) {
			for(Producto atraccion : atraccionesTotales) {
				if(atraccion.getNombre().equals(nombreAtraccion)) {
					atraccionesPromocion.add((Atraccion) atraccion);
					break;
				}
			}
		}
		
		if(tipoPromocion.equals(TipoPromocion.PORCENTUAL)) {
			double porcentajeDescuento = resultados.getDouble("descuento");
			return new PromocionPorcentual(nombre, atraccionesPromocion, tipoAtraccion, porcentajeDescuento);
		}
		else if (tipoPromocion.equals(TipoPromocion.ABSOLUTA)) {
			int costoFinal = resultados.getInt("descuento");
			return new PromocionAbsoluta(nombre, atraccionesPromocion, tipoAtraccion, costoFinal);
		}
		else if (tipoPromocion.equals(TipoPromocion.AXB)) {
			return new PromocionAxB(nombre, atraccionesPromocion, tipoAtraccion);
		}
		return null;		
		
	}

	@Override
	public List<Producto> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countAll() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(Producto t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Producto t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Producto t) {
		// TODO Auto-generated method stub
		return 0;
	}

}
