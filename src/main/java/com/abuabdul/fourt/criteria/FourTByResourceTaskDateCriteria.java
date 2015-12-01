package com.abuabdul.fourt.criteria;

import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;

import com.abuabdul.fourt.domain.TaskDetail;

/**
 * Criteria to find Results based on Task Date
 * 
 * @author abuabdul
 *
 */
public class FourTByResourceTaskDateCriteria extends FourTAbstractCriteria<TaskDetail>implements FourTCriteria<TaskDetail> {

	protected final Date byTaskDate;

	public FourTByResourceTaskDateCriteria(CriteriaQuery<TaskDetail> criteria, CriteriaBuilder criteriaBuilder,
			Date taskDate) {
		super(criteria, criteriaBuilder);
		this.byTaskDate = taskDate;
	}

	@Override
	public Predicate applyCriteria() {
		Predicate p = criteriaBuilder.equal(root.get("taskDate"), byTaskDate);
		return p;
	}

}
