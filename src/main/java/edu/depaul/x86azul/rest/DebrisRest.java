package edu.depaul.x86azul.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/debris")
public class DebrisRest {
	@GET
	@Produces("application/json")
	public String listDebris() {
		return "Debris";
	}

	
	
}
