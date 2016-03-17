package com.l3dant.bean;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import com.l3dant.resource.ContactResource;

public class App extends Application {

	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> clazz = new HashSet<>();
		clazz.add(ContactResource.class);
		return clazz;
	}
}
