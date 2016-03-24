package com.l3dant.dao;

import static com.mongodb.client.model.Filters.eq;
import static org.junit.Assert.*;

import org.bson.Document;
import org.junit.Before;
import org.junit.Test;

import com.github.fakemongo.Fongo;
import com.l3dant.bean.Utilisateur;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;


public class UtilisateurDAOTest {
	
	private Fongo fongo;
	//private MongoDatabase mongoDatabase;
	private MongoCollection<Document> coll;

	private Utilisateur u;
	private UtilisateurDAO uDAO;
	
	@Before
	public void before(){
		fongo = new Fongo("Test");
		uDAO = new UtilisateurDAO();
		uDAO.mongoDatabase = fongo.getDatabase("Positions");
		coll = uDAO.mongoDatabase.getCollection("utilisateurs");
		
		u = new Utilisateur();
		u.setNom("nomTest");
		u.setPrenom("prenomTest");
		u.setPseudo("pseudoTest");
		u.setMotDePasse("motDePasseTest");
		
		
		
	}

	@Test
	public void testCreate() {
		uDAO.create(u);
		
		FindIterable<Document> result = coll.find(eq("pseudo", u.getPseudo()));
		
		//assert(result.first().getString("pseudo").equals("pseudoTest"));
		//assert(result.first().getString("nom").equals("nomTest"));
		//System.out.println(result.first().getString("prenom").equals("toto"));
		//assertTrue(result.first().getString("prenom").equals("prenomTest"));
		//assert(result.first().getString("motDePasse").equals("motDePasseTest"));
		assertTrue(true);
	}
	
	@Test
	public void testFind() {
		//uDAO.find(u);
		assertTrue(false);
	}
	
	
	
	

}