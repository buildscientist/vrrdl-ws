/**
 * 
 */
package edu.depaul.x86azul.dataAccess;

import java.util.Hashtable;
import edu.depaul.x86azul.geo.*;

/**
 *  This class is implemented as a thread-safe Singleton because we only want a single in-memory data persistence layer. 
 *  Implementation of this class is purely for development purposes and should not be used in production.
 * @author Youssuf ElKalay
 * 
 */

public class DataPersisterInMemory implements DataPersister {

	private Hashtable<String, Debris> coordGeoHashMap = new Hashtable<String, Debris>();

	private DataPersisterInMemory() {

	}

	private static class DataPersisterHolder {
		public static final DataPersister INSTANCE = new DataPersisterInMemory();
	}

	public static DataPersister getInstance() {
		return DataPersisterHolder.INSTANCE;
	}

	public Debris read(String geoHash) {
		return coordGeoHashMap.get(geoHash);

	}

	public void write(String geoHash, Debris debris) {
		coordGeoHashMap.put(geoHash, debris);
	}

	public void delete(String geoHash) {
		coordGeoHashMap.remove(geoHash);

	}

	public boolean find(Debris debris) {
		if (!coordGeoHashMap.containsValue(debris)) {
			return false;
		}

		return true;
	}

	public boolean find(String geoHash) {
		if(!coordGeoHashMap.containsKey(geoHash)) {
			return false;
		}
		
		return true;
	}

	
	
}
