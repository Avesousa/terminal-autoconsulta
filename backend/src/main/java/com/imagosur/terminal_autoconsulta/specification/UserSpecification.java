package com.imagosur.terminal_autoconsulta.specification;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import com.imagosur.commons.dao.AbstractSpecification;
import com.imagosur.commons.dao.FieldSort;
import com.imagosur.terminal_autoconsulta.entity.EquipoEntity;
import com.imagosur.terminal_autoconsulta.entity.EquipoEntity_;

public class UserSpecification extends AbstractSpecification<EquipoEntity> {

	private String username;
	
	@Override
	protected void populatePredicate(List<Predicate> predicates, Root<EquipoEntity> root, CriteriaBuilder cb) {
		if (username != null) {
			predicates.add(cb.equal(root.get(EquipoEntity_.descripcion), username));
		}
	}

	@Override
	protected Order getOrderForFieldSort(FieldSort fieldSort, Root<EquipoEntity> root, CriteriaBuilder cb) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
