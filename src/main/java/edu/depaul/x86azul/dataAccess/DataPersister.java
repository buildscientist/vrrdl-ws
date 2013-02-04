/**
 * 
 */
package edu.depaul.x86azul.dataAccess;

import com.javadocmd.simplelatlng.LatLng;

/**
 * @author Youssuf ElKalay
 *
 */
public interface DataPersister {
	public LatLng get(String geohash);
	
	public String insert(LatLng coordinates);
	
	public void delete(String hash);
	
	public boolean contains(LatLng coordinates);
	
	
}
