package com.imagosur.terminal_autoconsulta.request.BSF;

public class ESummaryBSF {

	private String email;
	private ClientBSF documentoCliente;
	
	public ESummaryBSF(String email, ClientBSF documentoCliente) {
		super();
		this.email = email;
		this.documentoCliente = documentoCliente;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public ClientBSF getDocumentoCliente() {
		return documentoCliente;
	}
	public void setDocumentoCliente(ClientBSF documentoCliente) {
		this.documentoCliente = documentoCliente;
	}
}
