package edu.depaul.x86azul.geo;

import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.codehaus.jackson.annotate.JsonCreator;
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

	@JsonIgnore
	private final int MAXLATITUDE = 90;
	@JsonIgnore
	private final int MINLATITUDE = -90;
	@JsonIgnore
	private final int MINLONGITUDE = -180;
	@JsonIgnore
	private final int MAXLONGITUDE = 180;

	@Min(value = MINLATITUDE)
	@Max(value = MAXLATITUDE)
	private double latitude;

	@Min(value = MINLONGITUDE)
	@Max(value = MAXLONGITUDE)
	private double longitude;

	private double speed;

	@NotNull
	/*
	 * Android device IDs are 16 characters minimum - while iOS device IDs are 40
	 * characters
	 */
	@Size(min = 16)
	private String uid;

	@NotNull
	@Past
	private Date dateTime;

	@JsonIgnore
	private LatLng point;

	@JsonIgnore
	private static Validator validator;

	public Debris() {

	}

	@JsonCreator
	public Debris(@JsonProperty("latitude") double lat,
			@JsonProperty("longitude") double lng,
			@JsonProperty("uid") String id,
			@JsonProperty("timestamp") Date timeDate) {
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

	public boolean passesBeanValidation() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
		Set<ConstraintViolation<Debris>> constraintViolations = validator
				.validate(this);

		if (constraintViolations.size() != 0) {
			return false;
		}

		return true;
	}

}
