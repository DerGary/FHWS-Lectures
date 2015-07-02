package de.fhws.applab.restdemo.api;

import de.fhws.applab.restdemo.models.database.DocentDatabase;
import de.fhws.applab.restdemo.models.Docent;
import de.fhws.applab.restdemo.models.database.EventDatabase;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.Collection;

@Path( "/docents" )
public class DocentService
{

	@Context
	UriInfo uriInfo;

	@GET
	@Produces( MediaType.APPLICATION_JSON )
	public Collection<Docent> getPerson()
	{
		return DocentDatabase.getInstance().Database.getAll();
	}

	@POST
	@Consumes( MediaType.APPLICATION_JSON )
	public Response post( Docent docent )
	{
		if(docent != null) {
			docent.set_id(EventDatabase.getInstance().Database.getNextKey());
			DocentDatabase.getInstance().Database.setValue(docent.get_id(), docent);
			return Response.status(Response.Status.CREATED).type(MediaType.APPLICATION_JSON).entity(docent).build();
		}
		return Response.status(Response.Status.NO_CONTENT).build();
	}
	@DELETE
	@Path("/{id}")
	@Consumes( MediaType.APPLICATION_JSON )
	public Response delete( @PathParam("id") int id )
	{
		boolean b = DocentDatabase.getInstance().Database.removeValue(id);
		if(b)
			return Response.ok().build();
		return Response.status(Response.Status.NOT_MODIFIED).build();
	}

}