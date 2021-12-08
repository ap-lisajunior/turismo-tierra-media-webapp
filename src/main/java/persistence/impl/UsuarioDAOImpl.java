package persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import persistence.commons.ConnectionProvider;
import model.Usuario;
import model.nullobjects.NullUser;
import model.TipoAtraccion;
import persistence.UsuarioDAO;
import persistence.commons.MissingDataException;

public class UsuarioDAOImpl implements UsuarioDAO {

	@Override
	public List<Usuario> findAll() {
		return null;
	}

	@Override
	public int countAll() {
		try {
			String sql = "SELECT COUNT(1) AS TOTAL FROM USUARIOS";
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
	public int update(Usuario usuario) {
		try {
			String sql = "UPDATE USUARIOS SET PRESUPUESTO = ?, TIEMPO = ? WHERE NOMBRE = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);

			statement.setInt(1, usuario.getPresupuesto());
			statement.setDouble(2, usuario.getTiempo());
			statement.setString(3, usuario.getNombre());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public Usuario findByUsername(String nombreUsuario) {
		try {
			String sql = "SELECT * FROM USUARIOS WHERE NOMBRE = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, nombreUsuario);
			ResultSet resultados = statement.executeQuery();

			Usuario usuario = NullUser.build();

			if (resultados.next()) {
				usuario = toUser(resultados);
			}

			return usuario;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int delete(Usuario usuario) {
		try {
			String sql = "DELETE FROM USUARIOS WHERE NOMBRE = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, usuario.getNombre());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	

	@Override
	public int insert(Usuario usuario) {
		try {
			String sql = "INSERT INTO USUARIOS (NOMBRE, PASSWORD, PRESUPUESTO, TIEMPO, TIPO_ATRACCION, ES_ADMIN) VALUES (?, ?, ?, ?, ?, ?)";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, usuario.getNombre());
			statement.setString(2, usuario.getPassword());
			statement.setInt(3, usuario.getPresupuesto());
			statement.setDouble(4, usuario.getTiempo());
			statement.setString(5, usuario.getTipoAtraccionToString());
			statement.setBoolean(6, usuario.getAdmin());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	private Usuario toUser(ResultSet resultados) throws SQLException {
		return new Usuario(resultados.getString(2), resultados.getString(3), resultados.getInt(4), resultados.getDouble(5),
				TipoAtraccion.valueOf(resultados.getString(6)), resultados.getBoolean(7));
	}

	@Override
	public LinkedList<Usuario> createUsuarios() {
		try {
			String sql = "SELECT * FROM USUARIOS";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();
			LinkedList<Usuario> usuarios = new LinkedList<Usuario>();

			while (resultados.next()) {
				usuarios.add(toUser(resultados));
			}
			return usuarios;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

}
