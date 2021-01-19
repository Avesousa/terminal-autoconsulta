package com.imagosur.terminal_autoconsulta.service;

import java.util.Collection;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.imagosur.terminal_autoconsulta.entity.EquipoEntity;


public class UserDetailsImpl implements UserDetails {

	private static final long serialVersionUID = 7070811958155934933L;

	private Boolean inactivo;
	private String username;

	@JsonIgnore
	private String password;

	public UserDetailsImpl(String username, String password, Boolean activo) {
		this.username = username;		
		this.password = password;
		this.inactivo = !activo;
	}

	public static UserDetailsImpl build(EquipoEntity user) {

		return new UserDetailsImpl(
				user.getDescripcion(), 
				user.getClave(),
				user.getActivo());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserDetailsImpl user = (UserDetailsImpl) o;
		return Objects.equals(username, user.username);
	}

	public Boolean getInactivo() {
		return inactivo;
	}

	public void setInactivo(Boolean inactivo) {
		this.inactivo = inactivo;
	}
}
