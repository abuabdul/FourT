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
package com.abuabdul.fourt.criteria.result;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.emptyCollectionOf;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.collections.Lists;

import com.abuabdul.fourt.criteria.builder.ResourceTaskDetailBuilder;
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
public class FourTResultCriteriaServiceTest {

	@Mock
	private FourTVetoService fourTVetoService;

	@Mock
	private FourTPredicateService<TaskDetail> fourTPredicateService;

	@Mock
	private FourTDefaultCriteria<TaskDetail> fourTDefaultCriteria;

	@InjectMocks
	private FourTResultCriteriaService fourTResultCriteria;

	@Mock
	private EntityManager entityManager;

	@Mock
	private CriteriaBuilder criteriaBuilder;

	@Mock
	private CriteriaQuery<TaskDetail> criteriaQuery;

	@Mock
	private Root<TaskDetail> root;

	@BeforeMethod
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
		when(fourTVetoService.getEntityManager()).thenReturn(entityManager);
		when(entityManager.getCriteriaBuilder()).thenReturn(criteriaBuilder);
		when(criteriaBuilder.createQuery(TaskDetail.class)).thenReturn(criteriaQuery);
		when(criteriaQuery.from(TaskDetail.class)).thenReturn(root);
	}

	@Test(groups = "integration")
	public void testFindTasksBasedOnTaskDetail() throws FourTServiceException {
		ResourceTaskDetail resourceTaskDtl = new ResourceTaskDetailBuilder().build();

		List<TaskDetail> taskList = fourTResultCriteria.findTasksBasedOn(resourceTaskDtl);
		
		assertThat(taskList, emptyCollectionOf(TaskDetail.class));
		verify(fourTDefaultCriteria, times(1)).defaultSelectAllCriteria();
	}

	@Test(groups = "integration")
	public void testFindTasksBasedOnTaskDetailWithDuration() throws FourTServiceException {
		ResourceTaskDetail resourceTaskDtl = new ResourceTaskDetailBuilder().withOnlyDurationCriteria("3.0").build();
		List<TaskDetail> resultLists = Lists.newArrayList();
		
		when(fourTVetoService.findTaskDetailByCriteriaQuery(criteriaQuery)).thenReturn(resultLists);
		
		List<TaskDetail> taskList = fourTResultCriteria.findTasksBasedOn(resourceTaskDtl);
	
		assertThat(taskList, emptyCollectionOf(TaskDetail.class));
		assertThat(taskList, equalTo(resultLists));
		verify(fourTDefaultCriteria, never()).defaultSelectAllCriteria();
	}

}
