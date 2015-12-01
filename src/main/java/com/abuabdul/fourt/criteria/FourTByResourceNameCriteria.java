package com.abuabdul.fourt.criteria;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;

import com.abuabdul.fourt.domain.TaskDetail;

/**
 * Criteria to find Results based on Resource Name
 * 
 * @author abuabdul
 *
 */
public class FourTByResourceNameCriteria extends FourTAbstractCriteria<TaskDetail>implements FourTCriteria<TaskDetail> {

	protected final String byResourceName;

	public FourTByResourceNameCriteria(CriteriaQuery<TaskDetail> criteria, CriteriaBuilder criteriaBuilder,
			String resourceName) {
		super(criteria, criteriaBuilder);
		this.byResourceName = resourceName;
	}

	@Override
	public Predicate applyCriteria() {
		Predicate p = criteriaBuilder.equal(root.get("name"), byResourceName);
		return p;
	}

}
