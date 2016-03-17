package com.l3dant.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.l3dant.bean.Contact;
import com.l3dant.bean.Localisation;
import com.l3dant.bean.Utilisateur;
import com.l3dant.dao.UtilisateurDAO;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

@Path("/utilisateur")
public class Service {
	
	private MongoClient m;

	@GET
	@Produces("application/json")
    public List<Contact> getContacts() throws SQLException {
		System.out.println("getContacts");
		List<Contact> obj2 = new ArrayList<Contact>();
		 
		Contact seb = new Contact("Duchenne","Sebastien");
		Contact davy = new Contact("Ly","Davy");
		
		obj2.add(seb);
		obj2.add(davy);
		
        return obj2;
    }
	
	public MongoDatabase connectionBase(){
		m = new MongoClient("127.0.0.1", 27017);
		MongoDatabase db = m.getDatabase("Positions");
		return db;
	}
	
}
