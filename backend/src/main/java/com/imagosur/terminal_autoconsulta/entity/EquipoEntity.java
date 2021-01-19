package com.imagosur.terminal_autoconsulta.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.imagosur.commons.entity.Entidad;

@Entity
@Table(name = "equipo")
public class EquipoEntity implements Entidad<String> {
	@Id
	@Column(name = "descripcion")
	private String descripcion;

	@Column(name = "clave")
	private String clave;

	@Column(name = "activo")
	private Boolean activo;

	@Override
	public String getId() {
		return descripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

}
