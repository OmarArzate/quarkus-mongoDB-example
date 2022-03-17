package mx.ejemplo.app.controller;

import java.net.URI;

import org.bson.types.ObjectId;
import org.jboss.logging.Logger;
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

import mx.ejemplo.app.model.Usuario;
import mx.ejemplo.app.service.UsuarioService;

@Path("/app")
public class UsuarioController {

	private static final Logger LOG = Logger.getLogger(UsuarioController.class); 
	
	@Inject
	UsuarioService usuarioService;
	
	@GET
	@Path("/usuarios")
	@RolesAllowed({ "ADMIN","CNOC"})
	public Response get() {
		List<Usuario> usuarioServiceList = null;
		try {
			usuarioServiceList = usuarioService.findAll();
		}catch(Exception e){
			
		}
		return Response.ok(usuarioServiceList).build();
	}
	
	@POST
	@Path("/role/{id}")
	@RolesAllowed({ "ADMIN","CNOC"})
	public Response create (@PathParam("id") String id, Usuario usuario) throws URISyntaxException {
		usuarioService.createUser(id, usuario);
		return Response.created(new URI("/" + usuario.id)).build();
	}
	
	@PUT
	@Path("/{id}")
	@RolesAllowed({ "ADMIN","CNOC"})
	public Response update(@PathParam("id") String id, Usuario usuario) {
		usuarioService.updateUser(usuario,id);
		return Response.ok(usuario).build();
	}

	@DELETE
	@Path("/{id}")
	public Response delete( @PathParam("id") String id){
		usuarioService.delete(id);
		return Response.ok().build();
	}

}
