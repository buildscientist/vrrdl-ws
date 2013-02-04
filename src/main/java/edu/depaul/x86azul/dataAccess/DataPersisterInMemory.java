/**
 * 
 */
package edu.depaul.x86azul.dataAccess;

import java.util.HashMap;
import com.javadocmd.simplelatlng.LatLng;
import com.javadocmd.simplelatlng.Geohasher;

/**
 * @author Youssuf ElKalay
 * 
 */

public class DataPersisterInMemory implements DataPersister {
	/*
	 * This class is implemented as a thread-safe Singleton because we only want a single in-memory data persistence layer. 
	 * Implementation of this class is purely for development purposes and should not be used in production.
	 */
	private HashMap<String, LatLng> coordGeoHashMap = new HashMap<String, LatLng>();

	private DataPersisterInMemory() {

	}

	private static class DataPersisterHolder {
		public static final DataPersister INSTANCE = new DataPersisterInMemory();
	}

	public static DataPersister getInstance() {
		return DataPersisterHolder.INSTANCE;
	}

	public LatLng get(String geohash) {
		return coordGeoHashMap.get(geohash);

	}

	public String insert(LatLng coordinates) {
		String geohash = Geohasher.hash(coordinates);
		coordGeoHashMap.put(geohash, coordinates);
		return geohash;
	}

	public void delete(String geohash) {
		coordGeoHashMap.remove(geohash);

	}

	public boolean contains(LatLng coordinates) {
		if (!coordGeoHashMap.containsValue(coordinates)) {
			return false;
		}

		return true;
	}

}
