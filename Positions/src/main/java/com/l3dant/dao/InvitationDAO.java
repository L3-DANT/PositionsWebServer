package com.l3dant.dao;

import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import com.l3dant.bean.Invitation;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
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

	public List<Invitation> getInvits(String pseudo) {
		FindIterable<Document> result = collUser.find(eq("pseudo", pseudo));
		List<Invitation> invitations= new ArrayList<Invitation>();
		for(Document document : result){
			for(Document d : (List<Document>)(document.get("invits"))){
				Invitation i = new Invitation();
				System.out.println("document :" + d);
				System.out.println("dema,deur :" +  d.getString("demandeur"));
				i.setDemandeur(d.getString("demandeur"));
				i.setConcerne(d.getString("concerne"));
				i.setDate(d.getString("date"));
				i.setAccept(d.getString("accept"));
				invitations.add(i);
			}
		}
		System.out.println(invitations);
		return invitations;
	}
	
	
	//pour vérifier si l'invitation n'existe pas déjà, renvoie true si elle existe déjà
	public boolean find(String demandeur, String concerne) {
		FindIterable<Document> result = collUser.find(eq("pseudo", demandeur));
		Document document = result.first();
		
		//On récupère les invitations sous forme de liste de document
		List<Document> invitations = (List<Document>)document.get("invits");
		for(Document invitation : invitations){
			if(invitation.get("concerne").toString().compareTo(concerne) == 0)
				return true;
		}
		
		return false;
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
	
	
	//On supprime du côté de l'invité et on met à jour l'invitation du côté demandeur
	@Override
	public Invitation update(Invitation i) {
		
		//suppression côté invité
		collUser.findOneAndUpdate(
				eq("pseudo", i.getConcerne()),
				new Document("$pull", new Document("invits", new Document("demandeur", i.getDemandeur())
						.append("concerne", i.getConcerne())
					)
				)			
		);
		
		//update côté demandeur => pour notifs
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
