package com.abuabdul.fourt.criteria.result;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.emptyCollectionOf;

import java.util.List;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.abuabdul.fourt.domain.TaskDetail;
import com.abuabdul.fourt.exception.FourTServiceException;
import com.abuabdul.fourt.model.ResourceTaskDetail;

/**
 * @author abuabdul
 *
 */
public class FourTResultCriteriaServiceTest {

	@Mock
	private FourTResultCriteria fourTResultCriteria;

	@Mock
	private ResourceTaskDetail resourceTaskDtl;

	@BeforeMethod
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testFindTasksBasedOnTaskDetail() throws FourTServiceException {
		List<TaskDetail> taskList = fourTResultCriteria.findTasksBasedOn(resourceTaskDtl);
		assertThat(taskList, emptyCollectionOf(TaskDetail.class));
	}

}
