package com.abuabdul.fourt.criteria.builder;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.collections.Lists;

import com.abuabdul.fourt.criteria.FourTByResourceNameCriteria;
import com.abuabdul.fourt.criteria.FourTCriteria;
import com.abuabdul.fourt.criteria.fallback.FourTDefaultCriteria;
import com.abuabdul.fourt.criteria.predicate.FourTPredicateService;
import com.abuabdul.fourt.domain.TaskDetail;
import com.abuabdul.fourt.exception.FourTServiceException;
import com.abuabdul.fourt.model.ResourceTaskDetail;
import com.abuabdul.fourt.service.FourTVetoService;

/**
 * TestNG tests to validate ResourceTaskCriteriaBuilder
 * 
 * @author abuabdul
 *
 */
public class FourTResourceTaskCriteriaBuilderTest {

	@Mock
	private FourTVetoService fourTVetoService;

	@Mock
	private FourTPredicateService<TaskDetail> fourTPredicateService;

	@Mock
	private FourTDefaultCriteria<TaskDetail> fourTDefaultCriteria;

	@Mock
	private EntityManager entityManager;

	@Mock
	private CriteriaBuilder criteriaBuilder;

	@Mock
	private CriteriaQuery<TaskDetail> criteriaQuery;

	@Mock
	private Root<TaskDetail> root;

	@Mock
	private List<Predicate> allPredicates;

	@Mock
	private Predicate finalPredicate;

	private List<TaskDetail> resultLists = Lists.newArrayList(new TaskDetail());
	private List<FourTCriteria<TaskDetail>> criteriaList = Lists.newArrayList();

	@BeforeMethod
	public void initAndStubMocks() throws FourTServiceException {
		MockitoAnnotations.initMocks(this);

		when(fourTVetoService.getEntityManager()).thenReturn(entityManager);
		when(entityManager.getCriteriaBuilder()).thenReturn(criteriaBuilder);
		when(criteriaBuilder.createQuery(TaskDetail.class)).thenReturn(criteriaQuery);
		when(criteriaQuery.from(TaskDetail.class)).thenReturn(root);
		when(fourTDefaultCriteria.defaultSelectAllCriteria()).thenReturn(resultLists);
		when(fourTPredicateService.generateCriteriaPredicates(criteriaList)).thenReturn(allPredicates);
		when(fourTPredicateService.applyOROnPredicates(criteriaBuilder, allPredicates)).thenReturn(finalPredicate);
		when(fourTPredicateService.whereClauseAndSelect(criteriaQuery, finalPredicate, root)).thenReturn(criteriaQuery);
		when(fourTVetoService.findTaskDetailByCriteriaQuery(criteriaQuery)).thenReturn(resultLists);
	}

	@Test(groups = "integration")
	public void testResourceCriteriaBuilderForDefault() throws FourTServiceException {
		// build ResourceTaskDetail for default
		ResourceTaskDetail resourceTaskDtl = new ResourceTaskDetailBuilder().build();

		List<TaskDetail> taskDetails = new FourTResourceTaskCriteriaBuilder(fourTVetoService, criteriaList)
				.withInputResourceTaskDetail(resourceTaskDtl).withPredicateService(fourTPredicateService)
				.addResourceNameCriteria().addTaskDateCriteria().addTaskDurationCriteria().addTaskStatusCriteria()
				.withDefaultCriteria(fourTDefaultCriteria).executeCriteria();

		verify(fourTDefaultCriteria, times(1)).defaultSelectAllCriteria();

		assertThat(taskDetails, hasSize(1));
		assertThat(taskDetails, equalTo(resultLists));

		verify(fourTPredicateService, never()).generateCriteriaPredicates(criteriaList);
		verify(fourTPredicateService, never()).applyOROnPredicates(criteriaBuilder, allPredicates);
		verify(fourTPredicateService, never()).whereClauseAndSelect(criteriaQuery, finalPredicate, root);
		verify(fourTVetoService, never()).findTaskDetailByCriteriaQuery(criteriaQuery);
		assertThat(criteriaList, empty());

		// Reset the criteria after every test
		criteriaList = Lists.newArrayList();
	}

	@Test(groups = "integration")
	public void testResourceCriteriaBuilderForCriteria() throws FourTServiceException {
		// build ResourceTaskDetail for all criteria
		ResourceTaskDetail resourceTaskDtl = new ResourceTaskDetailBuilder("Abu", "23/12/2015", "3.0", "In Progress")
				.build();

		List<TaskDetail> taskDetails = new FourTResourceTaskCriteriaBuilder(fourTVetoService, criteriaList)
				.withInputResourceTaskDetail(resourceTaskDtl).withPredicateService(fourTPredicateService)
				.addResourceNameCriteria().addTaskDateCriteria().addTaskDurationCriteria().addTaskStatusCriteria()
				.withDefaultCriteria(fourTDefaultCriteria).executeCriteria();

		verify(fourTPredicateService, times(1)).generateCriteriaPredicates(criteriaList);
		verify(fourTPredicateService, times(1)).applyOROnPredicates(criteriaBuilder, allPredicates);
		verify(fourTPredicateService, times(1)).whereClauseAndSelect(criteriaQuery, finalPredicate, root);
		verify(fourTVetoService, times(1)).findTaskDetailByCriteriaQuery(criteriaQuery);

		assertThat(taskDetails, hasSize(1));
		assertThat(taskDetails, equalTo(resultLists));
		assertThat(criteriaList, hasSize(4));

		verify(fourTDefaultCriteria, never()).defaultSelectAllCriteria();

		// Reset the criteria after every test
		criteriaList = Lists.newArrayList();
	}

	@Test(groups = "integration")
	public void testResourceCriteriaBuilderForResourceNameCriteria() throws FourTServiceException {
		// build ResourceTaskDetail for all criteria
		ResourceTaskDetail resourceTaskDtl = new ResourceTaskDetailBuilder().withOnlyResourceNameCriteria("Abu")
				.build();

		List<TaskDetail> taskDetails = new FourTResourceTaskCriteriaBuilder(fourTVetoService, criteriaList)
				.withInputResourceTaskDetail(resourceTaskDtl).withPredicateService(fourTPredicateService)
				.addResourceNameCriteria().addTaskDateCriteria().addTaskDurationCriteria().addTaskStatusCriteria()
				.withDefaultCriteria(fourTDefaultCriteria).executeCriteria();

		verify(fourTPredicateService, times(1)).generateCriteriaPredicates(criteriaList);
		verify(fourTPredicateService, times(1)).applyOROnPredicates(criteriaBuilder, allPredicates);
		verify(fourTPredicateService, times(1)).whereClauseAndSelect(criteriaQuery, finalPredicate, root);
		verify(fourTVetoService, times(1)).findTaskDetailByCriteriaQuery(criteriaQuery);

		assertThat(taskDetails, equalTo(resultLists));
		assertThat(taskDetails, hasSize(1));
		assertThat(criteriaList, hasSize(1));
		assertThat(criteriaList.get(0), instanceOf(FourTByResourceNameCriteria.class));
		FourTByResourceNameCriteria resourceNameCriteria = (FourTByResourceNameCriteria) criteriaList.get(0);
		assertThat(resourceNameCriteria.getByResourceName(), equalTo("Abu"));

		verify(fourTDefaultCriteria, never()).defaultSelectAllCriteria();

		// Reset the criteria after every test
		criteriaList = Lists.newArrayList();
	}
}
