package com.imagosur.terminal_autoconsulta.mail;

import static org.apache.commons.lang3.StringUtils.isEmpty;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.imagosur.commons.mailsender.MailSenderException;

public class MailSenderSmtp extends MailSenderConfig implements MailSender   {
	private String message;
	private String subject;
	private String content;
	private String from;
	private String name;

	private List<SourceFile> files = new ArrayList();
	private List<String> recipients;
	
	public MailSenderSmtp(String host, String puerto, boolean tlsEnabled, String user, String pass, String socksHost, String socksPuerto, String name) {
		String[][] propieties = {
				{"mail.smtp.host",host},
				{"mail.smtp.port",puerto},
				{"mail.smtp.auth","'"+!isEmpty(user)+"'"},
				{"mail.smtp.starttls.enable","'"+tlsEnabled+"'"}};
		if(!isEmpty(socksHost)) {
			String[][] propietiesStocks = {
					{"mail.smtp.socks.host",socksHost},
					{"mail.smtp.socks.port",socksPuerto}};
			this.setPropietiesSessionSocks(propietiesStocks);
		}else {
			this.setPropietiesSessionSocks(null);
		}
		this.setPropietiesSession(propieties);
		this.setUser(user);
		this.setPassword(pass);
	}
	
	
	@Override
	public void send(Mail mail) throws MailSenderException, Exception {
			this.validate(mail);
			Session session = this.getSession();
			MimeMultipart bodyAll = new MimeMultipart();
			BodyPart bodyText = new MimeBodyPart();
			MimeMessage message = new MimeMessage(session);
			if(mail.getFiles().size()>0) {
				for(SourceFile file : mail.getFiles()) {
					BodyPart bodyFiles = new MimeBodyPart();
					bodyFiles.setDataHandler(new DataHandler(new FileDataSource(file.getPath())));
					bodyFiles.setFileName(file.getName());
					bodyAll.addBodyPart(bodyFiles);
				}
			}
            if (!isEmpty(from)) {
                InternetAddress address = new InternetAddress(from,name);
                message.setFrom(address);
            }
            for (String recipientMailAddress : mail.getRecipients()) {
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipientMailAddress,name));
            }
            message.setSubject(mail.getSubject());
            if (!isEmpty(mail.getContentType())) {
                bodyText.setContent(mail.getText(), mail.getContentType());
            } else {
                bodyText.setText(mail.getText());
            }
            bodyAll.addBodyPart(bodyText);
            message.setContent(bodyAll);
            Transport t = session.getTransport("smtp");
            if(!isEmpty(this.getUser())) {
            	session.setDebug(false);
            	t.connect(this.getUser(), this.getPassword());
            }
            t.sendMessage(message, message.getAllRecipients());
            t.close();
	}


	@Override
	public String getMessage() {
		return this.message;
	}


	@Override
	public String getSubject() {
		return this.subject;
	}


	@Override
	public List<String> getRecipients() {
		return this.recipients;
	}


	@Override
	public void setRecipients(List<String> recipients) {
		this.recipients = recipients;
		
	}


	@Override
	public void setRecipient(String recipient) {
		if(this.recipients == null)
			this.recipients = new ArrayList();
		this.recipients.add(recipient);
	}


	@Override
	public void setMessage(String message) {
		this.message = message;
		
	}


	@Override
	public void setSubject(String subject) {
		this.subject = subject;
	}


	@Override
	public String getContent() {
		return this.content;
	}


	@Override
	public void setContent(String content) {
		this.content = content;
	}


	@Override
	public void setFrom(String from) {
		this.from = from;
		
	}


	@Override
	public void setName(String name) {
		this.name = name;
		
	}
	
	
	
	

}
