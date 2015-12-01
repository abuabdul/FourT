package com.abuabdul.fourt.criteria;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.abuabdul.fourt.domain.TaskDetail;

/**
 * Criteria to find Results based on Task Duration
 * 
 * @author abuabdul
 *
 */
public class FourTByResourceTaskDurationCriteria extends FourTAbstractCriteria<TaskDetail>
		implements FourTCriteria<TaskDetail> {

	private final Float byTaskDuration;

	public FourTByResourceTaskDurationCriteria(CriteriaQuery<TaskDetail> criteria, CriteriaBuilder criteriaBuilder,
			Root<TaskDetail> root, Float taskDuration) {
		super(criteria, criteriaBuilder, root);
		this.byTaskDuration = taskDuration;
	}

	@Override
	public Predicate applyCriteria() {
		Predicate p = criteriaBuilder.equal(root.get("duration"), byTaskDuration);
		return p;
	}

}
