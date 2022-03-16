package mx.ejemplo.app.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import mx.ejemplo.app.dao.RoleDAO;
import mx.ejemplo.app.dao.UsuarioDAO;
import mx.ejemplo.app.model.TRole;
import mx.ejemplo.app.model.Usuario;
import mx.ejemplo.app.service.UsuarioService;
import mx.ejemplo.app.utils.PBKDF2Encoder;

@Service
public class UsuarioServiceImpl implements UsuarioService{

	@Inject
	UsuarioDAO usuarioDao;
	
	@Inject
	RoleDAO roleDao;

	@Inject
    PBKDF2Encoder passwordEncoder;
	
	@Override
	public List<Usuario> findAll() {
		return usuarioDao.findAllUsers();
	}

	@Override
	public void createUser(String id, Usuario usuario) {
		
		ObjectId idO = new ObjectId(id);
		
		TRole rol = roleDao.findById(idO);
		usuario.password = passwordEncoder.encode(usuario.password);
		usuario.setRol(rol);
			usuarioDao.createUser(usuario); 
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

	@Override
	public Usuario findByUserName(String username) {
		// TODO Auto-generated method stub
		return usuarioDao.findByUserName(username);
	}

}
