package edu.depaul.x86azul;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;

import com.javadocmd.simplelatlng.*;
import com.javadocmd.simplelatlng.util.LengthUnit;

import edu.depaul.x86azul.dataAccess.*;
import edu.depaul.x86azul.geo.*;

public class DebrisDAOTest {

	private DebrisDAO dao;
	private DebrisDTO dto;
	private Debris debris;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		debris = new Debris(LatLng.random().getLatitude(), LatLng.random()
				.getLongitude(), new Date());
		dto = new DebrisDTO();
		dao = new DebrisDAO();
		dto.addDebris(debris);
	}

	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void testGetDebris() {
		Debris deb = dao.getDebris(debris.getGeoHash());
		assertEquals(debris, deb);
	}

	@Test
	public void testIsDebrisInRange() {
		/*
		 * Create a random debris object and obtain its distance from the debris
		 * object created during setup(). Use that distance to discover whether
		 * the random debris created in this method is in range of other debris
		 * reported.
		 */
		Debris actual = new Debris(LatLng.random().getLatitude(), LatLng
				.random().getLongitude(), new Date());

		double distance = LatLngTool.distance(actual.getPoint(),
				debris.getPoint(), LengthUnit.KILOMETER);
		assertTrue(dao.isDebrisInRange(actual, distance));
	}

	@Test
	public void testGetAllPointsInRange() {
		Debris actual = new Debris(LatLng.random().getLatitude(), LatLng
				.random().getLongitude(), new Date());
		double distance = LatLngTool.distance(actual.getPoint(),
				debris.getPoint(), LengthUnit.KILOMETER);
		ArrayList<LatLng> points = dao.getAllPointsInRange(actual, distance);

		assertFalse(points.isEmpty());

	}

}
