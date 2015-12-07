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
package com.abuabdul.fourt.criteria.predicate;

import static org.springframework.util.CollectionUtils.isEmpty;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.abuabdul.fourt.criteria.FourTCriteria;
import com.abuabdul.fourt.domain.TaskDetail;
import com.google.common.collect.Lists;

/**
 * @author abuabdul
 *
 * @param <Resource>
 */
public class FourTPredicateServiceImpl implements FourTPredicateService<TaskDetail> {

	@Override
	public List<Predicate> generateCriteriaPredicates(List<FourTCriteria<TaskDetail>> criteriaList) {
		List<Predicate> allPredicates = Lists.newArrayList();
		if (!isEmpty(criteriaList)) {
			for (FourTCriteria<TaskDetail> fourTCriteria : criteriaList) {
				allPredicates.add(fourTCriteria.applyCriteria());
			}
		}
		return allPredicates;
	}

	@Override
	public Predicate applyOROnPredicates(CriteriaBuilder criteriaBuilder, List<Predicate> allPredicates) {
		Predicate[] predicates = allPredicates.toArray(new Predicate[allPredicates.size()]);
		return criteriaBuilder.or(predicates);
	}

	@Override
	public CriteriaQuery<TaskDetail> whereClauseAndSelect(CriteriaQuery<TaskDetail> criteria, Predicate finalPredicate,
			Root<TaskDetail> root) {
		criteria.where(finalPredicate);
		criteria.select(root);
		return criteria;
	}

}
