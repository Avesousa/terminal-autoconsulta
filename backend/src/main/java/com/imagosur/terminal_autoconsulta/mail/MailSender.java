package com.imagosur.terminal_autoconsulta.mail;

import java.util.List;

public interface MailSender{
	void send(Mail mail) throws MailSenderException,Exception;
	String getMessage();
	String getSubject();
	String getContent();
	List<String> getRecipients();
	void setRecipients(List<String> recipients);
	void setRecipient(String recipient);
	void setMessage(String message);
	void setSubject(String subject);
	void setContent(String content);
	void setFrom(String from);
	void setName(String name);
	
}
