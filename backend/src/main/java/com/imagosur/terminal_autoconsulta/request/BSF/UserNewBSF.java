package com.imagosur.terminal_autoconsulta.request.BSF;

public class UserNewBSF {
	
	private String idTipoDocumento;
	private String documento;
    private String correoElectronico;
    private String fechaNacimiento;
    
	public String getIdTipoDocumento() {
		return idTipoDocumento;
	}
	public void setIdTipoDocumento(String idTipoDocumento) {
		this.idTipoDocumento = idTipoDocumento;
	}
	public String getDocumento() {
		return documento;
	}
	public void setDocumento(String documento) {
		this.documento = documento;
	}
	public String getCorreoElectronico() {
		return correoElectronico;
	}
	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	@Override
	public String toString() {
		return "UserNewBSF [idTipoDocumento=" + idTipoDocumento + ", documento=" + documento + ", correoElectronico="
				+ correoElectronico + ", fechaNacimiento=" + fechaNacimiento + "]";
	}
    
    

}
