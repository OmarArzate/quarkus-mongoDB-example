package mx.ejemplo.app.dao;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;


import io.quarkus.mongodb.panache.PanacheMongoRepository;
import io.quarkus.panache.common.Sort;
import mx.ejemplo.app.model.TRole;

@ApplicationScoped
public class RoleDAO implements PanacheMongoRepository<TRole>{

	public List<TRole> findAllRoles(){
		return  listAll(Sort.by("nombre"));
	}
	
	public TRole findByName(String name) {
		return  find("nombre", name ).firstResult();
	}
	
	public void createUser(TRole role) {
		persist(role); 
	}
	
}
