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
	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setDemandeur(String demandeur) {
		this.demandeur = demandeur;
	}

	public void setConcerne(String concerne) {
		this.concerne = concerne;
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
	
	public void setAccept(String accept) {
		if(accept.equals("ACCEPTEE"))
			this.accept = StatutInvit.ACCEPTEE;
		else if(accept.equals("REFUSEE") )
			this.accept = StatutInvit.REFUSEE;
		else
			this.accept = StatutInvit.EN_ATTENTE;
	}
	/*
	public void setAccept(StatutInvit accept) {
		this.accept = accept;
	}*/
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date){
		this.date = date;
	}

	
	
	
}
