package de.interfaceag;

import org.eclipse.microprofile.opentracing.Traced;

import javax.enterprise.context.ApplicationScoped;

@Traced
@ApplicationScoped
public class TracingService {

	public String hallo() {
		return "Hallo";
	}
}
