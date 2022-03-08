package mx.ejemplo.app.dao;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import org.bson.types.ObjectId;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import io.quarkus.panache.common.Sort;
import mx.ejemplo.app.model.TRole;

@ApplicationScoped
public class RoleDAO implements PanacheMongoRepository<TRole>{

	public List<TRole> findAllRoles(){
		return  listAll(Sort.by("nombre"));
	}
	
	public TRole findByIdRole(ObjectId id) {
		return  findById(id);
	}
	
	public TRole findByName(String name) {
		return  find("nombre", name ).firstResult();
	}
	
	public void createUser(TRole role) {
		persist(role); 
	}
	
}
