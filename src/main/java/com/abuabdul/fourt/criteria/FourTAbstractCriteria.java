package com.abuabdul.fourt.criteria;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.abuabdul.fourt.domain.TaskDetail;

/**
 * @author abuabdul
 *
 * @param <T>
 */
public abstract class FourTAbstractCriteria<T> implements FourTCriteria<T> {

	protected final CriteriaQuery<T> criteriaQuery;
	protected final CriteriaBuilder criteriaBuilder;
	protected final Root<TaskDetail> root;

	public FourTAbstractCriteria(CriteriaQuery<T> criteria, CriteriaBuilder criteriaBuilder, Root<TaskDetail> root) {
		this.criteriaQuery = criteria;
		this.criteriaBuilder = criteriaBuilder;
		this.root = root;
	}

}
