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

	private Hashtable<Long, Debris> coordGeoHashMap = new Hashtable<Long, Debris>();
	private long counter = 0;

	private DataPersisterInMemory() {

	}

	private static class DataPersisterHolder {
		public static final DataPersister INSTANCE = new DataPersisterInMemory();
	}

	public static DataPersister getInstance() {
		return DataPersisterHolder.INSTANCE;
	}
	

	public Debris read(Long debrisId) {
		return coordGeoHashMap.get(debrisId);

	}
	
	public Enumeration<Long> readAllKeys() {
		return coordGeoHashMap.keys();
	}
	
	public Enumeration<Debris> readAllValues() {
		return coordGeoHashMap.elements();
	}

	public void write(Debris debris) {
		// increment the counter
		counter++;
		// make sure the debris aware of its uuid
		debris.setDebrisId(counter);
		coordGeoHashMap.put(counter, debris);
	}

	public void delete(Long debrisId) {
		coordGeoHashMap.remove(debrisId);

	}
	
	public void deleteAll() {
		coordGeoHashMap.clear();
	}

	public boolean find(Debris debris) {
		// this will compare their coordinate as well
		return coordGeoHashMap.contains(debris);
	}

	public boolean find(Long debrisId) {
		
		return coordGeoHashMap.containsKey(debrisId);
	}
	
	public Object clone() throws CloneNotSupportedException {
		return null;
		
	}

	
	
}
