package com.cucco.ejbclient;

import java.math.BigDecimal;

import javax.naming.NamingException;

import it.javaboss.services.AtmService;

public class EJBBankClient extends EJBClientGeneral<AtmService> {
	
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
			AtmService service = lookupRemote("ejb:/bank/AtmServiceImpl!it.javaboss.services.AtmService", true);
			if (service.autenticate("1111")) {
				// Stampa saldo iniziale
				System.out.println("Amount: " + service.balance());

				// Deposito
				service.deposit(BigDecimal.TEN);

				// Stampa saldo finale
				System.out.println("Amount: " + service.balance());
			}
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}
	
	public static void main(String[] args) {
		EJBBankClient ejbRemoteClient = new EJBBankClient();
		ejbRemoteClient.process();
	}
	
	

}
