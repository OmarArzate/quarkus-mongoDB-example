package mx.ejemplo.app.model;

import io.quarkus.mongodb.panache.common.MongoEntity;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@MongoEntity(collection = "TROLE")
public class TRole extends ReactivePanacheMongoEntity{

	public String nombre;
	
}
