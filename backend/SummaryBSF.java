package com.imagosur.terminal_autoconsulta.request.BSF;

import java.math.BigInteger;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class SummaryBSF {

	private String token;
	@JsonFormat(shape= JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date fechaNacimiento;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private Date nacimiento;
	private String tarjeta;
	private String resumen;
	private BigInteger numerodoc;
	private int tipodoc;
	
	
	public void parsearFecha() {
		this.nacimiento = new Date(this.fechaNacimiento.getTime());
	}
	
	public BigInteger getNumerodoc() {
		return numerodoc;
	}
	public void setNumerodoc(BigInteger numerodoc) {
		this.numerodoc = numerodoc;
	}
	public int getTipodoc() {
		return tipodoc;
	}
	public void setTipodoc(int tipodoc) {
		this.tipodoc = tipodoc;
	}
	public String getResumen() {
		return resumen;
	}
	public void setResumen(String resumen) {
		this.resumen = resumen;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Date getNacimiento() {
		return nacimiento;
	}
	public void setNacimiento(Date nacimiento) {
		this.nacimiento = nacimiento;
	}
	public String getTarjeta() {
		return tarjeta;
	}
	public void setTarjeta(String tarjeta) {
		this.tarjeta = tarjeta;
	}
	@Override
	public String toString() {
		return "ResumenRequest [token=" + token + ", nacimiento=" + nacimiento + ", tarjeta=" + tarjeta + ", resumen="
				+ resumen + ", numerodoc=" + numerodoc + ", tipodoc=" + tipodoc + "]";
	}
	
}
