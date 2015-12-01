package com.abuabdul.fourt.criteria.builder;

import static com.abuabdul.fourt.util.FourTUtils.simpleDateWithDDMMYYYY;
import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.springframework.util.CollectionUtils.isEmpty;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.abuabdul.fourt.criteria.FourTByResourceNameCriteria;
import com.abuabdul.fourt.criteria.FourTByResourceTaskDateCriteria;
import com.abuabdul.fourt.criteria.FourTByResourceTaskDurationCriteria;
import com.abuabdul.fourt.criteria.FourTByResourceTaskStatusCriteria;
import com.abuabdul.fourt.criteria.FourTCriteria;
import com.abuabdul.fourt.criteria.fallback.FourTDefaultCriteria;
import com.abuabdul.fourt.criteria.predicate.FourTPredicateService;
import com.abuabdul.fourt.domain.Resource;
import com.abuabdul.fourt.domain.TaskDetail;
import com.abuabdul.fourt.exception.FourTServiceException;
import com.abuabdul.fourt.model.ResourceTaskDetail;
import com.abuabdul.fourt.service.FourTService;
import com.google.common.collect.Lists;

/**
 * @author abuabdul
 *
 */
public class FourTResourceCriteriaBuilder {

	private final ResourceTaskDetail emptyResourceTaskDtl = new ResourceTaskDetail();
	private final List<FourTCriteria<TaskDetail>> criteriaList = Lists.newArrayList();
	private final FourTService fourTService;
	private final EntityManager entityManager;
	private final CriteriaBuilder criteriaBuilder;
	private final CriteriaQuery<TaskDetail> criteriaQuery;

	private ResourceTaskDetail resourceTaskDetail;
	private FourTDefaultCriteria fourTDefaultCriteria;
	private FourTPredicateService<TaskDetail> predicateService;

	public FourTResourceCriteriaBuilder(FourTService fourTService) {
		this.fourTService = fourTService;
		this.entityManager = fourTService.getEntityManager();
		this.criteriaBuilder = entityManager.getCriteriaBuilder();
		this.criteriaQuery = criteriaBuilder.createQuery(TaskDetail.class);
	}

	public FourTResourceCriteriaBuilder withInputResourceTaskDetail(ResourceTaskDetail resourceTaskDetail) {
		this.resourceTaskDetail = resourceTaskDetail;
		return this;
	}

	public FourTResourceCriteriaBuilder withDefaultCriteria(FourTDefaultCriteria fourTDefaultCriteria) {
		this.fourTDefaultCriteria = fourTDefaultCriteria;
		return this;
	}

	public FourTResourceCriteriaBuilder withPredicateService(FourTPredicateService<TaskDetail> predicateService) {
		this.predicateService = predicateService;
		return this;
	}

	public FourTResourceCriteriaBuilder addResourceNameCriteria() {
		if (isCriteriaApplicable(getDefault().getResourceName())) {
			criteriaList.add(newFourTResourceNameCriteria());
		}
		return this;
	}

	public FourTResourceCriteriaBuilder addTaskDateCriteria() {
		if (isCriteriaApplicable(getDefault().getTaskDate())) {
			criteriaList.add(newFourTResourceTaskDateCriteria());
		}
		return this;
	}

	public FourTResourceCriteriaBuilder addTaskDurationCriteria() {
		if (isCriteriaApplicable(getDefault().getDuration())) {
			criteriaList.add(newFourTResourceTaskDurationCriteria());
		}
		return this;
	}

	public FourTResourceCriteriaBuilder addTaskStatusCriteria() {
		if (isCriteriaApplicable(getDefault().getStatus())) {
			criteriaList.add(newFourTResourceTaskStatusCriteria());
		}
		return this;
	}

	private boolean isCriteriaApplicable(String criteriaValue) {
		return !isBlank(criteriaValue);
	}

	private ResourceTaskDetail getDefault() {
		return defaultIfNull(resourceTaskDetail, emptyResourceTaskDtl);
	}

	protected FourTCriteria<TaskDetail> newFourTResourceNameCriteria() {
		return new FourTByResourceNameCriteria(criteriaQuery, criteriaBuilder, resourceTaskDetail.getResourceName());
	}

	protected FourTCriteria<TaskDetail> newFourTResourceTaskDateCriteria() {
		return new FourTByResourceTaskDateCriteria(criteriaQuery, criteriaBuilder,
				simpleDateWithDDMMYYYY(resourceTaskDetail.getTaskDate().trim()));
	}

	protected FourTCriteria<TaskDetail> newFourTResourceTaskDurationCriteria() {
		return new FourTByResourceTaskDurationCriteria(criteriaQuery, criteriaBuilder,
				new Float(resourceTaskDetail.getDuration().trim()));
	}

	protected FourTCriteria<TaskDetail> newFourTResourceTaskStatusCriteria() {
		return new FourTByResourceTaskStatusCriteria(criteriaQuery, criteriaBuilder, resourceTaskDetail.getStatus());
	}

	public List<TaskDetail> executeCriteria() throws FourTServiceException {
		if (isEmpty(criteriaList)) {
			// return fourTDefaultCriteria.defaultSelectAllCriteria();
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			// CriteriaQuery<Resource> criteriaQuery =
			// criteriaBuilder.createQuery(Resource.class);
			CriteriaQuery<TaskDetail> criteriaQuery = criteriaBuilder.createQuery(TaskDetail.class);
			Root<TaskDetail> root = criteriaQuery.from(TaskDetail.class);
			Join<TaskDetail, Resource> owner = root.join("resource");
			Predicate p = criteriaBuilder.equal(root.get("duration"), new Float(2));
			//Predicate p3 = criteriaBuilder.equal(root.get("status"), "Completed");
			//Predicate p2 = criteriaBuilder.equal(owner.get("taskDate"), simpleDateWithDDMMYYYY("23/11/2015"));
			//Predicate p1 = criteriaBuilder.equal(owner.get("name"), "Raja");
			Predicate[] prdcs = new Predicate[] { p };
			Predicate finalP = criteriaBuilder.or(prdcs);
			criteriaQuery.where(finalP);
			criteriaQuery = criteriaQuery.select(root);
			// TypedQuery<Resource> typedQuery =
			// entityManager.createQuery(criteriaQuery);
			TypedQuery<TaskDetail> typedQuery = entityManager.createQuery(criteriaQuery);
			List<TaskDetail> results = typedQuery.getResultList();
			System.out.println(results.size());
			for (TaskDetail taskdetail : results) {
				System.out.println(taskdetail.getDuration());
				System.out.println(taskdetail.getStatus());
				System.out.println(taskdetail.getTaskDesc());
				System.out.println(taskdetail.getResource().getName());
				System.out.println(taskdetail.getResource().getTaskDate().toString());
			}
			return fourTDefaultCriteria.defaultSelectAllCriteria();
		} else {
			List<Predicate> allPredicates = Lists.newArrayList();
			for (FourTCriteria<TaskDetail> fourTCriteria : criteriaList) {
				allPredicates.add(fourTCriteria.applyCriteria());
			}
			Predicate[] predicates = allPredicates.toArray(new Predicate[allPredicates.size()]);
			Predicate finalPredicate = criteriaBuilder.and(predicates);
			criteriaQuery.where(finalPredicate);
			return entityManager.createQuery(criteriaQuery).getResultList();
			/*
			 * List<Predicate> allPredicates =
			 * predicateService.generateCriteriaPredicates(criteriaList);
			 * Predicate finalPredicate =
			 * predicateService.applyAndOnPredicates(criteriaBuilder,
			 * allPredicates); return
			 * fourTService.findResourceByCriteriaQuery(criteriaQuery.where(
			 * finalPredicate));
			 */
		}
	}

}
