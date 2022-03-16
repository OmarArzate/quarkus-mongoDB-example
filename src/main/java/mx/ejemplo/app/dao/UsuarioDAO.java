package mx.ejemplo.app.dao;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.bson.types.ObjectId;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import io.quarkus.panache.common.Sort;
import mx.ejemplo.app.model.TRole;
import mx.ejemplo.app.model.Usuario;
import mx.ejemplo.app.utils.PBKDF2Encoder;

@ApplicationScoped
public class UsuarioDAO implements PanacheMongoRepository<Usuario>{
	


	public List<Usuario> findAllUsers(){
		return  listAll(Sort.by("nombre"));
	}
	
	public Usuario findByUserName(String username) {
		return  find("username", username ).firstResult();
	}
	
	public void createUser(Usuario usuario) {
		persist(usuario); 
	}
	
	public void updateUser(Usuario usuario) {
		update(usuario); 
	}

}
