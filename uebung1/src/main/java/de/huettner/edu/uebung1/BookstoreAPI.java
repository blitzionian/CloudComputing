package de.huettner.edu.uebung1;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.wadl.internal.WadlResource;
import org.springframework.stereotype.Component;

import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;

@Component
public class BookstoreAPI extends ResourceConfig {
	public BookstoreAPI() {
		register(JacksonFeature.class);
		register(BookResource.class);
		// register(BookExceptionMapper.class);
		register(WadlResource.class);

		register(ApiListingResource.class);
		register(SwaggerSerializers.class);

		BeanConfig beanConfig = new BeanConfig();
		beanConfig.setVersion("1.0.1");
		beanConfig.setSchemes(new String[] { "http" });
		beanConfig.setHost("localhost:8080");
		beanConfig.setBasePath("/api");
		beanConfig.setResourcePackage("de.huettner.edu.uebung1");
		beanConfig.setScan(true);
	}
}