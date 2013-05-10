package edu.depaul.x86azul.dataAccess;

import java.util.ArrayList;
import java.util.Enumeration;

import edu.depaul.x86azul.geo.*;
import com.javadocmd.simplelatlng.LatLng;
import com.javadocmd.simplelatlng.window.CircularWindow;
import com.javadocmd.simplelatlng.util.LengthUnit;

/**
 * @author Youssuf ElKalay
 * 
 */
public class DebrisDAO {

	private DataPersister data = DataPersisterInMemory.getInstance();

	public DebrisDAO() {

	}

	public Debris getDebris(Long debrisId) {
	
		return data.read(debrisId);
	}

	public boolean doesDebrisExist(Long debrisId) {

		return data.find(debrisId);
	}

	public boolean hasSimilarDebris(Debris debris) {

		return data.find(debris);
	}
	
	public Debris getSimilarDebris(Debris sampleDebris) {

		Enumeration<Debris> e = data.readAllValues();

		while (e.hasMoreElements()) {
			Debris debris = e.nextElement();
			if (debris.equals(sampleDebris)) {
				return debris;
			}
		}

		return null;
	}
	
	public boolean isDebrisInRange(Debris center, double radius) {
		CircularWindow window = new CircularWindow(center.getPoint(), radius,
				LengthUnit.KILOMETER);
		Enumeration<Debris> e = data.readAllValues();

		while (e.hasMoreElements()) {
			if (window.contains(e.nextElement().getPoint())) {
				return true;
			}
		}

		return false;
	}

	public boolean isDebrisInRange(LatLng center, double radius) {
		CircularWindow window = new CircularWindow(center, radius,
				LengthUnit.KILOMETER);
		Enumeration<Debris> e = data.readAllValues();

		while (e.hasMoreElements()) {
			if (window.contains(e.nextElement().getPoint())) {
				return true;
			}
		}

		return false;
	}

	public ArrayList<LatLng> getAllPointsInRange(Debris center, double radius) {
		CircularWindow window = new CircularWindow(center.getPoint(), radius,
				LengthUnit.KILOMETER);
		Enumeration<Debris> e = data.readAllValues();
		ArrayList<LatLng> pointList = new ArrayList<LatLng>();

		while (e.hasMoreElements()) {
			LatLng p = e.nextElement().getPoint();
			if (window.contains(p)) {
				pointList.add(p);
			}

		}

		return pointList;
	}

	public ArrayList<Debris> getAllDebrisesInRange(LatLng center, double radius) {
		CircularWindow window = new CircularWindow(center, radius,
				LengthUnit.KILOMETER);
		Enumeration<Debris> e = data.readAllValues();
		ArrayList<Debris> debrisList = new ArrayList<Debris>();

		while (e.hasMoreElements()) {
			Debris debris = e.nextElement();
			LatLng p = debris.getPoint();
			if (window.contains(p)) {
				debrisList.add(debris);
			}

		}

		return debrisList;
	}
	
	public ArrayList<LatLng> getAllPointsInRange(LatLng center, double radius) {
		CircularWindow window = new CircularWindow(center, radius,
				LengthUnit.KILOMETER);
		Enumeration<Debris> e = data.readAllValues();
		ArrayList<LatLng> pointList = new ArrayList<LatLng>();

		while (e.hasMoreElements()) {
			LatLng p = e.nextElement().getPoint();
			if (window.contains(p)) {
				pointList.add(p);
			}

		}

		return pointList;
	}

}
