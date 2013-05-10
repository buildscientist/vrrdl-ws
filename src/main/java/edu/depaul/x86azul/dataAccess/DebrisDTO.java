package edu.depaul.x86azul.dataAccess;

import edu.depaul.x86azul.geo.*;


/**
 * @author Youssuf ElKalay
 * 
 */
public class DebrisDTO {

	private DataPersister data = DataPersisterInMemory.getInstance();

	public DebrisDTO() {

	}

	public void addDebris(Debris debris) {
		// the debrisId will get automatically calculated 
		// within data persister
		data.write(debris);
	}
	
	public void removeDebris(Long debrisId) {
		data.delete(debrisId);
		
	}
	
	public void removeAllDebris() {
		data.deleteAll();
	}
 }
