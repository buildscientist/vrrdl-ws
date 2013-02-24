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
	public Response addDebris(Debris debris) {
		if (debris == null) {
			return Response.status(Status.BAD_REQUEST).build();
		}

		//TODO: Build validation for Debris() data members
		
		// Call setPoint() because Debris() constructor is never called
		// TODO: Figure out a better way to handle this
		debris.setPoint();

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
