package mx.ejemplo.app.model;

import io.quarkus.mongodb.panache.common.MongoEntity;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoEntity;

@MongoEntity(collection = "usuario")
public class Usuario extends ReactivePanacheMongoEntity{

	public String nombre;
	public String a_paterno;
	public String a_materno;
	public String username;
	public String correo;
	public String telefono;
	
	public TRole rol;
	
	public Usuario() {
		
	}

	public Usuario(String nombre, String a_paterno, String a_materno, String username, String correo, String telefono,  TRole rol) {
		this.nombre = nombre;
		this.a_paterno = a_paterno;
		this.a_materno = a_materno;
		this.username = username;
		this.correo = correo;
		this.telefono = telefono;
		this.rol = rol;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getA_paterno() {
		return a_paterno;
	}

	public void setA_paterno(String a_paterno) {
		this.a_paterno = a_paterno;
	}

	public String getA_materno() {
		return a_materno;
	}

	public void setA_materno(String a_materno) {
		this.a_materno = a_materno;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public TRole getRol() {
		return rol;
	}

	public void setRol(TRole rol) {
		this.rol = rol;
	}
	
	
	
}
