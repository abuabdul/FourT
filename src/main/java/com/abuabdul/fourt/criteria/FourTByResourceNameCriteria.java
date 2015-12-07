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
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.abuabdul.fourt.domain.Resource;
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
			Root<TaskDetail> root, String resourceName) {
		super(criteria, criteriaBuilder, root);
		this.byResourceName = resourceName;
	}

	@Override
	public Predicate applyCriteria() {
		Join<TaskDetail, Resource> owner = root.join("resource");
		Predicate p = criteriaBuilder.equal(owner.get("name"), byResourceName);
		return p;
	}

	public String getByResourceName() {
		return byResourceName;
	}
	
}
