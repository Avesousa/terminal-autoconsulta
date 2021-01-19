package com.imagosur.terminal_autoconsulta.request.front;

public class ESummaryFront {

	private String email;
	private String token;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	@Override
	public String toString() {
		return "ESummaryFront [email=" + email + ", token=" + token + "]";
	}
	
	
	
}
