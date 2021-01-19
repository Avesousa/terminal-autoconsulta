package com.imagosur.terminal_autoconsulta.model;

import java.math.BigInteger;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ClienteFront {

	private String nombre;
	private String apellido;
	private int tipo;
	private BigInteger dni;
	private String usuario;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private Date nacimiento;
	private String url;
	private String correo;
	
	//Getter and Setter
	public void parsearFecha(String fecha) {
		String[] fechaDiv = (fecha.split("/"));
		this.nacimiento = new Date(
				Integer.parseInt(fechaDiv[2])-1900,
				Integer.parseInt(fechaDiv[1])-1,
				Integer.parseInt(fechaDiv[0]));
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public BigInteger getDni() {
		return dni;
	}

	public void setDni(BigInteger dni) {
		this.dni = dni;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Date getNacimiento() {
		return nacimiento;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	@Override
	public String toString() {
		return "ClienteFront [nombre=" + nombre + ", apellido=" + apellido + ", tipo=" + tipo + ", dni=" + dni
				+ ", usuario=" + usuario + ", nacimiento=" + nacimiento + ", url=" + url + ", correo=" + correo + "]";
	}

	
}
