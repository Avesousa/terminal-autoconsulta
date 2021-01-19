package com.imagosur.terminal_autoconsulta.model;

public class InfoSummaryFront {
	private String tarjeta;
	private String fecha;
	
	//Getter and setter
	public String getTarjeta() {
		return tarjeta;
	}
	public void setTarjeta(String tarjeta) {
		this.tarjeta = tarjeta;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	@Override
	public String toString() {
		return "InfoSummaryFront [tarjeta=" + tarjeta + ", fecha=" + fecha + "]";
	}
	
	
}
