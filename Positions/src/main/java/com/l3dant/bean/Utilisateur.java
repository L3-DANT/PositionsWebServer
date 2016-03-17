package com.l3dant.bean;

public class Utilisateur {
	private String nom;
	private String prenom;
	private String pseudo;
	private String motDePasse;
	private Localisation localisation;
	
	
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
	
	
	
	
	
}
