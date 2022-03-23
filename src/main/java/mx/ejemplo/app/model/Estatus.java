package mx.ejemplo.app.model;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@MongoEntity(collection = "ESTATUS")
public class Estatus extends PanacheMongoEntity{
    public String nombre;
    
}
