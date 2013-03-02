package edu.depaul.x86azul.rest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.javadocmd.simplelatlng.LatLng;
import com.javadocmd.simplelatlng.util.LengthUnit;
import com.javadocmd.simplelatlng.LatLngTool;

/**
 * @author Youssuf ElKalay REST resource to calculate the distance between 2
 *         geographical points
 * 
 */
@Path("/distance")
public class CoordinateDistanceResource {

	@Path("/latitude/{lat1}/longitude/{lng1}/latitude2/{lat2}/longitude2/{lng2}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDistanceBetweenPoints(@PathParam("lat1") double lat1,
			@PathParam("lng1") double lng1, @PathParam("lat2") double lat2,
			@PathParam("lng2") double lng2) {
		
		LatLng p1 = new LatLng(lat1,lng1); 
		LatLng p2 = new LatLng(lat2,lng2);
		
		double distance = LatLngTool.distance(p1, p2, LengthUnit.KILOMETER);
		
		return Response.ok(distance, MediaType.APPLICATION_JSON).build();
		
	}
}
