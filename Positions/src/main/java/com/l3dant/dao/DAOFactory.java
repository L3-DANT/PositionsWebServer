package com.l3dant.dao;

import com.l3dant.bean.Localisation;
import com.l3dant.bean.Utilisateur;


public class DAOFactory {
	
	public static DAO<Utilisateur> getUtilisateurDAO(){
		return new UtilisateurDAO();
	}
	
	public static DAO<Localisation> getLocalisationDAO(){
		return new LocalisationDAO();
	}
	
	//mettre ici les autres servicesDAO
	
}
