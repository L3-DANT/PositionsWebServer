package com.l3dant.bean;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import com.l3dant.service.ContactService;
import com.l3dant.service.InvitationService;
import com.l3dant.service.LocalisationService;
import com.l3dant.service.UtilisateurService;

public class App extends Application {

	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> clazz = new HashSet<>();
		clazz.add(UtilisateurService.class);
		clazz.add(ContactService.class);
		clazz.add(LocalisationService.class);
		clazz.add(InvitationService.class);
		return clazz;
	}
}
