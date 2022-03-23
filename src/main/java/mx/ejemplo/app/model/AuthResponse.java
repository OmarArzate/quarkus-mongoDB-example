package mx.ejemplo.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class AuthResponse {

	public String username;
	public TRole role;
    public String token;
    public Estatus estatus;
    
}
