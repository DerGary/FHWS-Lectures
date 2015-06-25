package de.fhws.applab.restdemo.api;

import de.fhws.applab.restdemo.models.Event;
import de.fhws.applab.restdemo.models.Lecture;
import de.fhws.applab.restdemo.models.database.EventDatabase;
import de.fhws.applab.restdemo.models.database.LectureDatabase;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
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
	public Lecture post( Lecture lecture )
	{
		lecture.set_id(LectureDatabase.getInstance().Database.getNextKey());
		LectureDatabase.getInstance().Database.setValue(lecture.get_id(),lecture);
		return lecture;
	}

}