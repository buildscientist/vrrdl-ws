package edu.depaul.x86azul.rest;

import java.util.ArrayList;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.javadocmd.simplelatlng.LatLng;
import com.javadocmd.simplelatlng.Geohasher;

import edu.depaul.x86azul.geo.Debris;
import edu.depaul.x86azul.dataAccess.*;

/**
 * @author Youssuf ElKalay This REST resource handles any and all actions
 *         related to proximity of a point (lat/long) to known locations of
 *         debris.
 */

@Path("/proximity")
public class ProximityResource {
	@Path("/latitude/{latitude}/longitude/{longitude}/radius/{radius}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findDebrisByProximity(@PathParam("latitude") double lat,
			@PathParam("longitude") double lng,
			@PathParam("radius") double radius) {
		DebrisDAO dao = new DebrisDAO();
		LatLng center = new LatLng(lat, lng);

		if (lat == 0.0 || lng == 0.0 || radius == 0.0) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		
		if (!dao.isDebrisInRange(center, radius)) {
			return Response.status(Status.NOT_FOUND).build();

		}
		
		ArrayList<LatLng> nearestPoints = new ArrayList<LatLng>();
		ArrayList<Debris> nearestDebris = new ArrayList<Debris>();
		nearestPoints = dao.getAllPointsInRange(center, radius);
		
		for(LatLng point : nearestPoints) {
			String geohash = Geohasher.hash(point);
			nearestDebris.add(dao.getDebris(geohash));
		}
		
		return Response.ok(nearestDebris, MediaType.APPLICATION_JSON).build();
	}

}
