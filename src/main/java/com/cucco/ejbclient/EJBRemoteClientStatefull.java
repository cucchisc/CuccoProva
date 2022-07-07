package com.cucco.ejbclient;

import javax.naming.NamingException;

import org.jboss.as.quickstarts.ejb.remote.stateful.RemoteCounter;

public class EJBRemoteClientStatefull extends EJBClientGeneral<RemoteCounter> {
	
	private static final String INITIAL_CONTEXT_FACTORY = "org.wildfly.naming.client.WildFlyInitialContextFactory";
	private static final String PROVIDER_URL = "remote+http://localhost:8080";

	@Override
	protected String getInitialContextFactory() {
		return INITIAL_CONTEXT_FACTORY;
	}

	@Override
	protected String getProviderUrl() {
		return PROVIDER_URL;
	}

	@Override
	public void process() {
		try {
			RemoteCounter statefulRemoteCounter = lookupRemote("ejb:/ejb-remote-server-side-1.0.0/CounterBean!org.jboss.as.quickstarts.ejb.remote.stateful.RemoteCounter", true);
	        System.out.println("Obtained a remote stateful counter for invocation");
	        // invoke on the remote counter bean
	        final int NUM_TIMES = 5;
	        System.out.println("Counter will now be incremented " + NUM_TIMES + " times");
	        for (int i = 0; i < NUM_TIMES; i++) {
	            System.out.println("Incrementing counter");
	            statefulRemoteCounter.increment();
	            System.out.println("Count after increment is " + statefulRemoteCounter.getCount());
	        }
	        // now decrementing
	        System.out.println("Counter will now be decremented " + NUM_TIMES + " times");
	        for (int i = NUM_TIMES; i > 0; i--) {
	            System.out.println("Decrementing counter");
	            statefulRemoteCounter.decrement();
	            System.out.println("Count after decrement is " + statefulRemoteCounter.getCount());
	        }
	        
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}
	
	public static void main(String[] args) {
		EJBRemoteClientStatefull ejbRemoteClient = new EJBRemoteClientStatefull();
		ejbRemoteClient.process();
	}
	
	

}
