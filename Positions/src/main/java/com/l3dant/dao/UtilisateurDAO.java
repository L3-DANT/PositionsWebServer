package com.l3dant.dao;

import org.bson.Document;

import com.l3dant.bean.Invitation;
import com.l3dant.bean.Localisation;
import com.l3dant.bean.Utilisateur;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import static com.mongodb.client.model.Filters.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;


public class UtilisateurDAO implements DAO<Utilisateur>{

	private final MongoCollection<Document> collUtilisateurs;
	
	public UtilisateurDAO() {
		collUtilisateurs = ConnexionMongo.getDatabase().getCollection("utilisateurs");
	}
	
	//si existe renvoie true
	public boolean doExist(String pseudo) {
		FindIterable<Document> result = collUtilisateurs.find(eq("pseudo", pseudo));
		Document document = result.first();

		return document != null;
	}
	
	
	@Override
	public Utilisateur find(String name) {
		FindIterable<Document> result = collUtilisateurs.find(eq("pseudo", name));
		Document document = result.first();
		if(document == null){
			return null;
		}

		Utilisateur ut = new Utilisateur();
		ut.setId(document.getObjectId("_id"));
		ut.setNom(document.getString("nom"));
		ut.setPrenom(document.getString("prenom"));
		ut.setPseudo(document.getString("pseudo"));
		ut.setMotDePasse(document.getString("motDePasse"));
		ut.setMail(document.getString("mail"));
		/*List<Invitation> invitations= new ArrayList<Invitation>();
		for(Document d : (List<Document>)document.get("invits")){
			Invitation i = new Invitation();
			i.setDemandeur(d.getString("demandeur"));
			i.setConcerne(d.getString("concerne"));
			i.setDate(d.getString("date"));
			//i.setAccept(d.getString("accept"));
			invitations.add(i);
		}
		ut.setInvits(invitations);*/
		Document doc = (Document) document.get("localisation");

		if(doc != null){
			Localisation loc = new Localisation();
			loc.setLongitude(doc.getDouble("longitude"));
			loc.setLatitude(doc.getDouble("latitude"));
			loc.setDate(doc.getString("date"));
			loc.setHeure(doc.getString("heure"));
			ut.setLocalisation(loc);
		}
		return ut;
	}
	
	public List<Utilisateur> findViaPrefix(String prefix){
		ArrayList<Utilisateur> newFriends = new ArrayList<Utilisateur>();
		BasicDBObject query = new BasicDBObject();
		
		//On enlève certains caractere dechappement (espaces, tabulation et sauts de ligne) du prefix
		//attention string immutable
		prefix = prefix.replaceAll("\r", "");
		prefix = prefix.replaceAll("\t", "");
		prefix = prefix.replaceAll("\\s+", ""); //Un espace ou plus
		prefix = prefix.replaceAll("\n", "");
		
		Pattern p = Pattern.compile("^" + prefix, Pattern.CASE_INSENSITIVE); //Regex pour trouver tous les mots ayant ce prefixe, sans prendre en compte la casse
		System.out.println(p);
		query.put("pseudo", p);
		FindIterable<Document> result = collUtilisateurs.find(query);
		
		
		for(Document doc : result){
			Utilisateur u = new Utilisateur();
			u.setPseudo(doc.getString("pseudo"));
			newFriends.add(u);
		}
		System.out.println(newFriends);
		
		return newFriends;
	}
	
	@Override
	public Utilisateur create(Utilisateur u) {
		collUtilisateurs.insertOne(
				new Document()
                .append("nom", u.getNom())
                .append("prenom", u.getPrenom())
                .append("pseudo", u.getPseudo())
                .append("mail", u.getMail())
                .append("motDePasse", u.getMotDePasse())
                .append("token", u.getToken())
                .append("contacts", new ArrayList<>())
                .append("invits", new ArrayList<>())
                .append("localisation", new Document().append("latitude", 0.0)
                									  .append("longitude", 0.0)
                									  .append("date", "")
                									  .append("heure", ""))

                );
		
		return u;
	}

	@Override
	public Utilisateur update(Utilisateur u) {
		collUtilisateurs.findOneAndUpdate(
				eq("pseudo", u.getPseudo()),
				new Document("$set", new Document("nom", u.getNom()))
					.append("$set", new Document("prenom", u.getPrenom()))
					.append("$set", new Document("motDePasse", u.getMotDePasse()))
					.append("$set", new Document("mail", u.getMail()))
					.append("$set", new Document("localisation", 
										new Document("latitude", u.getLocalisation().getLatitude())
										.append("longitude", u.getLocalisation().getLongitude())
										.append("date", u.getLocalisation().getDate())
										.append("heure", u.getLocalisation().getHeure())
										)
					)
                );
		
		return u;
	}

	@Override
	public boolean delete(Utilisateur u) {
		collUtilisateurs.deleteOne(eq("pseudo", u.getPseudo()));
		return true;
	}
	
}
