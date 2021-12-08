package persistence;

import java.util.LinkedList;

import persistence.commons.GenericDAO;
import model.Usuario;

public interface UsuarioDAO extends GenericDAO<Usuario>{
	
	public abstract Usuario findByUsername(String username);
	
	public abstract LinkedList<Usuario> createUsuarios();
}
