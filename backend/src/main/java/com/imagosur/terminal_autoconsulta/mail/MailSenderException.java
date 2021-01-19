package com.imagosur.terminal_autoconsulta.mail;

public class MailSenderException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 354929332428703515L;
    private int numberError;
    

    public MailSenderException() {
        super();
    }

    public MailSenderException(String message, int error) {
        super(message);
    }

	public int getNumberError() {
		return numberError;
	}

	public void setNumberError(int numberError) {
		this.numberError = numberError;
	}
    
    
}
