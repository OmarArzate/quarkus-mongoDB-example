package mx.ejemplo.app.dao;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import org.bson.types.ObjectId;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import io.quarkus.panache.common.Sort;
import mx.ejemplo.app.model.Usuario;

@ApplicationScoped
public class UsuarioDAO implements PanacheMongoRepository<Usuario>{
	


	public List<Usuario> findAllUsers(){
		return  listAll(Sort.by("nombre"));
	}
	
	public Usuario findByUserName(String username) {
		return  find("username", username ).firstResult();
	}
	
	public List<String> agregaPass(String id){
		ObjectId idO =new ObjectId(id);
		
		DBObject listItem = new BasicDBObject("password", "");
        DBObject updateQuery = new BasicDBObject("$push", listItem);

		Usuario u = new Usuario();
		u.findById(idO);

		update(u);

        return null;

	}	
	
}
