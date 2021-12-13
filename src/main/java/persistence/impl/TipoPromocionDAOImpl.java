package persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import persistence.commons.ConnectionProvider;
import model.TipoAtraccion;
import model.TipoPromocion;
import persistence.TipoAtraccionDAO;
import persistence.TipoPromocionDAO;
import persistence.commons.MissingDataException;

public class TipoPromocionDAOImpl implements TipoPromocionDAO {

	@Override
	public LinkedList<TipoPromocion> createTiposPromocion() {
		try {
			String sql = "SELECT nombre FROM tipos_promociones";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();
			
			LinkedList<TipoPromocion> tiposPromocion = new LinkedList<TipoPromocion>();
			while(resultados.next()) {
				tiposPromocion.add(toTipoPromocion(resultados));
			}
			
			return tiposPromocion;
			
		} catch(Exception e){
			throw new MissingDataException(e);
		}
	}

	private TipoPromocion toTipoPromocion(ResultSet resultados) throws SQLException {
		return TipoPromocion.valueOf(resultados.getString("nombre"));
	}

	@Override
	public List<TipoPromocion> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countAll() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(TipoPromocion t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(TipoPromocion t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(TipoPromocion t) {
		// TODO Auto-generated method stub
		return 0;
	}


}
