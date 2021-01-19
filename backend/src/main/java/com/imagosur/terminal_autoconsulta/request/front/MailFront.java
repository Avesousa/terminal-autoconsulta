package com.imagosur.terminal_autoconsulta.request.front;

import java.math.BigInteger;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MailFront{

	private String token;
	private String genero;
	private BigInteger documento;
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getgenero() {
		return genero;
	}
	public void setgenero(String genero) {
		this.genero = genero;
	}
	public BigInteger getdocumento() {
		return documento;
	}
	public void setdocumento(BigInteger documento) {
		this.documento = documento;
	}
	
	@Override
	public String toString() {
		return "MailFront [token=" + token + ", genero=" + genero + ", documento=" + documento + "]";
	}

	
}
