package edu.depaul.x86azul.rest;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

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

		for (LatLng point : nearestPoints) {
			String geohash = Geohasher.hash(point);
			nearestDebris.add(dao.getDebris(geohash));
		}

		return Response.ok(nearestDebris, MediaType.APPLICATION_JSON).build();
	}

	/*
	 * This is a temporary workaround until we get the push notification service up and running. It's main purpose is to provide 
	 * all debris points by proximity (if within radial proximity) and/or all debris submitted by the device ID
	 */
	@Path("/latitude/{latitude}/longitude/{longitude}/radius/{radius}/uid/{uid}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAllDebrisByProximityAndUID(
			@PathParam("latitude") double lat,
			@PathParam("longitude") double lng,
			@PathParam("radius") double radius, @PathParam("uid") String uid) {
		DebrisDAO dao = new DebrisDAO();
		LatLng center = new LatLng(lat, lng);
		ArrayList<LatLng> nearestPoints = new ArrayList<LatLng>();
		ArrayList<Debris> nearestDebris = new ArrayList<Debris>();

		if (lat == 0.0 || lng == 0.0 || radius == 0.0 || uid == null) {
			return Response.status(Status.BAD_REQUEST).build();
		}

		if (!dao.isDebrisInRange(center, radius)) {

			nearestPoints = dao.getAllPointsByUID(uid);
			if(nearestPoints.size() == 0) {
				return Response.status(Status.NOT_FOUND).build();
			}

			for (LatLng point : nearestPoints) {
				String geohash = Geohasher.hash(point);
				nearestDebris.add(dao.getDebris(geohash));
			}

			return Response.ok(nearestDebris, MediaType.APPLICATION_JSON)
					.build();

		}
		
		ArrayList<LatLng> pointsByUID = new ArrayList<LatLng>();
		ArrayList<Debris> debrisByUID = new ArrayList<Debris>();
		pointsByUID = dao.getAllPointsByUID(uid);
		
		if (pointsByUID.size() > 0) {
			for (LatLng point : pointsByUID) {
				String geohash = Geohasher.hash(point);
				debrisByUID.add(dao.getDebris(geohash));
			}
		}

		
		nearestPoints = dao.getAllPointsInRange(center, radius);
		for (LatLng point : nearestPoints) {
			String geohash = Geohasher.hash(point);
			nearestDebris.add(dao.getDebris(geohash));
		}
		
		class DebrisComparator implements Comparator<Debris> {

			@Override
			public int compare(Debris d1, Debris d2) {
				if (d1.getGeoHash().equals(d2.getGeoHash())) {
					return 0;
				}
				return -1;
			}

		}
		
		ArrayList<Debris> allDebris = new ArrayList<Debris>();
		allDebris.addAll(debrisByUID);
		allDebris.addAll(nearestDebris);
		Set<Debris> debrisSet = new TreeSet<Debris>(new DebrisComparator());
		for(Debris d : allDebris) {
			if(!debrisSet.add(d)) {
				allDebris.remove(d);
			}
		}
		
		return Response.ok(allDebris, MediaType.APPLICATION_JSON).build();

	}

}
