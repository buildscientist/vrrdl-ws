package edu.depaul.x86azul.rest;

import java.net.URI;
import java.net.URISyntaxException;

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

	@Path("geohash/{geohash}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findDebrisByGeoHash(@PathParam("geohash") String geohash) {
		return getDebrisResponse(geohash);

	}
	
	@Path("geohash/{geohash}")
	@DELETE
	public Response removeDebrisByGeoHash(@PathParam("geohash") String geohash) {
		DebrisDAO dao = new DebrisDAO();
		if(dao.doesDebrisExist(geohash)) {
			DebrisDTO dto = new DebrisDTO(); 
			dto.removeDebris(geohash);
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
		 * Explicitly set the point for this debris instance because the LatLng
		 * /* field is ignored by Jackson and its setter isn't called.
		 */
		// TODO: Find a better way of handling this
		debris.setPoint();
		
		DebrisDAO dao = new DebrisDAO();
		if (dao.doesDebrisExist(debris.getGeoHash())) {
			return Response.seeOther(new URI("/debris/" + debris.getGeoHash())).build();			
		}
		
		
		DebrisDTO dto = new DebrisDTO();
		dto.addDebris(debris);
		String geohash = debris.getGeoHash();
		return Response.created(new URI(geohash)).build();
	}

	private Response getDebrisResponse(String geohash) {
		DebrisDAO dao = new DebrisDAO();
		Debris debris = dao.getDebris(geohash);
		if (debris == null) {
			return Response.status(Status.NOT_FOUND).build();
		}

		return Response.ok(debris, MediaType.APPLICATION_JSON).build();

	}

}
