package de.fhws.applab.restdemo.api;

import de.fhws.applab.restdemo.models.Event;
import de.fhws.applab.restdemo.models.database.EventDatabase;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.Collection;

@Path( "/events" )
public class EventService
{

	@Context
	UriInfo uriInfo;

	@GET
	@Produces( MediaType.APPLICATION_JSON )
	public Collection<Event> get()
	{
		return EventDatabase.getInstance().Database.getAll();
	}

	@POST
	@Consumes( MediaType.APPLICATION_JSON )
	public Response post( Event event )
	{
		if(event != null) {
			event.set_id(EventDatabase.getInstance().Database.getNextKey());
			EventDatabase.getInstance().Database.setValue(event.get_id(), event);
			return Response.status(Response.Status.CREATED).type(MediaType.APPLICATION_JSON).entity(event).build();
		}
		return Response.status(Response.Status.NO_CONTENT).build();
	}
	@DELETE
	@Path("/{id}")
	@Consumes( MediaType.APPLICATION_JSON )
	public Response delete( @PathParam("id") int id )
	{
		boolean b = EventDatabase.getInstance().Database.removeValue(id);
		if(b)
			return Response.ok().build();
		return Response.status(Response.Status.NOT_MODIFIED).build();
	}
}