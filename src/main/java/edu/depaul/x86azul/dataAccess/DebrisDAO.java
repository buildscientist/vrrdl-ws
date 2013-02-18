/**
 * 
 */
package edu.depaul.x86azul.dataAccess;

import java.util.ArrayList;
import edu.depaul.x86azul.geo.*;
import com.javadocmd.simplelatlng.LatLng;

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
	
	public boolean isDebrisInRange(LatLng center, double radius) {
		return false;
	}
	
	public ArrayList<LatLng> getAllCoordinatesInRange(LatLng center, double radius) {
		return null;
	}
	
}
