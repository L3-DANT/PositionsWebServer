package com.l3dant.client;


import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.junit.Before;
import org.junit.Test;

import com.l3dant.bean.Utilisateur;

public class UtilisateurServiceTest {
	//private WebClient clt;
	private Utilisateur u;
	
	@Before
	public void before(){
		u = new Utilisateur();
		u.setNom("Duchenne");
		u.setPrenom("Sébastien");
		u.setPseudo("seb");
		u.setMotDePasse("sebastien");
	}
	
	@Test
	public void inscription() throws Exception {
		String getUrl = "http://localhost:8080/Positions/utilisateur/inscription";
		
		/*Client newClient = ClientBuilder.newClient();
		newClient.target(getUrl).request().get();*/
		
		
		
		ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target(getUrl);
        Response response = target.request().get();
        String value = response.readEntity(String.class);
        System.out.println(value);
        response.close();
	}
	
	
	@Test
	public void connexion()throws Exception {
		String getUrl = "http://localhost:8080/Positions/utilisateur/connexion";
		
		
		
		
		
	}
	
	
}
