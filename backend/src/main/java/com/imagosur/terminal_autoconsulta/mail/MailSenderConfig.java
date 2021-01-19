package com.imagosur.terminal_autoconsulta.mail;

import static org.apache.commons.lang3.StringUtils.isEmpty;

import java.util.List;
import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.imagosur.commons.mailsender.MailSenderException;

public class MailSenderConfig {

	private String[][] propietiesSession;
	private String[][] propietiesSessionSocks;
    private String user;
    private String password;
    
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    public Properties getSessionProperties() {
    	Properties prop = new Properties();
    	for(String[] key : propietiesSession) {
    		prop.put(key[0],key[1]);
    	}
    	
    	if(propietiesSessionSocks != null) {
    		for(String[] key: propietiesSessionSocks) {
    			prop.put(key[0], key[1]);
    		}
    	}
    	return prop;
    } 
    
    public Session getSession() {
        if(!isEmpty(user) && !isEmpty(password)) {
        	return Session.getInstance(getSessionProperties(), new MailSenderAuthentication(user, password));
        }else {
        	return Session.getInstance(getSessionProperties());
        }
    } 
    
    public void validate(Mail mail) {
    	
    	if (isEmpty(mail.getSubject())) {
            throw new MailSenderException(
                    "el campo subject del mail es inválido con valor: '" + mail.getSubject() + "'");
        }
        if (isEmpty(mail.getText())) {
            throw new MailSenderException("el campo text del mail es inválido con valor: '" + mail.getText() + "'");
        }
        if (mail.getRecipients() == null || mail.getRecipients().size() == 0) {
            throw new MailSenderException("el campo recipients del mail está vacío");
        }
    }
    
	public String[][] getPropietiesSessionSocks() {
		return propietiesSessionSocks;
	}
	public void setPropietiesSessionSocks(String[][] propietiesSessionSocks) {
		this.propietiesSessionSocks = propietiesSessionSocks;
	}
	public String[][] getPropietiesSession() {
		return propietiesSession;
	}
	public void setPropietiesSession(String[][] propietiesSession) {
		this.propietiesSession = propietiesSession;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

    
    
	
}
