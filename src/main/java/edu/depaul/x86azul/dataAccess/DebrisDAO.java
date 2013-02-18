/**
 * 
 */
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

	public Debris getDebris(String geoHash) {
		if(data.find(geoHash)) {
			return data.read(geoHash);
		}
	
		return null;
	}
	
	public boolean isDebrisInRange(Debris center, double radius) {
		CircularWindow window = new CircularWindow(center.getPoint(),radius,LengthUnit.MILE);
		Enumeration<Debris> e = data.readAllValues();
		
		while(e.hasMoreElements()) {
			if(window.contains(e.nextElement().getPoint())) {
				return true;
			}
		}
		
		return false;
	}
	
	public ArrayList<LatLng> getAllCoordinatesInRange(Debris center, double radius) {
		CircularWindow window = new CircularWindow(center.getPoint(),radius,LengthUnit.MILE);
		Enumeration<Debris> e = data.readAllValues();
		ArrayList<LatLng> pointList = new ArrayList<LatLng>();
		
		while(e.hasMoreElements()) {
			if(window.contains(e.nextElement().getPoint())) {
				pointList.add(e.nextElement().getPoint());
			}
			
			}
		
		return pointList;
	}
	
}
