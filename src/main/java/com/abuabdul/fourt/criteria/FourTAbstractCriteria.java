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
import javax.persistence.criteria.Root;

import com.abuabdul.fourt.domain.TaskDetail;

/**
 * @author abuabdul
 *
 * @param <T>
 */
public abstract class FourTAbstractCriteria<T> implements FourTCriteria<T> {

	protected final CriteriaQuery<T> criteriaQuery;
	protected final CriteriaBuilder criteriaBuilder;
	protected final Root<TaskDetail> root;

	public FourTAbstractCriteria(CriteriaQuery<T> criteria, CriteriaBuilder criteriaBuilder, Root<TaskDetail> root) {
		this.criteriaQuery = criteria;
		this.criteriaBuilder = criteriaBuilder;
		this.root = root;
	}

}
