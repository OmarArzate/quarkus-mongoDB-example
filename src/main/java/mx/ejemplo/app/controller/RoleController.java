package mx.ejemplo.app.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import mx.ejemplo.app.model.TRole;
import mx.ejemplo.app.service.RoleService;

@Path("/roles")
public class RoleController {

	@Inject
	RoleService roleService;
	
	@GET
	public Response getRoles(){
		List<TRole> rolServiceList = null;
		try {
			rolServiceList = roleService.findAll();
		}catch(Exception e){
			
		}
		return Response.ok(rolServiceList).build();
	}
	
	@POST
	public Response create (TRole role) throws URISyntaxException {
		roleService.createRole(role);
		return Response.created(new URI("/" + role.id)).build();
	}
}
