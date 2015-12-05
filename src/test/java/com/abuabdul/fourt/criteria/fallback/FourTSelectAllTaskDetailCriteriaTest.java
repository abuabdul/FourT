package com.abuabdul.fourt.criteria.fallback;

import static org.mockito.Mockito.verify;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.abuabdul.fourt.domain.TaskDetail;
import com.abuabdul.fourt.exception.FourTServiceException;
import com.abuabdul.fourt.service.FourTVetoService;

public class FourTSelectAllTaskDetailCriteriaTest {

	@Mock
	private FourTVetoService fourTVetoService;

	@BeforeMethod
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}

	@Test(groups = "integration")
	public void testDefaultSelectAllCriteria() throws FourTServiceException {
		FourTDefaultCriteria<TaskDetail> fourTDefaultCriteria = new FourTSelectAllTaskDetailCriteria(
				fourTVetoService);
		fourTDefaultCriteria.defaultSelectAllCriteria();

		verify(fourTVetoService).findAllTaskDetails();
	}

}
