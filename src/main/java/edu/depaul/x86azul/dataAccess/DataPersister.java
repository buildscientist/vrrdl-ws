package edu.depaul.x86azul.dataAccess;

import java.util.Enumeration;
import edu.depaul.x86azul.geo.*;

/**
 * @author Youssuf ElKalay
 *
 */
public interface DataPersister {
	public Debris read(Long debrisId);
	
	public void write(Debris debris);
	
	public void delete(Long debrisId);
	
	public void deleteAll();
	
	public boolean find(Debris debris);
	
	public boolean find(Long debrisId);
	
	public Enumeration readAllKeys();
	
	public Enumeration readAllValues();
	
	
}
