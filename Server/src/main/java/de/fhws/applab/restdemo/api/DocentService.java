package de.fhws.applab.restdemo.api;

import de.fhws.applab.restdemo.models.database.DocentDatabase;
import de.fhws.applab.restdemo.models.Docent;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
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
	public Docent post( Docent docent )
	{
		docent.set_id(DocentDatabase.getInstance().Database.getNextKey());
		DocentDatabase.getInstance().Database.setValue(docent.get_id(),docent);
		return docent;
	}

}