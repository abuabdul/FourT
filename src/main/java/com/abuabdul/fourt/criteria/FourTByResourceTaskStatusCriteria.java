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

	public String getByTaskStatus() {
		return byTaskStatus;
	}
}
