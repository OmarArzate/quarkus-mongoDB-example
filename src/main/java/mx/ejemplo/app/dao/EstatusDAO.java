package mx.ejemplo.app.dao;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;


import io.quarkus.mongodb.panache.PanacheMongoRepository;
import io.quarkus.panache.common.Sort;
import mx.ejemplo.app.model.Estatus;

@ApplicationScoped
public class EstatusDAO implements PanacheMongoRepository<Estatus>{

	public List<Estatus> findAllRoles(){
		return  listAll(Sort.by("nombre"));
	}	
}
