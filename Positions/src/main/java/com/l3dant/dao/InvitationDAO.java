package com.l3dant.dao;

import static com.mongodb.client.model.Filters.eq;
import org.bson.Document;
import com.l3dant.bean.Invitation;
import com.mongodb.client.MongoCollection;

public class InvitationDAO implements DAO<Invitation>{
	
	private final MongoCollection<Document> collUser; //On r�cup�re la collection concernant les users parce que toutes les interactions vont s'effectuer sur la liste d'invit du user
	
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
	
	
	//On cr�e la nouvelle invitation sur les listes des deux utilisateurs concern�s
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
	
	
	//On supprime du c�t� de l'invit� et on met � jour l'invitation du c�t� demandeur
	@Override
	public Invitation update(Invitation i) {
		
		//suppression c�t� invit�
		collUser.findOneAndUpdate(
				eq("pseudo", i.getConcerne()),
				new Document("$pull", new Document("invits", new Document("demandeur", i.getDemandeur())
						.append("concerne", i.getConcerne())
					)
				)			
		);
		
		//update c�t� demandeur => pour notifs
		//degueulasse mais on a pas le choix, on doit avancer
		collUser.findOneAndUpdate(
				eq("pseudo", i.getDemandeur()),
				new Document("$pull", new Document("invits", new Document("demandeur", i.getDemandeur())
						.append("concerne", i.getConcerne())
					)
				)			
		);
		
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
		collUser.findOneAndUpdate(
				eq("pseudo", i.getDemandeur()) , 
				new Document("$set", new Document("invits", 
									new Document("demandeur", i.getDemandeur())
										.append("concerne", i.getConcerne())
										.append("date", i.getDate())
										.append("accept", i.getAccept())
									)
				)
		);*/
				
	
		return i;
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