package edu.depaul.x86azul;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Date;
import java.util.Enumeration;
import java.util.UUID;

import edu.depaul.x86azul.dataAccess.*;
import edu.depaul.x86azul.geo.*;
import com.javadocmd.simplelatlng.*;

public class DataPersisterInMemoryTest {

	private static DataPersister data;
	private Debris debris;
	private Date date;
	private long debrisId;
	private Enumeration<Long> idEnum;
	private Enumeration<Debris> debrisEnum;
	private UUID id;
	private double speed;
	private double accuracy;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		data = DataPersisterInMemory.getInstance();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		id = UUID.randomUUID();
		speed = 20.0;
		accuracy = 12;
		date = new Date();
		debris = new Debris(LatLng.random().getLatitude(), LatLng.random()
				.getLongitude(),id.toString(), speed, accuracy, date);
		
		data.write(debris);
		debrisId = debris.getDebrisId();
		
	}

	@After
	public void tearDown() throws Exception {
		data.deleteAll();
	}

	@Test
	public void testGetInstance() {
		DataPersister d = DataPersisterInMemory.getInstance();
		assertEquals(data, d);
	}

	@Test
	public void testRead() {
		Debris deb = data.read(debrisId);
		assertEquals(debris, deb);
	}

	@Test
	public void testReadAllKeys() {
		idEnum = data.readAllKeys();
		// Test setup only inserts one Debris object into the data store so we
		// only expect one id
		long id = idEnum.nextElement();
		assertEquals(debrisId, id);
	}

	@Test
	public void testReadAllValues() {
		debrisEnum = data.readAllValues();
		// Test setup only inserts one Debris object into the data store
		Debris deb = debrisEnum.nextElement();
		assertEquals(debris, deb);
	}

	@Test
	public void testWrite() {
		date = new Date();
		debris = new Debris(LatLng.random().getLatitude(), LatLng.random()
				.getLongitude(),id.toString(), speed, accuracy, date);
		data.write(debris);
		debrisId = debris.getDebrisId();
		assertEquals(debris,data.read(debrisId));
	}

	@Test
	public void testDelete() {
		data.delete(debrisId);
		assertFalse(data.find(debrisId));
	}

	@Test
	public void testDeleteAll() {
		data.deleteAll();
		assertFalse(data.find(debris));
	}

	@Test
	public void testFindDebris() {
		assertTrue(data.find(debris));
	}

	@Test
	public void testFindGeoHash() {
		assertTrue(data.find(debrisId));

	}

}
