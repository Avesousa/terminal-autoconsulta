package com.imagosur.terminal_autoconsulta.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Email {

	@JsonProperty("EMAIL")
	private String email;
	@JsonProperty("TIPO_EMAIL")
    private String tipo_email;
	@JsonProperty("PRINCIPAL")
    private boolean principal;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTipo_email() {
		return tipo_email;
	}
	public void setTipo_email(String tipo_email) {
		this.tipo_email = tipo_email;
	}
	public boolean isPrincipal() {
		return principal;
	}
	public void setPrincipal(boolean principal) {
		this.principal = principal;
	}
    
    
	
}
