package com.l3dant.dao;

import org.bson.Document;

import com.l3dant.bean.Contact;
import static com.mongodb.client.model.Filters.*;

import java.util.List;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;

public class ContactDAO implements DAO<Contact>{
	
	private final MongoCollection<Document> collUser;
	
	public ContactDAO(){
		collUser = ConnexionMongo.getDatabase().getCollection("utilisateurs");
	}
	
	@Override
	public Contact find(String name) {

		return null;
	}

	@Override
	public Contact create(Contact c) {
		return c;
	}
	
	//pour vérifier si l'ami n'existe pas déjà, renvoie true s'il existe déjà
	public boolean find(String demandeur, String concerne) {
			FindIterable<Document> result = collUser.find(eq("pseudo", demandeur));
			Document document = result.first();
			
			//On récupère les contacts sous forme de liste de document
			List<Document> contacts = (List<Document>)document.get("contacts");
			for(Document contact : contacts){
				if(contact.get("pseudo").toString().compareTo(concerne) == 0)
					return true;
			}
			
			return false;
	}
		
		
	//A test
	public boolean create(String demandeur, String concerne) {
		collUser.findOneAndUpdate(
				eq("pseudo", demandeur),
				new Document("$push", new Document("contacts", new Document("pseudo", concerne)))		
		);
		
		collUser.findOneAndUpdate(
				eq("pseudo", concerne),
				new Document("$push", new Document("contacts", new Document("pseudo",demandeur)))			
		);
	
		return true;
	}
	
	@Override
	public Contact update(Contact t) {

		return null;
	}

	@Override
	public boolean delete(Contact t) {
		
		return false;
	}

}
