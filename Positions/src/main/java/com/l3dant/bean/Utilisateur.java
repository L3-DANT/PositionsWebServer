package com.l3dant.bean;

import java.util.List;

public class Utilisateur {
	private String nom;
	private String prenom;
	private String mail;
	private String pseudo;
	private String motDePasse;
	private Localisation localisation;
	private List<Utilisateur> contacts;
	private String token;
	
	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String getPrenom() {
		return prenom;
	}
	
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
	
	public String getPseudo() {
		return pseudo;
	}
	
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	
	public String getMotDePasse() {
		return motDePasse;
	}
	
	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}
	
	public Localisation getLocalisation() {
		return localisation;
	}
	
	public void setLocalisation(Localisation localisation) {
		this.localisation = localisation;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	
	public List<Utilisateur> getContacts() {
		return contacts;
	}


	public void setContacts(List<Utilisateur> contacts) {
		this.contacts = contacts;
	}

	public String toString(){
		return "[pseudo : " + this.pseudo + "]" ;
	}
	
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Utilisateur that = (Utilisateur) o;

		if (pseudo != null ? !pseudo.equals(that.pseudo) : that.pseudo != null) return false;
		return motDePasse != null ? motDePasse.equals(that.motDePasse) : that.motDePasse == null;

	}

	@Override
	public int hashCode() {
		int result = pseudo != null ? pseudo.hashCode() : 0;
		result = 31 * result + (motDePasse != null ? motDePasse.hashCode() : 0);
		return result;
	}
}
