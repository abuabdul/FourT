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
