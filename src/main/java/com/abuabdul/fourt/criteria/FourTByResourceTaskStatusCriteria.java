package com.abuabdul.fourt.criteria;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.abuabdul.fourt.domain.TaskDetail;

/**
 * Criteria to find Results based on Task Status
 * 
 * @author abuabdul
 *
 */
public class FourTByResourceTaskStatusCriteria extends FourTAbstractCriteria<TaskDetail>
		implements FourTCriteria<TaskDetail> {

	protected final String byTaskStatus;

	public FourTByResourceTaskStatusCriteria(CriteriaQuery<TaskDetail> criteria, CriteriaBuilder criteriaBuilder,
			Root<TaskDetail> root, String taskStatus) {
		super(criteria, criteriaBuilder, root);
		this.byTaskStatus = taskStatus;
	}

	@Override
	public Predicate applyCriteria() {
		Predicate p = criteriaBuilder.equal(root.get("status"), byTaskStatus);
		return p;
	}

}
