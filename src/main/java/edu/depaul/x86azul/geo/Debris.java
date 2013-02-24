package edu.depaul.x86azul.geo;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

import com.javadocmd.simplelatlng.LatLng;
import com.javadocmd.simplelatlng.Geohasher;


/**
 * A general purpose class used to define Debris. Debris in VRRDL is submitted by client applications. Debris contains a number of fields including lat/long 
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
	
	public Debris(double lat, double lng,String id,Date timeDate) {
		uid = id;
		latitude = lat;
		longitude = lng;
		dateTime = timeDate;
		setPoint();
	}
	
	public String toString() {
		String debrisString = new String("latitude:" + latitude + "\nlongitude:" + longitude + "\nspeed:" + speed 
								+ "\nuid:" + uid + "\ntimestamp:" + dateTime.toString());
		return debrisString;
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

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}
	
	public LatLng getPoint() {
		return point;
	}

	public void setPoint() { 
		point = new LatLng(latitude,longitude);
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}
	
	public String getGeoHash() {
		return Geohasher.hash(point);
	}

}
