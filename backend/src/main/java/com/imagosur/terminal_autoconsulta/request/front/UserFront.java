package com.imagosur.terminal_autoconsulta.request.front;

import java.math.BigInteger;

public class UserFront {

	private String usuario;
	private String password;
	private String option;
	private String window = "login";
	private int tipoDocumento;
	private BigInteger documento;
	private String genero;
	private String eresumen;
	
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getOption() {
		return option;
	}
	public void setOption(String option) {
		this.option = option;
	}
	public String getWindow() {
		return window;
	}
	public void setWindow(String window) {
		this.window = window;
	}
	public int getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(int tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public BigInteger getDocumento() {
		return documento;
	}
	public void setDocumento(BigInteger documento) {
		this.documento = documento;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String gender) {
		this.genero = gender;
	}
	public String getEresumen() {
		return eresumen;
	}
	public void setEresumen(String eresumen) {
		this.eresumen = eresumen;
	}
	public boolean isAdhesion() {
		return this.eresumen.equals("si");
	}
	
	
	
}
