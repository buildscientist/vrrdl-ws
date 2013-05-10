package edu.depaul.x86azul;

import static org.junit.Assert.*;

import java.util.UUID;
import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.javadocmd.simplelatlng.LatLng;

import edu.depaul.x86azul.dataAccess.DebrisDAO;
import edu.depaul.x86azul.dataAccess.DebrisDTO;
import edu.depaul.x86azul.geo.Debris;

public class DebrisDTOTest {

	private DebrisDAO dao;
	private DebrisDTO dto;
	private Debris debris;
	private UUID id;
	private double speed;
	private double accuracy;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		id = UUID.randomUUID();
		speed = 20.0;
		accuracy = 12;
		debris = new Debris(LatLng.random().getLatitude(), LatLng.random()
				.getLongitude(),id.toString(), speed, accuracy, new Date());
		dto = new DebrisDTO();
		dao = new DebrisDAO();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddDebris() {
		dto.addDebris(debris);

		Debris debris2 = dao.getDebris(debris.getDebrisId());
		assertEquals(debris, debris2);
	}

	@Test
	public void testRemoveDebris() {
		dto.addDebris(debris);
		dto.removeDebris(debris.getDebrisId());

		Debris debris2 = dao.getDebris(debris.getDebrisId());
		assertNull(debris2);

	}

	@Test
	public void testRemoveAllDebris() {
		dto.addDebris(debris);
		dto.removeAllDebris();

		Debris debris2 = dao.getDebris(debris.getDebrisId());
		assertNull(debris2);

	}

}
