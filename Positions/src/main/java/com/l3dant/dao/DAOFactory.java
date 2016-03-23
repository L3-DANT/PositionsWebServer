package com.l3dant.dao;

import com.l3dant.bean.Utilisateur;


public class DAOFactory {
	
	public static DAO<Utilisateur> getUtilisateurDAO(){
		return new UtilisateurDAO();
	}
	
	//mettre ici les autres servicesDAO
	
}
