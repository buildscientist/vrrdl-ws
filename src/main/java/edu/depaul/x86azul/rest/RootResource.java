package edu.depaul.x86azul.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author Youssuf ElKalay
 *
 */

@Path("/")
public class RootResource {

	@GET
	@Produces(MediaType.TEXT_HTML)
	public String getRootPage() {
		String welcomeMessage = "<html><head><title>VRRDL-WS</title></head><body><p>Vehicle Related Road Debris Locator Web Service</p></body></html>";
		return welcomeMessage;
	}
}
