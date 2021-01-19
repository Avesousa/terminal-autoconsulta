package com.imagosur.terminal_autoconsulta.model.response;

public class Summary {
	private String resumen;
	private String tarjeta;
	private String fecha;

	public String getResumen() {
		return resumen;
	}

	public void setResumen(String resumen) {
		this.resumen = resumen;
		separar();
	}
	

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
	
	public void separar() {
		tarjeta = resumen.split(";")[0];
		fecha = resumen.split(";")[1];
	}

	@Override
	public String toString() {
		return "Resumen [resumen=" + resumen + ", tarjeta=" + tarjeta + ", fecha=" + fecha + "]";
	}
	
	
	
}
