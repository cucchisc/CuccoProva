package com.cucco.ejbclient;

import javax.naming.NamingException;

import org.jboss.as.quickstarts.ejb.remote.stateless.RemoteCalculator;

public class EJBRemoteClientStateless extends EJBClientGeneral<RemoteCalculator> {
	
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
			RemoteCalculator statelessRemoteCalculator = lookupRemote("ejb:/ejb-remote-server-side-1.0.0/CalculatorBean!org.jboss.as.quickstarts.ejb.remote.stateless.RemoteCalculator", false);
			System.out.println("Obtained a remote stateless calculator for invocation");
	        // invoke on the remote calculator
	        int a = 204;
	        int b = 340;
	        System.out.println("Adding " + a + " and " + b + " via the remote stateless calculator deployed on the server");
	        int sum = statelessRemoteCalculator.add(a, b);
	        System.out.println("Remote calculator returned sum = " + sum);
	        if (sum != a + b) {
	            throw new RuntimeException("Remote stateless calculator returned an incorrect sum " + sum + " ,expected sum was "
	                + (a + b));
	        }
	        // try one more invocation, this time for subtraction
	        int num1 = 3434;
	        int num2 = 2332;
	        System.out.println("Subtracting " + num2 + " from " + num1
	            + " via the remote stateless calculator deployed on the server");
	        int difference = statelessRemoteCalculator.subtract(num1, num2);
	        System.out.println("Remote calculator returned difference = " + difference);
	        if (difference != num1 - num2) {
	            throw new RuntimeException("Remote stateless calculator returned an incorrect difference " + difference
	                + " ,expected difference was " + (num1 - num2));
	        }
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}
	
	public static void main(String[] args) {
		EJBRemoteClientStateless ejbRemoteClient = new EJBRemoteClientStateless();
		ejbRemoteClient.process();
	}
	
	

}
