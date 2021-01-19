package com.imagosur.terminal_autoconsulta.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class MailSenderAuthentication extends Authenticator {

	private String user;
	private String password;
	
	public MailSenderAuthentication(String user, String password) {
		super();
		this.user = user;
		this.password = password;
	}
	
	public PasswordAuthentication getAuthentication() {
		return new PasswordAuthentication(user, password);
	}
	
	
}
