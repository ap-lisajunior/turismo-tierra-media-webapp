package persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import persistence.commons.ConnectionProvider;
import model.TipoAtraccion;
import persistence.TipoAtraccionDAO;
import persistence.commons.MissingDataException;

public class TipoAtraccionDAOImpl implements TipoAtraccionDAO {

	@Override
	public LinkedList<TipoAtraccion> createTiposAtraccion() {
		try {
			String sql = "SELECT nombre FROM tipos_atracciones";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();
			
			LinkedList<TipoAtraccion> tiposAtraccion = new LinkedList<TipoAtraccion>();
			while(resultados.next()) {
				tiposAtraccion.add(toTipoAtraccion(resultados));
			}
			
			return tiposAtraccion;
			
		} catch(Exception e){
			throw new MissingDataException(e);
		}
	}

	private TipoAtraccion toTipoAtraccion(ResultSet resultados) throws SQLException {
		return TipoAtraccion.valueOf(resultados.getString("nombre"));
	}

	@Override
	public List<TipoAtraccion> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countAll() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(TipoAtraccion t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(TipoAtraccion t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(TipoAtraccion t) {
		// TODO Auto-generated method stub
		return 0;
	}

}
