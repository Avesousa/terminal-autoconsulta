package com.imagosur.terminal_autoconsulta.request.BSF;

import java.math.BigInteger;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class SummaryBSF {

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private Date nacimiento;
	private String tarjeta;
	private String resumen;
	private BigInteger numerodoc;
	private int tipodoc;
	
	public SummaryBSF() {}
	
	public SummaryBSF(Date nacimiento, String tarjeta, String resumen) {
		this.nacimiento = nacimiento;
		this.tarjeta = tarjeta;
		this.resumen = resumen;
	}
	
	//getter and setter
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
	public String getResumen() {
		return resumen;
	}
	public void setResumen(String resumen) {
		this.resumen = resumen;
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

	@Override
	public String toString() {
		return "SummaryBSF [nacimiento=" + nacimiento + ", tarjeta=" + tarjeta + ", resumen="
				+ resumen + ", numerodoc=" + numerodoc + ", tipodoc=" + tipodoc + "]";
	}

	
	
}
