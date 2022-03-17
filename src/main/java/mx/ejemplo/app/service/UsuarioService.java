package mx.ejemplo.app.service;

import java.util.List;

import mx.ejemplo.app.model.Usuario;

public interface UsuarioService {

	public List<Usuario> findAll();

	public Usuario findById(String id);
	
	public Usuario findByUserName(String username);

	public void createUser(String id, Usuario usuario);

	public void updateUser(Usuario usuario, String id);

	public void delete(String id);
}
