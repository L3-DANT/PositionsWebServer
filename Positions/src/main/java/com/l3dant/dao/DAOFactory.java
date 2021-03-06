package com.l3dant.dao;

import com.l3dant.bean.Contact;
import com.l3dant.bean.Invitation;
import com.l3dant.bean.Localisation;
import com.l3dant.bean.Utilisateur;


public class DAOFactory {
	
	public static DAO<Utilisateur> getUtilisateurDAO(){
		return new UtilisateurDAO();
	}
	
	public static DAO<Localisation> getLocalisationDAO(){
		return new LocalisationDAO();
	}
	
	public static DAO<Contact> getContactDAO(){
		return new ContactDAO();
	}
	
	public static DAO<Invitation> getInvitationDAO(){
		return new InvitationDAO();
	}
	
	//mettre ici les autres servicesDAO
	
}
