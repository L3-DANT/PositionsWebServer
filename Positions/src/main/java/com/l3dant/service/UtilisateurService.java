package com.l3dant.service;



import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.RandomStringUtils;

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
	public boolean inscription(Utilisateur u){
		System.out.println("inscription");
		
		Utilisateur ut = uDAO.find(u.getPseudo());
		if(ut == null){
			u.setToken(RandomStringUtils.random(32, true, true));
			uDAO.create(u);
			return true;
		}
		return false;
	}
	
	@POST
	@Path("/connexion")
	public Utilisateur connexion(Utilisateur u){
		System.out.println("connexion");
		Utilisateur ut = uDAO.find(u.getPseudo());
		return ut; //ut peut être null
	}
	
	@POST
	@Path("/recherche")
	@Consumes("text/plain")
	@Produces("application/json")
	public List<Utilisateur> rechercheUsers(String prefix){
		return ((UtilisateurDAO)uDAO).findViaPrefix(prefix);
	}
	
	
	@DELETE
	@Path("/suppression")
	@Consumes("application/json")
	@Produces("application/json")
	public boolean supprimerCompte(Utilisateur u){
		System.out.println("supprimer");
		return uDAO.delete(u);
	}
	
	
	@POST
	@Path("/testLoc")
	public boolean test(Localisation u){
		System.out.println("testLoc");
		System.out.println("lat : " + u.getLatitude() + ", long : "+ u.getLongitude());
		
		return true;
	}
	
	@GET
	@Path("/test")
	public String test(){
		return "test ok";
	}
	
	@POST
	@Path("/uploadImg")
	@Consumes("*/*")
	public void uploadImg(InputStream is){
		System.out.println("uploadImg");
		//byte[] image = IOUtils.toByteArray(is);
		String fileLocation = "d://uploadedImage/test.png";
		File objFile = new File(fileLocation);
		if(objFile.exists()) {
	        objFile.delete();
	    }
		saveToFile(is, fileLocation);
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
	public Response downloadImg(){
		File file = new File("d:\\uc.png");

		ResponseBuilder response = Response.ok((Object) file);
		response.header("Content-Disposition",
			"attachment; filename=image_from_server.png");
		return response.build();
	}
}
