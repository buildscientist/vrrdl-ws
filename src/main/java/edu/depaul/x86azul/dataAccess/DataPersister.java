/**
 * 
 */
package edu.depaul.x86azul.dataAccess;

import edu.depaul.x86azul.geo.*;

/**
 * @author Youssuf ElKalay
 *
 */
public interface DataPersister {
	public Debris read(String geohash);
	
	public void write(String geoHash,Debris debris);
	
	public void delete(String hash);
	
	public boolean find(Debris debris);
	
	public boolean find(String geoHash);
	
	
}
