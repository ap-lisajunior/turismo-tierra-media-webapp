package persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import persistence.commons.ConnectionProvider;
import model.Atraccion;
import model.Producto;
import model.TipoAtraccion;
import persistence.AtraccionDAO;
import persistence.commons.MissingDataException;

public class AtraccionDAOImpl implements AtraccionDAO {

	@Override
	public List<Producto> findAll() {
		try {
			String sql = "SELECT * FROM atracciones";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();
			
			LinkedList<Producto> atracciones = new LinkedList<Producto>();
			while(resultados.next()) {
				atracciones.add(toAtraccion(resultados));
			}
			
			return atracciones;
			
		} catch(Exception e){
			throw new MissingDataException(e);
		}
	}

	@Override
	public int countAll() {
		try {
			String sql = "SELECT COUNT(1) AS TOTAL FROM ATRACCIONES";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			resultados.next();
			int total = resultados.getInt("TOTAL");

			return total;
		} catch (Exception e) {
			throw new MissingDataException(e);
		
		}
	}

	@Override
	public int insert(Producto atraccion) {
		try {
			String sql = "INSERT INTO ATRACCIONES (NOMBRE, COSTO, TIEMPO, CUPO, TIPO, ACTIVO) VALUES (?, ?, ?, ?, ?, ?)";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, atraccion.getNombre());
			statement.setInt(2, atraccion.getCosto());
			statement.setDouble(3, atraccion.getTiempo());
			statement.setInt(4, atraccion.getCupo());
			statement.setString(5, atraccion.getTipoAtraccion().toString());
			statement.setInt(6, atraccion.getActivo() ? 1 : 0);
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int update(Producto atraccion) {
		try {
			String sql = "UPDATE ATRACCIONES SET NOMBRE = ?, COSTO = ?, TIEMPO = ?, CUPO = ?, TIPO = ?, ACTIVO = ? WHERE NOMBRE = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, atraccion.getNombre());
			statement.setInt(2, atraccion.getCosto());
			statement.setDouble(3, atraccion.getTiempo());
			statement.setInt(4, atraccion.getCupo());
			statement.setString(5, atraccion.getTipoAtraccion().toString());
			statement.setInt(6, atraccion.getActivo() ? 1 : 0);
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int updateCupo(Producto atraccion) {
		
		try {
			String sql = "UPDATE ATRACCIONES SET CUPO = ? WHERE NOMBRE = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, atraccion.getCupo());
			statement.setString(2, atraccion.getNombre());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	
	
	@Override
	public int delete(Producto atraccion) {
		try {
			String sql = "DELETE FROM ATRACCIONES WHERE NOMBRE = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, atraccion.getNombre());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public LinkedList<Producto> createAtracciones() {
		try {
			String sql = "SELECT nombre, costo, tiempo, cupo, tipo, activo FROM atracciones";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();
			
			LinkedList<Producto> atracciones = new LinkedList<Producto>();
			while(resultados.next()) {
				atracciones.add(toAtraccion(resultados));
			}
			
			return atracciones;
			
		} catch(Exception e){
			throw new MissingDataException(e);
		}
	}
	

	private Atraccion toAtraccion(ResultSet resultados) throws SQLException {
		return new Atraccion(resultados.getString("nombre"), resultados.getInt("costo"), resultados.getDouble("tiempo"), resultados.getInt("cupo"), TipoAtraccion.valueOf(resultados.getString("tipo")), resultados.getBoolean("activo"));
	}

	@Override
	public Atraccion findByName(String nombreAtraccion) {
		try {
			String sql = "SELECT * FROM ATRACCIONES WHERE nombre = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, nombreAtraccion);
			
			ResultSet resultados = statement.executeQuery();

			Atraccion atraccion = null;
			if (resultados.next()) {
				atraccion = toAtraccion(resultados);
			}

			return atraccion;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

}
