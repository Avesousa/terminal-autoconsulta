package com.imagosur.terminal_autoconsulta.request.BSF;

import java.math.BigInteger;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DocumentationBSF {
	
	@JsonProperty("GENERO")
	private String genero;
	@JsonProperty("DNI")
	private BigInteger documento;
	private int tipoDocumento;
	
	public String getGenero() {
		return genero;
	}
	public int getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(int tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public BigInteger getDocumento() {
		return documento;
	}
	public void setDocumento(BigInteger documento) {
		this.documento = documento;
	}
	@Override
	public String toString() {
		return "DocumentationBSF [genero=" + genero + ", documento=" + documento + ", tipoDocumento=" + tipoDocumento
				+ "]";
	}
	
}
