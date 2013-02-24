package edu.depaul.x86azul.jacksonjsonprovider;


import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;

/*
 * @author Youssuf ElKalay
 * A custom Jackson ObjectMapperProvider so that we can set Jackson options that are used with Jersey.
 */

@Provider
public class ObjectMapperProvider implements ContextResolver<ObjectMapper> {
	private ObjectMapper objectMapper;

	public ObjectMapperProvider() {
		objectMapper = new ObjectMapper();
		objectMapper.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, false);

	}

	public ObjectMapper getContext(Class<?> type) {
		return objectMapper;
	}

}
