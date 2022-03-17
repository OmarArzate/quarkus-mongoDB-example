package mx.ejemplo.app.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import mx.ejemplo.app.model.TRole;
import mx.ejemplo.app.service.RoleService;

@Path("/roles")
public class RoleController {

	@Inject
	RoleService roleService;
	
	@GET
	@RolesAllowed({ "ADMIN","CNOC"})
	public Response getRoles(){
		List<TRole> rolServiceList = null;
		try {
			rolServiceList = roleService.findAll();
		}catch(Exception e){
			
		}
		return Response.ok(rolServiceList).build();
	}
	
	@POST
	@RolesAllowed({ "ADMIN","CNOC"})
	public Response createRole(TRole role) throws URISyntaxException {
		roleService.createRole(role);
		return Response.created(new URI("/" + role.id)).build();
	}

	@PUT
	@Path("/{id}")
	public Response updateRole(@PathParam("id") String id, TRole role){
		roleService.updateRole(id, role);
		return Response.ok().build();
	}

	@DELETE
	@Path("/{id}")
	public Response deleteRole(@PathParam("id") String id){
		roleService.delete(id);
		return Response.ok().build();
	}

}
