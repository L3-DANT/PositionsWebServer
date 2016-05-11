package com.l3dant.bean;

public class Invitation {
	
	private String demandeur; //Les pseudos
	private String concerne;
	private String date;
	private StatutInvit accept;
	
	public Invitation(String demandeur, String concerne, String date){
		this.demandeur = demandeur;
		this.concerne = concerne;
		this.date = date;
		this.accept = StatutInvit.EN_ATTENTE;
	}
	
	
}
