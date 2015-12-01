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
			String taskStatus) {
		super(criteria, criteriaBuilder);
		this.byTaskStatus = taskStatus;
	}

	@Override
	public Predicate applyCriteria() {
		Root<TaskDetail> fromTaskDetail = criteriaQuery.from(TaskDetail.class);
		Predicate p = criteriaBuilder.equal(fromTaskDetail.get("status"), byTaskStatus);
		return p;
	}

}
