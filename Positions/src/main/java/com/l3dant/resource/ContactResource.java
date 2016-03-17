package com.l3dant.resource;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.l3dant.bean.*;

@Path("/contact")
public class ContactResource {

	@GET
	@Produces("application/json")
    public List<Contact> getContacts() throws SQLException {
		System.out.println("getContacts");
		List<Contact> obj2 = new ArrayList<Contact>();
		 
		Contact seb = new Contact("Duchenne","Sebastien");
		Contact davy = new Contact("Ly","Davy");
		
		obj2.add(seb);
		obj2.add(davy);
		
        return obj2;
    }
	
}
