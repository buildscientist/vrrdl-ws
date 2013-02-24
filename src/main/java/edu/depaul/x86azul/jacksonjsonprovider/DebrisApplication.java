package edu.depaul.x86azul.jacksonjsonprovider;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

import edu.depaul.x86azul.rest.*;

/*
 * @author Youssuf ElKalay
 * 
 * This main purpose of this class is such that the ObjectMapperProvider used with
 * Jackson can be registered as a valid provider for all classes listed below. 
 * A dependency injection framework like Guice should used but the cost of importing/using
 * that framework isn't warranted given a single RESTful resource.
 */
public class DebrisApplication extends Application {

	@Override
	public Set<Class<?>> getClasses() {

		final Set<Class<?>> classes = new HashSet<Class<?>>();

		// register root resources
		classes.add(DebrisResource.class);
		
		// register Jackson ObjectMapper resolver
		classes.add(ObjectMapperProvider.class);

		return classes;
	}

}
