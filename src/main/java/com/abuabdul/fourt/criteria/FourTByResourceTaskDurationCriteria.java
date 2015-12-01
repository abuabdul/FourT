package com.abuabdul.fourt.criteria;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;

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
			Float taskDuration) {
		super(criteria, criteriaBuilder);
		this.byTaskDuration = taskDuration;
	}

	@Override
	public Predicate applyCriteria() {
		Path<Object> path = root.join("taskDetailList").get("duration");
		Predicate p = criteriaBuilder.equal(path, byTaskDuration);
		return p;
	}

}
