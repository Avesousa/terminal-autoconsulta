package com.imagosur.terminal_autoconsulta.model.response;

public class Link {

	private String url;
	private Boolean connect = false;
	
	public Link(String option) {
		if(option.equals("correo"))
			this.connect = true;
		this.url = "";
	}
	
	public Link() {
		this.url = "";
	}

	public Boolean getConnect() {
		return connect || !this.url.isEmpty();
	}

	public void setConnect(Boolean connect) {
		this.connect = connect;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	
	
}
