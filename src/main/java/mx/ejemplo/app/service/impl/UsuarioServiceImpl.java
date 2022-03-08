package mx.ejemplo.app.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import mx.ejemplo.app.dao.UsuarioDAO;
import mx.ejemplo.app.model.Usuario;
import mx.ejemplo.app.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService{

	@Inject
	UsuarioDAO usuarioDao;
	
	@Override
	public List<Usuario> findAll() {
		return usuarioDao.findAllUsers();
	}

	@Override
	public void createUser(ObjectId id, Usuario usuario) {
			usuarioDao.createUser(id,usuario); 
	}

	@Override
	public void  updateUser(Usuario usuario) {
		usuarioDao.updateUser(usuario);
	}

	@Override
	public Usuario findById(ObjectId id) {
		// TODO Auto-generated method stub
		return usuarioDao.findById(id);
	}

}
