package mx.ejemplo.app.controller;

import java.net.URI;

import org.jboss.logging.Logger;

import java.net.URISyntaxException;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import mx.ejemplo.app.model.Usuario;
import mx.ejemplo.app.service.UsuarioService;

@Path("/usuarios")
public class UsuarioController {

	private static final Logger LOG = Logger.getLogger(UsuarioController.class); 
	
	@Inject
	UsuarioService usuarioService;
	
	
	@GET
	//@RolesAllowed({ "ADMIN","CNOC"})
	
	public Response get() {
		List<Usuario> usuarioServiceList = null;
		try {
			usuarioServiceList = usuarioService.findAll();
		}catch(Exception e){
			return Response.serverError().build();
		}
		return Response.ok(usuarioServiceList).build();
	}
	
	@POST
	@Path("/role/{id}")
	//@RolesAllowed({ "ADMIN","CNOC"})
	public Response create (@PathParam("id") String id, Usuario usuario) throws URISyntaxException {
		String mensaje = usuarioService.createUser(id, usuario);

		if(mensaje == ""){
			return Response.created(new URI("/" + usuario.id)).build();
		}else{
			return Response.ok(mensaje)
                    .status(Status.BAD_REQUEST).build();
		}
		
	}
	
	@PUT
	@Path("/{id}")
	//@RolesAllowed({ "ADMIN","CNOC"})
	public Response update(@PathParam("id") String id, Usuario usuario) {
		usuarioService.updateUser(usuario,id);
		return Response.ok(usuario).build();

	}
	@PUT
	@Path("/update-password/{id}/{password}/{passConfirmar}")
	//@RolesAllowed({ "ADMIN","CNOC"})
	public Response updatePassword(@PathParam("id") String id, @PathParam("password") String password,@PathParam("passConfirmar") String passConfirmar) {
		String mensaje = usuarioService.updatePassword(id,password,passConfirmar);
		if(mensaje == ""){
			LOG.info(mensaje);
			return Response.ok("se actualizo correctamente").build();
		}else{
			LOG.info(mensaje);
			return Response.ok(mensaje)
                    .status(Status.BAD_REQUEST).build();
		}
	}

	@DELETE
	@Path("/{id}")
	public Response delete( @PathParam("id") String id){
		usuarioService.delete(id);
		return Response.ok().build();
	}

}
