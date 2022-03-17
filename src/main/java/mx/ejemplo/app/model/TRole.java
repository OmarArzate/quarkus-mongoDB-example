package mx.ejemplo.app.model;

import io.quarkus.mongodb.panache.common.MongoEntity;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoEntity;

@MongoEntity(collection = "TROLE")
public class TRole extends ReactivePanacheMongoEntity{

	public String nombre;

	public TRole() {}
	
	public TRole(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre(){
		return this.nombre;
	}

	public String setNombre(String nombre){
		return this.nombre = nombre;
	}
}
