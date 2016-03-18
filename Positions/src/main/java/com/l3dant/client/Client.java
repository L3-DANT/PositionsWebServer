package com.l3dant.client;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import org.apache.http.client.ClientProtocolException;
import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

public class Client {

	public static void main(String[] args) throws Exception {
		//ResteasyClient client = new ResteasyClientBuilder().build();
		
		//ResteasyWebTarget target = client.target("http://localhost:8080/Positions/utilisateur/test");
		String st;
		try {
			ResteasyClient client = new ResteasyClientBuilder().build();
			ResteasyWebTarget target = client.target("http://localhost:8080/Positions/utilisateur/test");
			
			Response response = target.request().get();
			
			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}
			System.out.println("Server response : \n");
			
			System.out.println(response.readEntity(String.class));
			
			response.close();
			
			} catch (Exception e) {
				 e.printStackTrace();
			}
		
		
	}

}
			