package edu.depaul.x86azul.dataAccess;

import java.util.Hashtable;
import java.util.Enumeration;
import edu.depaul.x86azul.geo.*;

/*
 * 
 * @author Youssuf ElKalay
 * 
 *  This class is implemented as a thread-safe Singleton because we only want a single in-memory data persistence layer. 
 *  Implementation of this class is purely for development purposes and should not be used in production.
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
	
	public Enumeration<String> readAllKeys() {
		return coordGeoHashMap.keys();
	}
	
	public Enumeration<Debris> readAllValues() {
		return coordGeoHashMap.elements();
	}

	public void write(String geoHash, Debris debris) {
		coordGeoHashMap.put(geoHash, debris);
	}

	public void delete(String geoHash) {
		coordGeoHashMap.remove(geoHash);

	}
	
	public void deleteAll() {
		coordGeoHashMap.clear();
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
	
	public Object clone() throws CloneNotSupportedException {
		return null;
		
	}

	
	
}
