package com.example.mercadolivre.validator;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ExistsIdValidator implements ConstraintValidator<ExistsId, Integer> {

	private String domainAttribute;
	private Class<?> klass;
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public void initialize(ExistsId params) {
		domainAttribute = params.fieldName();
		klass = params.domainClass();
	}

	@Override
	public boolean isValid(Integer value, ConstraintValidatorContext context) {
		Query query = manager.createQuery("Select 1 from "+klass.getName()+" where "+domainAttribute+"=:value");
		query.setParameter("value", value);
		List<?> list = query.getResultList();
		
		return !list.isEmpty();
	}

	
}