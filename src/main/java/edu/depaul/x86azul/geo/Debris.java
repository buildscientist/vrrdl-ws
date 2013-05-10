package edu.depaul.x86azul.geo;

import java.text.DecimalFormat;
import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

import com.javadocmd.simplelatlng.LatLng;

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
	@JsonProperty("uid")
	private String uid;
	@JsonProperty("speed")
	private double speed;
	@JsonProperty("accuracy")
	private double accuracy;
	@JsonProperty("timestamp")
	private Date dateTime;
	@JsonIgnore
	private LatLng point;
	@JsonProperty("debrisId")
	private Long debrisId;

	public Debris() {

	}

	public Debris(	double lat, 
			double lng, 
			String id, 
			double spd,
			double acc, 
			Date timeDate) {

		uid = id;
		speed = spd;
		accuracy = acc;
		dateTime = timeDate;

		// make sure the coordinate data in web service
		// conforms to the same precision

		DecimalFormat df  = new DecimalFormat("0.000000");
		latitude = Double.valueOf(df.format(lat));
		longitude = Double.valueOf(df.format(lng));

		point = new LatLng(latitude, longitude);

		// this will get overwritten just when this object 
		// is going to be written into persistent.
		// just put some random number first

		debrisId = 999L;
	}

	/**
	 * This function need to always be manually invoked
	 * after debris auto-creation resulted from user json 
	 */
	public void initAutoVars() {

		// correct the latlng precision
		DecimalFormat df  = new DecimalFormat("0.000000");
		latitude = Double.valueOf(df.format(latitude));
		longitude = Double.valueOf(df.format(longitude));

		point = new LatLng(latitude, longitude);
		debrisId = 999L;
	}

	@Override
	public String toString() {
		return "Debris [latitude=" + latitude + 
				", longitude=" + longitude + 
				", uid=" + uid + 
				", speed=" + speed + 
				", accuracy=" + accuracy + 
				", dateTime=" + dateTime + 
				", point=" + point + 
				", debrisId=" + debrisId + "]";
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

	public void setPoint(LatLng pt) {
		point = pt;
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

	public Long getDebrisId() {
		return debrisId;
	}

	public void setDebrisId(Long id) {
		debrisId = id;
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

	/*
	 * debris is equal if they're at the same location.
	 * here we assume the latlng value has been converted
	 * to the same precision
	 */
	@Override
	public boolean equals(Object obj){
		if (obj == null)
			return false;
		if (obj == this)
			return true;
		if (obj.getClass() != getClass())
			return false;

		Debris debris = (Debris)obj;

		if( (latitude != debris.getLatitude()) || 
				(longitude != debris.getLongitude()) )
			return false;

		return true;
	}

}
