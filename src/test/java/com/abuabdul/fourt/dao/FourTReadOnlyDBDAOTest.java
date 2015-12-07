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
package com.abuabdul.fourt.dao;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.when;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.collections.Lists;

import com.abuabdul.fourt.dao.impl.FourTReadOnlyDBDAOImpl;
import com.abuabdul.fourt.exception.FourTServiceException;

/**
 * @author abuabdul
 *
 */
public class FourTReadOnlyDBDAOTest {

	@Spy
	@InjectMocks
	private FourTReadOnlyDBDAOImpl fourTReadOnlyDBDAO;

	@Mock
	private EntityManager entityManager;

	@Mock
	private Query query;

	@BeforeMethod
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testFindCustomTaskResults() throws FourTServiceException {
		String nativeQuery = "Select * from resource";
		List<Object[]> resultList = Lists.newArrayList();
		resultList.add(new Object[0]);

		when(fourTReadOnlyDBDAO.getEntityManager()).thenReturn(entityManager);
		when(entityManager.createNativeQuery(nativeQuery)).thenReturn(query);
		when(query.getResultList()).thenReturn(resultList);

		List<Object[]> actualLists = fourTReadOnlyDBDAO.findCustomTaskResults(nativeQuery);

		assertThat(actualLists, equalTo(resultList));
		assertThat(actualLists, hasSize(1));
	}

}
