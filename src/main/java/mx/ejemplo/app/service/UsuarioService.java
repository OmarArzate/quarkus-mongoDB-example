package mx.ejemplo.app.service;

import java.util.List;

import org.bson.types.ObjectId;

import mx.ejemplo.app.model.Usuario;

public interface UsuarioService {

	public List<Usuario> findAll();

	public Usuario findById(ObjectId id);

	public void createUser(ObjectId id, Usuario usuario);

	public void updateUser(Usuario usuario);
}
