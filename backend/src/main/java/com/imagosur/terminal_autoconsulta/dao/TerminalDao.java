package com.imagosur.terminal_autoconsulta.dao;

import org.springframework.stereotype.Repository;

import com.imagosur.commons.dao.AbstractEntityManagerDao;
import com.imagosur.terminal_autoconsulta.entity.EquipoEntity;

@Repository
public class TerminalDao extends AbstractEntityManagerDao<EquipoEntity, String>{

	public TerminalDao() {
		super(EquipoEntity.class);
	}
}
