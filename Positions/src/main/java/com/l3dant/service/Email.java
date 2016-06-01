package com.l3dant.service;

public class Email {
	private String host="smtp.mail.yahoo.com";
	private String port="587";
	private String user="upmccfa@yahoo.fr";
	private String pass="DANT2016";
	
	public void init() {
		// reads SMTP server setting from web.xml file
		/*ServletContext context = getServletContext();
		host = context.getInitParameter("host");
		port = context.getInitParameter("port");
		user = context.getInitParameter("user");
		pass = context.getInitParameter("pass");*/
	}
	
	
	
	
}
