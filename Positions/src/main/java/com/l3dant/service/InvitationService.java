package com.l3dant.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.google.gson.Gson;
import com.l3dant.bean.Contact;
import com.l3dant.bean.Invitation;
import com.l3dant.bean.Utilisateur;
import com.l3dant.dao.DAO;
import com.l3dant.dao.DAOFactory;
import com.l3dant.dao.InvitationDAO;
import com.l3dant.dao.UtilisateurDAO;
import com.l3dant.dao.ContactDAO;

@Path("/invitation")
@Produces("application/json")
@Consumes("application/json")
public class InvitationService {
	private static DAO<Invitation> iDAO = DAOFactory.getInvitationDAO();
	private static DAO<Contact> cDAO = DAOFactory.getContactDAO();
	private static DAO<Utilisateur> uDAO = DAOFactory.getUtilisateurDAO();
	
	//Méthode pour ajouter une invitation
	//return l'id de l'invitation
	@Path("/inviteFriend")
	@POST
	public Invitation inviterAmi(@QueryParam("demandeur") String demandeur, @QueryParam("concerne") String concerne){
		System.out.println("inviterAmi - dem:" + demandeur + " - conc:" + concerne);
		
		String date = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
		Invitation i = new Invitation(demandeur, concerne, date);
		
		//on veut que le find du iDAO renvoie true lorsque l'invitation existe
		if(!((InvitationDAO)iDAO).find(demandeur, concerne) && !((ContactDAO)cDAO).find(demandeur,concerne) && ((UtilisateurDAO)uDAO).doExist(demandeur) && ((UtilisateurDAO)uDAO).doExist(concerne) )
			return iDAO.create(i);

		return null;		
		//return new Gson().toJson(iDAO.update(i));
	}
	
	//A la fin de l'acceptation ou du refus on supprime l'invitation du côté de l'invité
	//On change la date à la date d'acceptation ou refus pour la notifier au demandeur
	//Dans la partie iOS on envoie que le demandeur et l'invité
	@Path("/decision")
	@POST
	public Contact decision(@QueryParam("b") boolean b, @QueryParam("demandeur") String demandeur, @QueryParam("concerne") String concerne){
		System.out.println("decision - b:" + b + " - dem:" + demandeur + " - conc:" + concerne);
		
		Invitation i = new Invitation();
		i.setDemandeur(demandeur);
		i.setConcerne(concerne);
		i.setDate(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime()));
		
		 //on veut savoir si l'invitation existe et est en attente
		if(!((InvitationDAO)iDAO).findWithoutAccept(i.getDemandeur(), i.getConcerne()) ){
			System.out.println("invitation déjà existante");
			return null;
		}
		
		if(b){
			System.out.println("accepté");
			//i.setAccept(StatutInvit.ACCEPTEE);
			i.setAccept("ACCEPTEE");
			iDAO.update(i);
			return ContactService.addAmi(i.getDemandeur(), i.getConcerne());
		}	
		else {
			System.out.println("refusé");
			//i.setAccept(StatutInvit.REFUSEE);
			i.setAccept("REFUSEE");
		}
		
		iDAO.update(i);
		
		return null;
	}
	
	@Path("/recupInvits")
	@POST
	public String getInvits(@QueryParam("pseudo") String pseudo){
		System.out.println("recupInvits - pseudo:" + pseudo);
		List<Invitation> is = ((InvitationDAO)iDAO).getInvits(pseudo);
		System.out.println("nb invit : " + is.size());
		
		return new Gson().toJson(is); //nécessaire?
	}
	
	
	//On supprime l'invit du côté de celui qui a fait la demande
	//Dans la partie iOS on envoie que le demandeur et l'invité
	@Path("/supprInvit")
	@POST
	public Contact supprInvit(Invitation i){
		System.out.println("supprInvit - conc:" + i.getConcerne() + " - dem:" + i.getDemandeur());
		iDAO.delete(i);
		
		Utilisateur u = uDAO.find(i.getConcerne());
		Contact c = new Contact();
		c.setPseudo(u.getPseudo());
		c.setLoc(u.getLocalisation());
		
		System.out.println("pseudo:"+c.getPseudo() + "-lng:" +c.getLoc().getLongitude());
		
		return c;
	}
	
	
	
}
