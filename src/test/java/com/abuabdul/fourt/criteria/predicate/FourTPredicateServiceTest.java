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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.collections.Lists;

import com.abuabdul.fourt.criteria.FourTCriteria;
import com.abuabdul.fourt.domain.TaskDetail;

/**
 * @author abuabdul
 *
 */
public class FourTPredicateServiceTest {

	@Mock
	FourTCriteria<TaskDetail> fourTCriteria1;

	@Mock
	FourTCriteria<TaskDetail> fourTCriteria2;

	@Mock
	private CriteriaBuilder criteriaBuilder;

	@Mock
	private CriteriaQuery<TaskDetail> criteriaQuery;

	@Mock
	private Root<TaskDetail> root;

	@Mock
	private Predicate finalPredicate;

	@Spy
	private List<Predicate> allPredicates = Lists.newArrayList();

	private Predicate[] predicates = new Predicate[allPredicates.size()];
	private FourTPredicateService<TaskDetail> fourTPredicateService = new FourTPredicateServiceImpl();

	@BeforeMethod
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}

	@Test(groups = "integration")
	public void testGenerateCriteriaPredicates() {
		List<FourTCriteria<TaskDetail>> criteriaList = Lists.newArrayList();
		List<Predicate> generatedPredicates = fourTPredicateService.generateCriteriaPredicates(criteriaList);
		assertThat(generatedPredicates, hasSize(0));

		criteriaList.add(fourTCriteria1);
		criteriaList.add(fourTCriteria2);
		generatedPredicates = fourTPredicateService.generateCriteriaPredicates(criteriaList);
		assertThat(generatedPredicates, hasSize(2));
		verify(fourTCriteria1, times(1)).applyCriteria();
		verify(fourTCriteria2, times(1)).applyCriteria();
	}

	@Test(groups = "integration")
	public void testApplyOROnPredicates() {
		when(allPredicates.toArray()).thenReturn(predicates);
		fourTPredicateService.applyOROnPredicates(criteriaBuilder, allPredicates);
		verify(criteriaBuilder).or(predicates);
	}

	@Test(groups = "integration")
	public void testWhereClauseAndSelect() {
		CriteriaQuery<TaskDetail> criteria = fourTPredicateService.whereClauseAndSelect(criteriaQuery, finalPredicate,
				root);
		verify(criteriaQuery).where(finalPredicate);
		verify(criteriaQuery).select(root);
		assertThat(criteria, equalTo(criteriaQuery));
	}

}
