package com.abuabdul.fourt.criteria;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.abuabdul.fourt.domain.Resource;

/**
 * @author abuabdul
 *
 * @param <T>
 */
public abstract class FourTAbstractCriteria<T> implements FourTCriteria<T> {

	protected final CriteriaQuery<T> criteriaQuery;
	protected final CriteriaBuilder criteriaBuilder;
	protected final Root<Resource> root;

	public FourTAbstractCriteria(CriteriaQuery<T> criteria, CriteriaBuilder criteriaBuilder) {
		this.criteriaQuery = criteria;
		this.criteriaBuilder = criteriaBuilder;
		this.root = criteriaQuery.from(Resource.class);
	}

}
