package de.interfaceag;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.RestClientBuilder;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("/hello")
public class ExampleResource {

	@Inject
	TracingService service;

	@Context
	private UriInfo uriInfo;

	@ConfigProperty(name = "interface.neu")
	private String halloAusProperty;

	@ConfigProperty(name = "INTERFACE_GREETING")
	private String halloAusEnvironment;



	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String hello() {
		return "InterFace";
	}

	@GET
	@Path("/property")
	@Produces(MediaType.TEXT_PLAIN)
	public String helloProperty() {
		return this.halloAusProperty;
	}

	@GET
	@Path("/environment")
	@Produces(MediaType.TEXT_PLAIN)
	public String helloEnvironment() {
		return this.halloAusEnvironment;
	}

	@GET
	@Path("/chain")
	@Produces(MediaType.TEXT_PLAIN)
	public String chain() {
		ResourceClient resourceClient = RestClientBuilder.newBuilder().baseUri(uriInfo.getBaseUri()).build(ResourceClient.class);
		return "chain ->" + service.hallo() + " -> " + resourceClient.hello();
	}
}