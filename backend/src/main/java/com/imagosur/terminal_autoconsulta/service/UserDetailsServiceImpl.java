package com.imagosur.terminal_autoconsulta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.core.userdetails.UserDetails;
import com.imagosur.commons.dao.Dao;
import com.imagosur.terminal_autoconsulta.entity.EquipoEntity;
import com.imagosur.terminal_autoconsulta.specification.UserSpecification;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private Dao<EquipoEntity, String> dao;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		EquipoEntity user = null;
		UserSpecification spec = new UserSpecification();
		spec.setUsername(username);
		try {
			user = dao.getOne(spec);
		}
		catch (Exception e) {
			throw new UsernameNotFoundException("User Not Found with username: " + username);
		}

		return UserDetailsImpl.build(user);
	}
}
