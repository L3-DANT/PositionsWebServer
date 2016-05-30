package com.l3dant.serviceTest;

import static org.junit.Assert.*;

import java.util.List;

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
		Mockito.when(uDAO.find(u.getPseudo())).thenReturn(null);
		Mockito.when(uDAO.create(u)).thenReturn(u);
		
		//appel de la méthode de UtilisateurService
		Utilisateur ut = us.inscription(u);
		
		//tests
		assertTrue(ut.getPseudo().equals("pseudoTest"));
	}
	
	
	@Test
	public void testInscription_false(){
		Mockito.when(uDAO.find(u.getPseudo())).thenReturn(u);
		Mockito.when(uDAO.create(u)).thenReturn(u);
		
		Utilisateur ut = us.inscription(u);

		assertTrue(ut == null);
	}
	
	
	@Test
	public void testConnexion_true(){
		Mockito.when(uDAO.find(u.getPseudo())).thenReturn(u);
		
		Utilisateur ut = us.connexion(u);

		assertTrue(ut.equals("pseudoTest"));
	}
	
	
	@Test
	public void testConnexion_false(){
		u.setMotDePasse("mdpIncorrect");
		
		Mockito.when(uDAO.find(u.getPseudo())).thenReturn(null);
		
		Utilisateur ut = us.connexion(u);

		assertTrue(ut == null);
		u.setMotDePasse("motDePasseTest");
	}
	
	
	@Test
	public void testRechercheUsers_find(){
		//List<Utilisateur> uts = us.rechercheUsers("pseudo");
		
		//assertTrue(uts.size() != 0);
	}
	
	@Test
	public void testRechercheUsers_noNothing(){
		//List<Utilisateur> uts = us.rechercheUsers("vbdgs");
		
		//assertTrue(uts.size() == 0);
	}
	
	
	@Test
	public void testSupprimerCompte(){
		us.supprimerCompte(u);
		
		Utilisateur ut = uDAO.find(u.getPseudo());
		assertTrue(ut == null);
	}
	
	
	@Test
	public void testShareLocation(){
		u.setShareLocation(false);
		
		us.shareLocation(u);
		
		Utilisateur ut = uDAO.find(u.getPseudo());
		assertTrue(ut.isShareLocation() == false);
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
