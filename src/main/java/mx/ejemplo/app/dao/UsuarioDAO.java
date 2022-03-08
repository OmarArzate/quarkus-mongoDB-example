package mx.ejemplo.app.dao;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.bson.types.ObjectId;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import io.quarkus.panache.common.Sort;
import mx.ejemplo.app.model.TRole;
import mx.ejemplo.app.model.Usuario;

@ApplicationScoped
public class UsuarioDAO implements PanacheMongoRepository<Usuario>{
	
	@Inject
	RoleDAO roleDao;

	public List<Usuario> findAllUsers(){
		return  listAll(Sort.by("nombre"));
	}
	
	public Usuario findByName(String name) {
		return  find("nombre", name ).firstResult();
	}
	
	public void createUser(ObjectId id, Usuario usuario) {
		TRole rol = roleDao.findById(id);
		usuario.setRol(rol);
		persist(usuario); 
	}
	
	public void updateUser(Usuario usuario) {
		update(usuario); 
	}

}
