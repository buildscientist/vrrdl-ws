package edu.depaul.x86azul.geo;

import java.util.Date;
import com.javadocmd.simplelatlng.LatLng;

import edu.depaul.x86azul.dataAccess.*;

/**
 * @author Youssuf ElKalay
 *
 *
 */
public class DebrisLocator {

	private double lat;
	private double lng;
	private double speed;
	private String deviceID;
	private Date dateTime;
	private LatLng coordinates;
	private DataPersister data = DataPersisterInMemory.getInstance();

	public DebrisLocator(double latitude, double longitude, double spd, String devID,
			Date timeDate) {
		lat = latitude;
		lng = longitude;
		speed = spd;
		deviceID = devID;
		dateTime = timeDate;
	}
	
	public LatLng getDebris(String geohash) {
		return data.get(geohash);
	}

	public String markDebris() {
		coordinates = new LatLng(lat, lng);
		String geohash = data.insert(coordinates);
		return geohash;
	}

	public void removeDebris(String geohash) {
		data.delete(geohash);

	}

	public boolean isDebrisWithinRange(double lati, double longi) {
		coordinates.setLatitudeLongitude(lati, longi);
		if (!data.contains(coordinates)) {
			return false;
		}

		return true;
	}

	

}
