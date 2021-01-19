package com.imagosur.terminal_autoconsulta.request.front;
import java.math.BigInteger;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
public class SummaryFront {
	@JsonFormat(shape= JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private Date nacimiento;
	private String tarjeta;
	private String resumen;
	private int tipoDocumento;
	private BigInteger documento;
	
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
}
