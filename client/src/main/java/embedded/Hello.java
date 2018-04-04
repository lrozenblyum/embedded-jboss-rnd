package embedded;

import java.util.Hashtable;

import javax.naming.InitialContext;

import org.jboss.ejb3.embedded.EJB3StandaloneBootstrap;

import embedded.service.CustomerDAOLocal;

public class Hello {
	public static void main(String[] args) throws Exception {
		EJB3StandaloneBootstrap.boot(null);
		//TODO: need to add also native EmbeddedJBoss jars to the ignore list! They are renamed by Maven
		EJB3StandaloneBootstrap.ignoredJars.add("rt.jar");
		EJB3StandaloneBootstrap.ignoredJars.add("jfxrt.jar");
		
		//TODO: hack, need robust scanning. Doesn't work locally
		System.setProperty("java.class.path", System.getProperty("java.class.path") + ";" + "lib/embedded-0.0.1-SNAPSHOT.jar");
		System.out.println("Before scan classpath");
		EJB3StandaloneBootstrap.scanClasspath();

		System.out.println("DONE");

		InitialContext ctx = getInitialContext();
		CustomerDAOLocal local = (CustomerDAOLocal) ctx.lookup("CustomerDAOBean/local");
		int id = local.createCustomer("hello");
		System.out.println("Found: " + local.findCustomer(id).getName());
	}
	
	 public static InitialContext getInitialContext() throws Exception
	   {
	      Hashtable props = getInitialContextProperties();
	      return new InitialContext(props);
	   }

	   private static Hashtable getInitialContextProperties()
	   {
	      Hashtable props = new Hashtable();
	      props.put("java.naming.factory.initial", "org.jnp.interfaces.LocalOnlyContextFactory");
	      props.put("java.naming.factory.url.pkgs", "org.jboss.naming:org.jnp.interfaces");
	      return props;
	   }
}
