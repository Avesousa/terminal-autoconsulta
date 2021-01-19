package com.imagosur.terminal_autoconsulta.mail;

import java.util.ArrayList;
import java.util.List;

public class Mail{
	
	private String subject;
    private List<String> recipients;
    private String text;
    private String contentType;
    private String type;
    private List<SourceFile> files = new ArrayList();

	public List<SourceFile> getFiles() {
		return files;
	}
	public void setFiles(SourceFile file) {
		this.files.add(file);
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public List<String> getRecipients() {
		return recipients;
	}
	public void setRecipients(List<String> recipients) {
		this.recipients = recipients;
	}
	public void setRecipients(String recipients) {
		if(this.recipients == null) {
			this.recipients = new ArrayList();
		}
		this.recipients.add(recipients);
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}    
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void updateText(String key, String newText) {
		this.text = this.text.replace(key, newText);
	}
    
    

}
