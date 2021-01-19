package com.imagosur.terminal_autoconsulta.model.response;

import java.math.BigInteger;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class TarjetaDisponible {

	private String tarjetaDisponible;
	private String apellidoYnombre;
	private String nombreProducto;
	private String nombreLiq;
	private String fechaHasta;
	private String estadoPlastico;
	
	public String getTarjetaDisponible() {
		return tarjetaDisponible;
	}
	public void setTarjetaDisponible(String tarjetaDisponible) {
		this.tarjetaDisponible = tarjetaDisponible;
	}
	
	public String getCard(){
		return tarjetaDisponible.substring(2,tarjetaDisponible.length());
	}
	
	public String getApellidoYnombre() {
		return apellidoYnombre;
	}
	public void setApellidoYnombre(String apellidoYnombre) {
		this.apellidoYnombre = apellidoYnombre;
	}
	public String getNombreProducto() {
		return nombreProducto;
	}
	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}
	public String getNombreLiq() {
		return nombreLiq;
	}
	public void setNombreLiq(String nombreLiq) {
		this.nombreLiq = nombreLiq;
	}
	public String getFechaHasta() {
		return fechaHasta;
	}
	public void setFechaHasta(String fechaHasta) {
		this.fechaHasta = fechaHasta;
	}
	public String getEstadoPlastico() {
		return estadoPlastico;
	}
	public void setEstadoPlastico(String estadoPlastico) {
		this.estadoPlastico = estadoPlastico;
	}
	
	@Override
	public String toString() {
		return "TarjetaDisponible [tarjetaDisponible=" + tarjetaDisponible + ", apellidoYnombre=" + apellidoYnombre
				+ ", nombreProducto=" + nombreProducto + ", nombreLiq=" + nombreLiq + ", fechaHasta=" + fechaHasta
				+ ", estadoPlastico=" + estadoPlastico + "]";
	}
	
	
}
