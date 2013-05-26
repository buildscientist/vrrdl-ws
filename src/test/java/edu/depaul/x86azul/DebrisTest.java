package edu.depaul.x86azul;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.UUID;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.depaul.x86azul.geo.Debris;

public class DebrisTest {

	private Debris debris;
	private final int MAXLATITUDE = 90;
	private final int MAXLONGITUDE = 180;
	private Random random = new Random();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		double latitude = random.nextDouble() * random.nextInt(MAXLATITUDE);
		double longitude = random.nextDouble() * random.nextInt(MAXLONGITUDE)
				* -1;
		String id = UUID.randomUUID().toString();
		Date timeDate = new Date();
		debris = new Debris(latitude, longitude, id, timeDate);

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testLatitudeOutOfMinRange() {
		debris.setLatitude(random.nextDouble() * random.nextInt(MAXLONGITUDE) * -1000);
		assertFalse(debris.passesBeanValidation());
	}
	
	@Test
	public void testLatitudeOutOfMaxRange() {
		debris.setLatitude(random.nextDouble() * random.nextInt(MAXLONGITUDE) * 1000);
		assertFalse(debris.passesBeanValidation());
	}
	
	@Test
	public void testLongitudeOutOfMinRange() {
		debris.setLongitude(random.nextDouble() * random.nextInt(MAXLATITUDE) * -1000);
		assertFalse(debris.passesBeanValidation());
	}
	
	@Test
	public void testLongitudeOutOfMaxRange() {
		debris.setLongitude(random.nextDouble() * random.nextInt(MAXLATITUDE) * 1000);
		assertFalse(debris.passesBeanValidation());
	}

	
	@Test
	public void testDateInThePast() {
		GregorianCalendar pastDate = new GregorianCalendar();
		pastDate.set(1900, 1, 1, 0, 0);
		debris.setDateTime(pastDate.getTime());
		assertTrue(debris.passesBeanValidation());
	}
	
	@Test
	public void testDateInTheFuture() {
		GregorianCalendar futureDate = new GregorianCalendar();
		futureDate.set(3000, 1, 1, 0, 0);
		debris.setDateTime(futureDate.getTime());
		assertFalse(debris.passesBeanValidation());
	}
	
}
