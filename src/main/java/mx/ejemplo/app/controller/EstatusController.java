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

import mx.ejemplo.app.model.Estatus;
import mx.ejemplo.app.service.EstatusService;

@Path("/estatus")
public class EstatusController {

	@Inject
	EstatusService estatusService;
	
	@GET
	//@RolesAllowed({ "ADMIN","CNOC"})
	public Response getEstatus(){
		List<Estatus> rolServiceList = null;
		try {
			rolServiceList = estatusService.findAll();
		}catch(Exception e){
			
		}
		return Response.ok(rolServiceList).build();
	}
	
	@POST
	//@RolesAllowed({ "ADMIN","CNOC"})
	public Response createEstatus(Estatus estatus) throws URISyntaxException {
		estatusService.createEstatus(estatus);
		return Response.created(new URI("/" + estatus.id)).build();
	}

	@PUT
	@Path("/{id}")
	//@RolesAllowed({ "ADMIN","CNOC"})
	public Response updateEstatus(@PathParam("id") String id, Estatus estatus){
		estatusService.updateEstatus(id, estatus);
		return Response.ok().build();
	}

	@DELETE
	@Path("/{id}")
	//@RolesAllowed({ "ADMIN","CNOC"})
	public Response deleteRole(@PathParam("id") String id){
		estatusService.delete(id);
		return Response.ok().build();
	}

}
