package com.l3dant.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.junit.Test;

public class Seb {

	@Test
	public void dummy() throws Exception {
		String getUrl = "http://localhost:8080/Positions/utilisateur/test";
		
		Client newClient = ClientBuilder.newClient();
		newClient.target(getUrl).request().get();


		
		/*ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target(getUrl);
        Response response = target.request().get();

        String value = response.readEntity(String.class);
        System.out.println(value);
        response.close();*/
	}
	
}
