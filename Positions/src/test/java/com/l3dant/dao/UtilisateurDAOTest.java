package com.l3dant.dao;

import static com.mongodb.client.model.Filters.eq;
import static org.junit.Assert.*;

import java.util.List;

import org.bson.Document;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;

import com.l3dant.bean.Localisation;
import com.l3dant.bean.Utilisateur;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.junit.runners.MethodSorters;

// ATTENTION - il faut bien configurer la connexion à mongodb pour que les tests fonctionnent
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UtilisateurDAOTest {
	private MongoCollection<Document> coll;
	private Utilisateur u;
	private UtilisateurDAO uDAO;
	
	@Before
	public void before(){
		uDAO = new UtilisateurDAO();
		coll = ConnexionMongo.getDatabase().getCollection("utilisateurs");
		
		u = new Utilisateur();
		u.setNom("nomTest");
		u.setPrenom("prenomTest");
		u.setPseudo("pseudoTest");
		u.setMotDePasse("motDePasseTest");
		u.setShareLocation(true);
	}
	
	
	@Test
	public void testFind() {
		Utilisateur ut = uDAO.find(u.getPseudo());
		
		assertTrue(ut.getPseudo().equals("pseudoTest"));
		assertTrue(ut.getNom().equals("nomTest"));
		assertTrue(ut.getPrenom().equals("prenomTest"));
		assertTrue(ut.getMotDePasse().equals("motDePasseTest"));
	}
	
	
	@Test
	public void testFindViaPrefix() {
		List<Utilisateur> us = uDAO.findViaPrefix("pseudo");
		Utilisateur ut = us.get(0);
		
		assertTrue(ut.getPseudo().equals("pseudoTest"));
	}
	
	
	@Test
	public void testCreate() {
		uDAO.create(u);
		
		FindIterable<Document> result = coll.find(eq("pseudo", u.getPseudo()));
		
		assertTrue(result.first().getString("pseudo").equals("pseudoTest"));
		assertTrue(result.first().getString("nom").equals("nomTest"));
		assertTrue(result.first().getString("prenom").equals("prenomTest"));
		assertTrue(result.first().getString("motDePasse").equals("motDePasseTest"));
		assertTrue(result.first().getBoolean("shareLocation") == true);
	}
	
	
	@Test
	public void testUpdate() {
		double lng = 49.2;
		double lat = 2.78;
		String h = "22:45";
		String date = "28/05/2016";
		
		Localisation l = new Localisation();
		l.setLongitude(lng);
		l.setLatitude(lat);
		l.setDate(date);
		l.setHeure(h);
		
		u.setLocalisation(l);
		uDAO.update(u);
		
		u = uDAO.find(u.getPseudo());
		
		assertTrue(u.getLocalisation().getLongitude() == lng);
		assertTrue(u.getLocalisation().getLatitude() == lat);
		assertTrue(u.getLocalisation().getHeure().equals(h));
		assertTrue(u.getLocalisation().getDate().equals(date));
	}
	
	
	@Test
	public void testDelete() {
		uDAO.delete(u);
		Utilisateur ut = uDAO.find(u.getPseudo());
		assertTrue(ut == null);
		
		uDAO.create(u); // pour éviter que les autres méthodes plantent
	}
	
	
	@Test
	public void testShareLocation() {
		u.setShareLocation(false);
		uDAO.shareLocation(u);
		
		FindIterable<Document> result = coll.find(eq("pseudo", u.getPseudo()));
		boolean b = result.first().getBoolean("shareLocation");
		
		assertTrue(b == false);
	}
	
}