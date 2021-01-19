package com.imagosur.terminal_autoconsulta.model.response;

import java.math.BigInteger;

public class Usuario {

	private String usuario;
	private String message;
	//Token propuesto para enviar al front
	private String token;
	

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String nombre) {
		this.usuario = nombre;
	}

	public String getmessage() {
		return message;
	}

	public void setmessage(String message) {
		this.message = message;
	}

	
}
