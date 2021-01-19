package com.imagosur.terminal_autoconsulta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.imagosur.commons.dao.Dao;
import com.imagosur.terminal_autoconsulta.entity.EquipoEntity;

@Component
public class UserServiceImpl implements UserService {

	@Autowired
	private Dao<EquipoEntity, String> userRepository;
	
	@Override
	@Transactional
	public void save(EquipoEntity user) {
		userRepository.persist(user);
	}

	@Override
	@Transactional
	public void update(EquipoEntity user) {
		userRepository.update(user);
	}

}
