package com.l3dant.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import org.apache.commons.lang3.RandomStringUtils;

import com.google.gson.Gson;
import com.l3dant.bean.*;
import com.l3dant.dao.DAO;
import com.l3dant.dao.DAOFactory;
import com.l3dant.dao.UtilisateurDAO;

@Path("/utilisateur")
@Produces("application/json")
@Consumes("application/json")
public class UtilisateurService {
	
	private static DAO<Utilisateur> uDAO = DAOFactory.getUtilisateurDAO();
	
	@POST
	@Path("/inscription")
	public Utilisateur inscription(Utilisateur u){
		System.out.println("inscription");
		
		Utilisateur ut = uDAO.find(u.getPseudo());
		if(ut == null){
			u.setToken(RandomStringUtils.random(32, true, true));
			uDAO.create(u);
			u.setMotDePasse("");
			return u;
		}
		return null;
	}
	
	@POST
	@Path("/connexion")
	public Utilisateur connexion(Utilisateur u){
		System.out.println("connexion - pseudo:" + u.getPseudo());
		Utilisateur ut = uDAO.find(u.getPseudo());
		if(ut != null && ut.getMotDePasse().equals(u.getMotDePasse())){
			ut.setMotDePasse("");
			return ut;
		}
		return null;
	}
	

	@Path("/getFriends")
	@POST
	public String getFriends(@QueryParam("pseudo") String pseudo){
		System.out.println("getFriends - pseudo:" + pseudo);
		
		Utilisateur u;
		try{
			u = uDAO.find(pseudo);
		}catch(NullPointerException e){
			u = null;
		}

		List<Contact> contacts = new ArrayList<Contact>();
		if(u!= null && u.getContacts() != null){
			for(int i = 0; i < u.getContacts().size();i++){
				Contact c = new Contact();
				c.setPseudo(u.getContacts().get(i));
				c.setLoc(u.getLocalisation());
				contacts.add(c);
			}
		}
		
		return new Gson().toJson(contacts);
	}
	
	@POST
	@Path("/recherche")
	@Produces("application/json")
	public List<String> rechercheUsers(@QueryParam("prefix") String prefix){
		System.out.println("rechercheUsers - prefix:" + prefix);
		if(prefix.equals("")){
			return null;
		}
		
		List<Utilisateur> uts = ((UtilisateurDAO)uDAO).findViaPrefix(prefix);
		
		List<String> l = new ArrayList<String>();
		for(int i = 0; i < uts.size(); i++){
			l.add(uts.get(i).getPseudo());
		}
		return l;
	}
	
	
	@DELETE
	@Path("/suppression")
	public boolean supprimerCompte(Utilisateur u){
		System.out.println("supprimer");
		return uDAO.delete(u);
	}
	
	
	@GET
	@Path("/test")
	public String testConnexion(){
		System.out.println("testConnexion");
		return new Date().toString();
	}
	
	
	
	@POST
	@Path("/uploadImg")
	@Consumes("*/*")
	public boolean uploadImg(InputStream is, String pseudo){
		System.out.println("uploadImg");
		//byte[] image = IOUtils.toByteArray(is);
		String fileLocation = "/home/sebastien/Documents/ImagesPositions/" + pseudo + ".png";
		File objFile = new File(fileLocation);
		if(objFile.exists()) {
	        objFile.delete();
	    }
		saveToFile(is, fileLocation);
		return true;
	}
	
	private void saveToFile(InputStream is, String fileLocation) {
	    try {
	        OutputStream out = null;
	        int read = 0;
	        byte[] bytes = new byte[1024];

	        out = new FileOutputStream(new File(fileLocation));
	        while ((read = is.read(bytes)) != -1) {
	            out.write(bytes, 0, read);
	        }
	        out.flush();
	        out.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	@GET
	@Path("/downloadImg")
	public Response downloadImg(@QueryParam("pseudo") String pseudo){
		System.out.println("downloadImg");
		String chemin = "/home/sebastien/Documents/ImagesPositions/";
		String nomFichier = pseudo + ".png";
		File file = new File(chemin + nomFichier);

		if(!file.exists()) {
			nomFichier = "noPhoto.png";
			file = new File(chemin + nomFichier);
	    }
		
		ResponseBuilder response = Response.ok((Object) file);
		response.header("Content-Disposition", "attachment; filename=" + nomFichier);
		
		return response.build();
	}
	
	@POST
	@Path("/shareLocation")
	public boolean shareLocation(Utilisateur u){
		System.out.println("shareLocation - " + u.getPseudo());
		return ((UtilisateurDAO)uDAO).shareLocation(u);
	}
}
