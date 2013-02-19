package edu.depaul.x86azul;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.Date;
import java.util.Enumeration;

import edu.depaul.x86azul.dataAccess.*;
import edu.depaul.x86azul.geo.*;
import com.javadocmd.simplelatlng.*;

public class DataPersisterInMemoryTest {

	private static DataPersister data;
	private Debris debris;
	private Date date;
	private String geoHash;
	private Enumeration<String> hashEnum;
	private Enumeration<Debris> debrisEnum;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		data = DataPersisterInMemory.getInstance();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		date = new Date();
		debris = new Debris(LatLng.random().getLatitude(), LatLng.random()
				.getLongitude(), date);
		geoHash = Geohasher.hash(debris.getPoint());
		data.write(geoHash, debris);
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
		Debris deb = data.read(geoHash);
		String hash = Geohasher.hash(deb.getPoint());
		assertEquals(geoHash, hash);
	}

	@Test
	public void testReadAllKeys() {
		hashEnum = data.readAllKeys();
		// Test setup only inserts one Debris object into the data store so we
		// only expect one geohash
		String hash = hashEnum.nextElement();
		assertEquals(geoHash, hash);
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
				.getLongitude(), date);
		geoHash = Geohasher.hash(debris.getPoint());
		data.write(geoHash, debris);
		assertEquals(debris,data.read(geoHash));
	}

	@Test
	public void testDelete() {
		data.delete(geoHash);
		assertFalse(data.find(geoHash));
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
		assertTrue(data.find(geoHash));

	}

}
