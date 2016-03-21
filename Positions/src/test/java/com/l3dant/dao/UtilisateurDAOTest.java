package com.l3dant.dao;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;

import com.github.fakemongo.Fongo;
import com.l3dant.bean.Utilisateur;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;


public class UtilisateurDAOTest {
	
	private MongoClient mongoClient;
	private MongoDatabase mongoDatabase;

	private Utilisateur u;
	private UtilisateurDAO uDAO;
	
	@Before
	public void before(){
		mongoClient = new MongoClient("127.0.0.1", 27017);
		mongoDatabase = mongoClient.getDatabase("PositionsTest");
		
		Fongo f = new Fongo("mongo");
		DB db = f.getDB("mydb");
		DBCollection collection = db.getCollection("utilisateurs");
		
		u = new Utilisateur();
		u.setNom("nomTest");
		u.setPrenom("prenomTest");
		u.setPseudo("pseudoTest");
		u.setMotDePasse("motDePasseTest");
		
		uDAO = new UtilisateurDAO(mongoDatabase);
	}
	
	@Before
	public void setUp() {
	    MockitoAnnotations.initMocks(this);
	}
	
	@After
	public void after(){
		mongoClient.close();
	}
	
	@Test
	public void testInscrire() {
		uDAO.create(u);
		
		
		
	}
	
	@Test
	public void testConnexion() {
		
		
	}
	
	
	
	

}
