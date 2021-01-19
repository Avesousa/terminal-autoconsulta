package com.imagosur.terminal_autoconsulta.model.response;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Cliente {

	@JsonProperty("NOMBRE")
	private String nombreCliente;
	@JsonProperty("APELLIDO")
	private String apellidoCliente;
	private int tipoDocumento;
	@JsonProperty("NRO_DOC")
	private BigInteger numeroDocumento;
	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	@JsonProperty("FECHA_NAC")
	private String fechaNacimiento;
	private String url;
	@JsonProperty("Emails")
	private List<Email> listaCorreos;
	private String correo = "";
	public List<Email> getCorreos() {
		return listaCorreos;
	}
	public void setCorreos(List<Email> correos) {
		this.listaCorreos = correos;
		mainCorreo();
	}
	
	public void mainCorreo() {
		for(Email correo : listaCorreos) {
			if(correo.isPrincipal())
				this.correo = correo.getEmail();
		}
	}
	
	public String getCorreo() {
		this.mainCorreo();
		return this.correo;
	}
	
	public String getNombreCliente() {
		return nombreCliente;
	}
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}
	public String getApellidoCliente() {
		return apellidoCliente;
	}
	public void setApellidoCliente(String apellidoCliente) {
		this.apellidoCliente = apellidoCliente;
	}
	public BigInteger getNumeroDocumento() {
		return numeroDocumento;
	}
	public void setNumeroDocumento(BigInteger numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}
	public int getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(int tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public String toString() {
		return "Cliente [nombreCliente=" + nombreCliente + ", apellidoCliente=" + apellidoCliente + ", tipoDocumento="
				+ tipoDocumento + ", numeroDocumento=" + numeroDocumento + ", fechaNacimiento=" + fechaNacimiento
				+ ", url=" + url + ", listaCorreos=" + listaCorreos + ", correo=" + correo + "]";
	}
	
	
	
	
}
