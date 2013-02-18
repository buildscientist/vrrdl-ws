/**
 * 
 */
package edu.depaul.x86azul.geo;

import java.util.Date;
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
	private double latitude;
	private double longitude;
	private double speed;
	private String uid;
	private Date dateTime;
	private LatLng point;

	public Debris(double lat, double lng,Date timeDate) {
		latitude = lat;
		longitude = lng;
		dateTime = timeDate;
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
		point = new LatLng(longitude,latitude);
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
