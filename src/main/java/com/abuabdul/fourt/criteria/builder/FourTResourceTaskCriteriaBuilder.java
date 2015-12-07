/*
 * Copyright 2013-2016 abuabdul.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */
package com.abuabdul.fourt.criteria.builder;

import static com.abuabdul.fourt.util.FourTUtils.simpleDateWithDDMMYYYY;
import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.springframework.util.CollectionUtils.isEmpty;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.abuabdul.fourt.criteria.FourTByResourceNameCriteria;
import com.abuabdul.fourt.criteria.FourTByResourceTaskDateCriteria;
import com.abuabdul.fourt.criteria.FourTByResourceTaskDurationCriteria;
import com.abuabdul.fourt.criteria.FourTByResourceTaskStatusCriteria;
import com.abuabdul.fourt.criteria.FourTCriteria;
import com.abuabdul.fourt.criteria.fallback.FourTDefaultCriteria;
import com.abuabdul.fourt.criteria.predicate.FourTPredicateService;
import com.abuabdul.fourt.domain.TaskDetail;
import com.abuabdul.fourt.exception.FourTServiceException;
import com.abuabdul.fourt.model.ResourceTaskDetail;
import com.abuabdul.fourt.service.FourTVetoService;

/**
 * @author abuabdul
 *
 */
public class FourTResourceTaskCriteriaBuilder {

	private final ResourceTaskDetail emptyResourceTaskDtl = new ResourceTaskDetail();
	private final List<FourTCriteria<TaskDetail>> criteriaList;
	private final FourTVetoService fourTService;
	private final CriteriaBuilder criteriaBuilder;
	private final CriteriaQuery<TaskDetail> criteriaQuery;
	private final Root<TaskDetail> root;

	private ResourceTaskDetail resourceTaskDetail;
	private FourTDefaultCriteria<TaskDetail> fourTDefaultCriteria;
	private FourTPredicateService<TaskDetail> predicateService;

	public FourTResourceTaskCriteriaBuilder(FourTVetoService fourTService,
			List<FourTCriteria<TaskDetail>> criteriaList) {
		this.fourTService = fourTService;
		this.criteriaBuilder = fourTService.getEntityManager().getCriteriaBuilder();
		this.criteriaQuery = criteriaBuilder.createQuery(TaskDetail.class);
		this.root = criteriaQuery.from(TaskDetail.class);
		this.criteriaList = criteriaList;
	}

	public FourTResourceTaskCriteriaBuilder withInputResourceTaskDetail(ResourceTaskDetail resourceTaskDetail) {
		this.resourceTaskDetail = resourceTaskDetail;
		return this;
	}

	public FourTResourceTaskCriteriaBuilder withDefaultCriteria(FourTDefaultCriteria<TaskDetail> fourTDefaultCriteria) {
		this.fourTDefaultCriteria = fourTDefaultCriteria;
		return this;
	}

	public FourTResourceTaskCriteriaBuilder withPredicateService(FourTPredicateService<TaskDetail> predicateService) {
		this.predicateService = predicateService;
		return this;
	}

	public FourTResourceTaskCriteriaBuilder addResourceNameCriteria() {
		if (isCriteriaApplicable(getDefault().getResourceName())) {
			criteriaList.add(newFourTResourceNameCriteria());
		}
		return this;
	}

	public FourTResourceTaskCriteriaBuilder addTaskDateCriteria() {
		if (isCriteriaApplicable(getDefault().getTaskDate())) {
			criteriaList.add(newFourTResourceTaskDateCriteria());
		}
		return this;
	}

	public FourTResourceTaskCriteriaBuilder addTaskDurationCriteria() {
		if (isCriteriaApplicable(getDefault().getDuration())) {
			criteriaList.add(newFourTResourceTaskDurationCriteria());
		}
		return this;
	}

	public FourTResourceTaskCriteriaBuilder addTaskStatusCriteria() {
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
		return new FourTByResourceNameCriteria(criteriaQuery, criteriaBuilder, root,
				resourceTaskDetail.getResourceName());
	}

	protected FourTCriteria<TaskDetail> newFourTResourceTaskDateCriteria() {
		return new FourTByResourceTaskDateCriteria(criteriaQuery, criteriaBuilder, root,
				simpleDateWithDDMMYYYY(resourceTaskDetail.getTaskDate().trim()));
	}

	protected FourTCriteria<TaskDetail> newFourTResourceTaskDurationCriteria() {
		return new FourTByResourceTaskDurationCriteria(criteriaQuery, criteriaBuilder, root,
				new Float(resourceTaskDetail.getDuration().trim()));
	}

	protected FourTCriteria<TaskDetail> newFourTResourceTaskStatusCriteria() {
		return new FourTByResourceTaskStatusCriteria(criteriaQuery, criteriaBuilder, root,
				resourceTaskDetail.getStatus());
	}

	public List<TaskDetail> executeCriteria() throws FourTServiceException {
		if (isEmpty(criteriaList)) {
			return fourTDefaultCriteria.defaultSelectAllCriteria();
		} else {
			List<Predicate> allPredicates = predicateService.generateCriteriaPredicates(criteriaList);
			Predicate finalPredicate = predicateService.applyOROnPredicates(criteriaBuilder, allPredicates);
			CriteriaQuery<TaskDetail> executableQuery = predicateService.whereClauseAndSelect(criteriaQuery,
					finalPredicate, root);
			return fourTService.findTaskDetailByCriteriaQuery(executableQuery);
		}
	}

}
