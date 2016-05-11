package com.l3dant.serviceTest;

import static org.junit.Assert.*;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import com.l3dant.bean.Utilisateur;
import com.l3dant.dao.DAOFactory;
import com.l3dant.dao.UtilisateurDAO;

public class UtilisateurServiceTest {

	@Mock
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
	public void testInscription(){
		//u => json

		/*JSONObject obj = new JSONObject();

	    obj.put("nom", u.getNom());
	    obj.put("prenom", u.getPrenom());
	    obj.put("pseudo", u.getPseudo());
	    obj.put("motDePasse", u.getMotDePasse());
		*/
		
		//inscription(u)
		//UtilisateurDAO uDAO = DAOFactory.getUtilisateurDAO();
		//uDAO.create(u);
		
		//récup de l'utilisateur et comparaison avec celui créé
		
		
		
		
	}
	
	@Test
	public void testConnexion(){
		
	}
	
	

}
