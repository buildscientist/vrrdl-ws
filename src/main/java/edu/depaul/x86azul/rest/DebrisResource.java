package edu.depaul.x86azul.rest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import edu.depaul.x86azul.geo.*;
import edu.depaul.x86azul.dataAccess.DebrisDAO;
import edu.depaul.x86azul.dataAccess.DebrisDTO;

/*
 * @author Youssuf ElKalay
 * A resource encompassing all RESTful actions related to debris. 
 */

@Path("/debris")
public class DebrisResource {

	@Path("geohash/{geohash}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findDebrisByGeoHash(@PathParam("geohash") String geohash) {
		return getDebrisResponse(geohash);

	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addDebris(Debris debris) throws IllegalArgumentException, IllegalAccessException {
		//Explicitly set the point for this debris instance because the LatLng field is ignored by Jackson and it's setter isn't called.
		//TODO: Find a better way of handling this
		debris.setPoint();
		
		//Basic validation to ensure Debris data members are populated
		if (debris == null || debris.isNull() == true) {
			return Response.status(Status.BAD_REQUEST).build();
		}

		DebrisDTO dto = new DebrisDTO();
		dto.addDebris(debris);
		String geohash = debris.getGeoHash();
		return Response.ok(geohash, MediaType.APPLICATION_JSON).build();
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
