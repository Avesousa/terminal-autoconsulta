package com.imagosur.terminal_autoconsulta.request.BSF;

import java.math.BigInteger;

public class UserBSF {

	private int idTipoDocumento;
	private BigInteger documento;
	private String usuario;
	private String password;
	private String option;
	private String ventana = "login";
	private boolean isAdhesion;
	
	

	public boolean isAdhesion() {
		return isAdhesion;
	}

	public void setAdhesion(boolean isAdhesion) {
		this.isAdhesion = isAdhesion;
	}

	public String getVentana() {
		return ventana;
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

	public int getIdTipoDocumento() {
		return idTipoDocumento;
	}
	
	public void setIdTipoDocumento(int idTipoDocumento) {
		this.idTipoDocumento = idTipoDocumento;
	}
	
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public BigInteger getDocumento() {
		return documento;
	}

	public void setDocumento(BigInteger documento) {
		this.documento = documento;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String nombre) {
		this.usuario = nombre;
	}

	@Override
	public String toString() {
		return "UserBSF [idTipoDocumento=" + idTipoDocumento + ", documento=" + documento + ", usuario=" + usuario
				+ ", password=" + password + ", option=" + option + ", ventana=" + ventana + "]";
	}
	
	
}
