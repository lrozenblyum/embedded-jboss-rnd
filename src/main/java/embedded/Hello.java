package embedded;

import org.jboss.ejb3.embedded.EJB3StandaloneBootstrap;

public class Hello {
	public static void main(String[] args) {
		EJB3StandaloneBootstrap.boot( null );
		EJB3StandaloneBootstrap.ignoredJars.add("rt.jar");
		EJB3StandaloneBootstrap.scanClasspath();
		
		System.out.println("DONE");
		
		EJB3StandaloneBootstrap.shutdown();
	}
}
