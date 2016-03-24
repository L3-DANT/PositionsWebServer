package com.l3dant.serviceTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.l3dant.bean.Utilisateur;
import com.l3dant.dao.UtilisateurDAO;
import com.l3dant.service.UtilisateurService;

@RunWith(MockitoJUnitRunner.class)
public class UtilisateurServiceTest {

	@Mock
	UtilisateurDAO uDAO;
	
	private UtilisateurService us;
	private Utilisateur u;
	
	@Before
	public void before(){
		u = new Utilisateur();
		u.setNom("nomTest");
		u.setPrenom("prenomTest");
		u.setPseudo("pseudoTest");
		u.setMotDePasse("motDePasseTest");
		
		us = new UtilisateurService();
	}
	
	
	@Test
	public void testInscription_true(){
		//comportement des méthodes de utilisateurDAO appelées dans la méthode testée
		Mockito.when(uDAO.find(u)).thenReturn(null);
		Mockito.when(uDAO.create(u)).thenReturn(true);
		
		//appel de la méthode de UtilisateurService
		boolean b = us.inscription(u);
		
		//tests
		assertTrue(b);
	}
	
	@Test
	public void testInscription_false(){
		Mockito.when(uDAO.find(u)).thenReturn(u);
		Mockito.when(uDAO.create(u)).thenReturn(false);
		
		boolean b = us.inscription(u);
		
		assertFalse(b);
	}
	
	
	@Test
	public void testConnexion_true(){
		Mockito.when(uDAO.find(u)).thenReturn(u);
		
		boolean b = us.connexion(u);
		
		assertTrue(b);
	}
	
	@Test
	public void testConnexion_false(){
		u.setMotDePasse("mdpIncorrect");
		
		Mockito.when(uDAO.find(u)).thenReturn(null);
		
		boolean b = us.connexion(u);
		
		assertFalse(b);
		u.setMotDePasse("motDePasseTest");
	}
	
	/* NE PAS SUPPRIMER CETTE METHODE
	public void simulationCLient(){
		String getUrl = "http://localhost:8080/Positions/utilisateur/test";
		ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target(getUrl);
        Response response = target.request().get();
        String value = response.readEntity(String.class);
        System.out.println(value);
        response.close();
	}*/

	

}
