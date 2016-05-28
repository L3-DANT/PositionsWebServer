package com.l3dant.bean;


public class Contact {

	private String pseudo;
	private Localisation loc;
	
	public Contact(){}
	
	public Contact(String pseudo, Localisation loc){
		this.pseudo = pseudo;
		this.loc = loc;
	}

	public String getPseudo() {
		return pseudo;
	}

	public Localisation getLoc() {
		return loc;
	}

	
}
