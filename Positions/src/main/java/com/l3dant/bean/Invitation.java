package com.l3dant.bean;

public class Invitation {
	
	private int id;
	private String demandeur; //Les pseudos
	private String concerne;
	private String date;
	private StatutInvit accept;
	
	public Invitation(){}
	
	public Invitation(String demandeur, String concerne, String date){
		this.demandeur = demandeur;
		this.concerne = concerne;
		this.date = date;
		this.accept = StatutInvit.EN_ATTENTE;
	}
	
	
	
	public String getDemandeur() {
		return demandeur;
	}

	public String getConcerne() {
		return concerne;
	}

	public String getAccept() {
		return accept.toString();
	}

	public String getDate() {
		return date;
	}
	
	
}
