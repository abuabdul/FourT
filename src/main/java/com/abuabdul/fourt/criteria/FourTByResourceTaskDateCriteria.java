package com.abuabdul.fourt.criteria;

import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.abuabdul.fourt.domain.Resource;
import com.abuabdul.fourt.domain.TaskDetail;

/**
 * Criteria to find Results based on Task Date
 * 
 * @author abuabdul
 *
 */
public class FourTByResourceTaskDateCriteria extends FourTAbstractCriteria<TaskDetail>
		implements FourTCriteria<TaskDetail> {

	protected final Date byTaskDate;

	public FourTByResourceTaskDateCriteria(CriteriaQuery<TaskDetail> criteria, CriteriaBuilder criteriaBuilder,
			Root<TaskDetail> root, Date taskDate) {
		super(criteria, criteriaBuilder, root);
		this.byTaskDate = taskDate;
	}

	@Override
	public Predicate applyCriteria() {
		Join<TaskDetail, Resource> owner = root.join("resource");
		Predicate p = criteriaBuilder.equal(owner.get("taskDate"), byTaskDate);
		return p;
	}

}
