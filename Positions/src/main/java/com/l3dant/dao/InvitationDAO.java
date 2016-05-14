package com.l3dant.dao;

import static com.mongodb.client.model.Filters.eq;
import static java.util.Arrays.asList;
import org.bson.Document;

import com.l3dant.bean.Invitation;
import com.l3dant.bean.Utilisateur;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;

public class InvitationDAO implements DAO<Invitation>{
	
	private final MongoCollection<Document> collUser; //On récupère la collection concernant les users parce que toutes les interactions vont s'effectuer sur la liste d'invit du user
	
	public InvitationDAO() {
		collUser = ConnexionMongo.getDatabase().getCollection("utilisateurs");
	}
	
	@Override
	public Invitation find(String name) {
		return null;
	}
	
	
	public Invitation find(String demandeur, String concerne) {
		
		return null;
	}
	
	
	//On crée la nouvelle invitation sur les listes des deux utilisateurs concernés
	@Override
	public Invitation create(Invitation i) {
		
		collUser.findOneAndUpdate(
				eq("pseudo", i.getDemandeur()),
				new Document("$push", new Document("invits", new Document("demandeur", i.getDemandeur())
						.append("concerne", i.getConcerne())
						.append("date", i.getDate())
						.append("accept", i.getAccept())
						)					
				)		
		);
		
		/*
		DAO<Utilisateur> uDAO = DAOFactory.getUtilisateurDAO();
		BasicDBObject match = new BasicDBObject();
		
		Utilisateur demandeur = uDAO.find(i.getDemandeur());
		match.put("_id", demandeur.getId());
		
		BasicDBObject dbo = new BasicDBObject();
		dbo.put("demandeur", i.getDemandeur());
		dbo.put("concerne", i.getConcerne());
		dbo.put("date", i.getDate());
		dbo.put("accept", i.getAccept());
		
		BasicDBObject update = new BasicDBObject();
		update.put("$push", new BasicDBObject("invits", dbo));
		
		collUser.updateOne(match, dbo);*/
		
		
		
		collUser.findOneAndUpdate(
				eq("pseudo", i.getConcerne()),
				new Document("$push", new Document("invits", new Document("demandeur", i.getDemandeur())
						.append("concerne", i.getConcerne())
						.append("date", i.getDate())
						.append("accept", i.getAccept())
						
						)
				)			
		);

		return i;
	}

	@Override
	public Invitation update(Invitation t) {
		/*collUser.findOneAndUpdate(
				eq("pseudo", t.getDemandeur()),
				new Document("$set", new Document("demandeur", i.getDemandeur()))
					.append("$set", new Document("concerne", i.getConcerne()))
					.append("$set", new Document("date", i.getDate()))
					.append("$set", new Document("mail", u.getMail()))
					.append("$set", new Document("localisation", 
										new Document("latitude", u.getLocalisation().getLatitude())
										.append("longitude", u.getLocalisation().getLongitude())
										.append("date", u.getLocalisation().getDate())
										.append("heure", u.getLocalisation().getHeure())
										)
					)
        );
		
		insertOne(
				new Document()
                .append("demandeur", i.getDemandeur())
                .append("concerne", i.getConcerne())
                .append("date", i.getDate())
                .append("accept", i.getAccept())
                );
		
		return i;*/
		return t;
	}

	@Override
	//Delete sert uniquement pour supprimer chez le demandeur
	public boolean delete(Invitation i) {
		collUser.findOneAndUpdate(
				eq("pseudo", i.getDemandeur()),
				new Document("$pull", new Document("invits", new Document("demandeur", i.getDemandeur())
						.append("concerne", i.getConcerne())
					)
				)			
		);
		return false;
	}

}
