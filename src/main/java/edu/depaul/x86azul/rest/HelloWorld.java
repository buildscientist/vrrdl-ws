package edu.depaul.x86azul.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;


@Path("/helloworld")
public class HelloWorld {
	@GET
	@Produces("text/plain")
	public String getMessage() {
		return "Hello World\n";
	}
		
}
