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
