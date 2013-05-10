package edu.depaul.x86azul.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import edu.depaul.x86azul.geo.*;
import edu.depaul.x86azul.dataAccess.*;

/*
 * @author Youssuf ElKalay
 * A debris related REST resource. 
 */

@Path("/debris")
public class DebrisResource {

	@Path("debrisId/{debrisId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findDebrisById(@PathParam("debrisId") Long debrisId) {
		DebrisDAO dao = new DebrisDAO();
		Debris debris = dao.getDebris(debrisId);
		if (debris == null) {
			return Response.status(Status.NOT_FOUND).build();
		}

		return Response.ok(debris, MediaType.APPLICATION_JSON).build();
	}

	@Path("debrisId/{debrisId}")
	@DELETE
	public Response removeDebrisById(@PathParam("debrisId") Long debrisId) {
		DebrisDAO dao = new DebrisDAO();
		if (dao.doesDebrisExist(debrisId)) {
			DebrisDTO dto = new DebrisDTO();
			dto.removeDebris(debrisId);		
			return Response.ok().build();
		}

		return Response.status(Status.NOT_FOUND).build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addDebris(Debris debris) throws URISyntaxException {
		// Basic validation to ensure Debris data members are populated
		if (debris == null || debris.containsNullFields() == true) {
			return Response.status(Status.BAD_REQUEST).build();
		}

		/*
		 * Explicitly set the non-json vars for this debris instance because those
		 * /* fields are ignored by Jackson and its setter isn't called.
		 */
		// TODO: Find a better way of handling this
		debris.initAutoVars();

		DebrisDAO dao = new DebrisDAO();
		
		// check if we already have this particular debris
		Debris similarDebris = dao.getSimilarDebris(debris);
		
		if (similarDebris != null) {
			// already exist, redirect!
			return Response.seeOther(
					new URI("/debris/debrisId/" + similarDebris.getDebrisId())).build();
		}

		DebrisDTO dto = new DebrisDTO();
		dto.addDebris(debris);
		
		Long debrisId = debris.getDebrisId();
		return Response.created(new URI("debrisId/" + debrisId)).build();
	}

	@Path("/bulk")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response bulkAddDebris(ArrayList<Debris> debrisList) {

		ArrayList<Long> debrisIdList = new ArrayList<Long>();
		DebrisDAO dao = new DebrisDAO();
		DebrisDTO dto = new DebrisDTO();

		// Verify the bulk submission is not empty
		if (debrisList.isEmpty()) {
			return Response.status(Status.BAD_REQUEST).build();
		}

		// First pass for debris with missing/null fields
		for (Debris debris : debrisList) {
			if (debris == null || debris.containsNullFields() == true) {
				return Response.status(Status.BAD_REQUEST).build();
			}

		}

		for (Debris debris : debrisList) {
			// fill up the auto vars
			debris.initAutoVars();
			
			// Ignore debris that's already been persisted
			if (dao.hasSimilarDebris(debris)) {
				continue;
			}
			dto.addDebris(debris);
			debrisIdList.add(debris.getDebrisId());
	}

		return Response.ok(debrisIdList, MediaType.APPLICATION_JSON).build();

	}

}
