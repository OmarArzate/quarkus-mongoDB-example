package mx.ejemplo.app.model;

public class AuthResponse {

	public String username;
	
	public TRole role;
	
    public String token;
    

    public AuthResponse(){}

    public AuthResponse(String username, TRole role, String token){
    	this.username= username;
    	this.role = role;
        this.token = token;
    }


    @Override
    public String toString() {
        return "AuthResponse [ username="+username+ "role=" + role.id + "token=" + token + "]";
    }
    
    
}
