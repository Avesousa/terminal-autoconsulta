package com.imagosur.terminal_autoconsulta.request;

import javax.validation.constraints.Size;

public class SignupRequest {
	
	@Size(min = 3, max = 100)
	private String terminal;

	@Size(min = 6, max = 40)
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

}
