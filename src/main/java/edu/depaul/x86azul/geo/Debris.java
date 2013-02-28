package edu.depaul.x86azul.geo;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

import com.javadocmd.simplelatlng.LatLng;
import com.javadocmd.simplelatlng.Geohasher;

/**
 * A general purpose class used to define Debris. Debris in VRRDL is submitted
 * by client applications. Debris contains a number of fields including lat/long
 * a date/time representing submission of the debris.
 * 
 * 
 * @author Youssuf ElKalay
 */

public class Debris {
	@JsonProperty("latitude")
	private double latitude;
	@JsonProperty("longitude")
	private double longitude;
	@JsonProperty("speed")
	private double speed;
	@JsonProperty("uid")
	private String uid;
	@JsonProperty("timestamp")
	private Date dateTime;
	@JsonIgnore
	private LatLng point;

	public Debris() {

	}

	public Debris(double lat, double lng, String id, Date timeDate) {
		uid = id;
		latitude = lat;
		longitude = lng;
		dateTime = timeDate;
		point = new LatLng(latitude, longitude);
	}

	@Override
	public String toString() {
		return "Debris [latitude=" + latitude + ", longitude=" + longitude
				+ ", speed=" + speed + ", uid=" + uid + ", dateTime="
				+ dateTime + ", point=" + point + "]";
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dt) {
		dateTime = dt;
	}

	public LatLng getPoint() {
		return point;
	}

	public void setPoint() {
		point = new LatLng(latitude, longitude);
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double spd) {
		speed = spd;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String id) {
		uid = id;
	}

	public String getGeoHash() {
		return Geohasher.hash(point);
	}

	/**
	 * Checks the Debris object for any null fields.
	 * @return boolean
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	public boolean containsNullFields() {
		if(latitude == 0.0 || longitude == 0.0 || uid == null || dateTime == null) {
			return true;
		}
		
		return false;
	}

}
