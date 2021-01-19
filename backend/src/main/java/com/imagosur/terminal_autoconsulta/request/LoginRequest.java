package com.imagosur.terminal_autoconsulta.request;

public class LoginRequest {

	private String terminal;

	private String password;

	public String getTerminal() {
		return terminal;
	}

	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "LoginRequest [terminal=" + terminal + ", password=" + password + "]";
	}
	
	
}
