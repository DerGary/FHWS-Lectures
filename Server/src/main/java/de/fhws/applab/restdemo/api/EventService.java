package de.fhws.applab.restdemo.api;

import de.fhws.applab.restdemo.models.Docent;
import de.fhws.applab.restdemo.models.Event;
import de.fhws.applab.restdemo.models.database.DocentDatabase;
import de.fhws.applab.restdemo.models.database.EventDatabase;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import java.util.Collection;

@Path( "/events" )
public class EventService
{

	@Context
	UriInfo uriInfo;

	@GET
	@Produces( MediaType.APPLICATION_JSON )
	public Collection<Event> getEvent()
	{
		return EventDatabase.getInstance().Database.getAll();
	}

	@POST
	@Consumes( MediaType.APPLICATION_JSON )
	public Event post( Event event )
	{
		event.set_id(EventDatabase.getInstance().Database.getNextKey());
		EventDatabase.getInstance().Database.setValue(event.get_id(),event);
		return event;
	}

}