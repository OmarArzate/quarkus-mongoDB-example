package mx.ejemplo.app.model;

import java.util.Date;

import io.quarkus.mongodb.panache.common.MongoEntity;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@MongoEntity(collection = "USUARIOS")
public class Usuario extends ReactivePanacheMongoEntity {

	public String nombre;
	public String a_paterno;
	public String a_materno;
	public String username;
	public String correo;
	public String telefono;
	public TRole rol;
	public String password;
	public Estatus estatus;
	public Date changePassword;

	private static final long PASSWORD_EXPIRATION_TIME = 30L * 24L * 60L * 60L * 1000L; // 30 days

	public boolean isPasswordExpired() {
		if (this.changePassword == null){	return false;	}
		long currentTime = System.currentTimeMillis();
		long lastChangedTime = this.changePassword.getTime();
		return currentTime > lastChangedTime + PASSWORD_EXPIRATION_TIME;
	}
}
