package persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import jdbc.ConnectionProvider;
import model.Itinerario;
import model.Producto;
import model.Usuario;
import persistence.ItinerarioDAO;
import persistence.commons.MissingDataException;

public class ItinerarioDAOImpl implements ItinerarioDAO{

	@Override
	public List<Itinerario> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countAll() {
		try {
			String sql = "SELECT COUNT(1) AS TOTAL FROM ITINERARIOS";
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
	public int insertProducto(Usuario usuario, Producto producto) {
		try {
			String sql = "INSERT INTO ITINERARIOS (NOMBRE_USUARIO, NOMBRE_PRODUCTO, COSTO, TIEMPO) VALUES (?, ?, ?, ?)";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, usuario.getNombre());
			statement.setString(2, producto.getNombre());
			statement.setInt(3, producto.getCosto());
			statement.setDouble(4, producto.getTiempo());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}
	
	@Override
	public Itinerario findByUsuario(Usuario usuario, LinkedList<Producto> productos) {
		try {
			String sql = "SELECT itinerarios.nombre_usuario, group_concat(itinerarios.nombre_producto, ';') AS 'productos'\r\n"
					+ "FROM itinerarios\r\n"
					+ "WHERE nombre_usuario = ?"
					+ "GROUP BY itinerarios.nombre_usuario";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, usuario.getNombre());
			ResultSet resultados = statement.executeQuery();

			Itinerario itinerario = new Itinerario();
			
			if (resultados.next()) {
				itinerario = toItinerario(resultados, productos, itinerario);
			}
			return itinerario;
			
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int update(Itinerario t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Itinerario t) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public int insert(Itinerario t) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	private Itinerario toItinerario(ResultSet resultados, LinkedList<Producto> productos, Itinerario itinerario) throws SQLException {
		
		String listaProductos = resultados.getString("productos");		
		if(listaProductos != null) {
			String[] nombresListaProductos = listaProductos.split(";");
			for(String nombre : nombresListaProductos) {
				for(Producto producto : productos) {
					if(producto.getNombre().equals(nombre)) {
						itinerario.agregarProducto(producto);
						itinerario.setCosto(producto.getCosto());
						itinerario.setTiempo(producto.getTiempo());
						break;
					}
				}
			}
		}
		return itinerario;			
	}
}
