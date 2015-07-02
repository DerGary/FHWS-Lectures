package de.fhws.applab.restdemo.api;

import de.fhws.applab.restdemo.models.Lecture;
import de.fhws.applab.restdemo.models.database.LectureDatabase;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.Collection;

@Path( "/lectures" )
public class LectureService
{

	@Context
	UriInfo uriInfo;

	@GET
	@Produces( MediaType.APPLICATION_JSON )
	public Collection<Lecture> get()
	{
		return LectureDatabase.getInstance().Database.getAll();
	}


	@POST
	@Consumes( MediaType.APPLICATION_JSON )
	public Response post( Lecture lecture )
	{
		if(lecture != null) {
			lecture.set_id(LectureDatabase.getInstance().Database.getNextKey());
			LectureDatabase.getInstance().Database.setValue(lecture.get_id(), lecture);
			return Response.status(Response.Status.CREATED).type(MediaType.APPLICATION_JSON).entity(lecture).build();
		}
		return Response.status(Response.Status.NO_CONTENT).build();
	}
	@DELETE
	@Path("/{id}")
	@Consumes( MediaType.APPLICATION_JSON )
	public Response delete( @PathParam("id") int id )
	{
		boolean b = LectureDatabase.getInstance().Database.removeValue(id);
		if(b)
			return Response.ok().build();
		return Response.status(Response.Status.NOT_MODIFIED).build();
	}

}