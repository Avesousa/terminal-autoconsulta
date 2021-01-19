package com.imagosur.terminal_autoconsulta.response;

import java.util.List;

public class JwtResponse {

	private String token;
	private String type = "Bearer";
	private String username;
	private Boolean inactivo;

	public JwtResponse(String accessToken, String username, Boolean inactivo, List<String> roles) {
		this.token = accessToken;
		this.username = username;
		this.inactivo = inactivo;
	}

	public String getAccessToken() {
		return token;
	}

	public void setAccessToken(String accessToken) {
		this.token = accessToken;
	}

	public String getTokenType() {
		return type;
	}

	public void setTokenType(String tokenType) {
		this.type = tokenType;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Boolean getInactivo() {
		return inactivo;
	}

	public void setInactivo(Boolean inactivo) {
		this.inactivo = inactivo;
	}
}
