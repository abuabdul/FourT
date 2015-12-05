package com.abuabdul.fourt.service;

import static com.abuabdul.fourt.util.FourTUtils.simpleDateWithDDMMYYYY;
import static org.mockito.Mockito.verify;

import java.util.Date;

import javax.persistence.criteria.CriteriaQuery;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.abuabdul.fourt.dao.FourTResourceDAO;
import com.abuabdul.fourt.dao.FourTTaskDetailDAO;
import com.abuabdul.fourt.domain.Resource;
import com.abuabdul.fourt.domain.TaskDetail;
import com.abuabdul.fourt.exception.FourTServiceException;

public class FourTVetoServiceTest {

	@Mock
	private FourTResourceDAO fourTResourceDAO;

	@Mock
	private FourTTaskDetailDAO fourTTaskDetailDAO;

	@InjectMocks
	private FourTVetoServiceImpl fourTVetoService;

	@Mock
	private CriteriaQuery<TaskDetail> criteria;

	@Mock
	private Resource resource;

	@BeforeMethod
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}

	@Test(groups = "integration")
	public void testSaveResourceTaskDetail() throws FourTServiceException {
		fourTVetoService.saveResourceTaskDetails(resource);
		verify(fourTResourceDAO).saveResource(resource);
	}

	@Test(groups = "integration")
	public void testFindAllResourceWithTaskDetails() throws FourTServiceException {
		fourTVetoService.findAllResourceWithTaskDetails();
		verify(fourTResourceDAO).findAllResources();
	}

	@Test(groups = "integration")
	public void testFindAllTaskDetails() throws FourTServiceException {
		fourTVetoService.findAllTaskDetails();
		verify(fourTTaskDetailDAO).findAllTaskDetails();
	}

	@Test(groups = "integration")
	public void testFindResourceByName() throws FourTServiceException {
		String resourceName = "Abu";
		fourTVetoService.findResourceByName(resourceName);
		verify(fourTResourceDAO).findResourceByName(resourceName);
	}

	@Test(groups = "integration")
	public void testFindResourceByTaskDate() throws FourTServiceException {
		Date taskDate = simpleDateWithDDMMYYYY("23/07/2015");
		fourTVetoService.findResourceByTaskDate(taskDate);
		verify(fourTResourceDAO).findResourceByTaskDate(taskDate);
	}

	@Test(groups = "integration")
	public void testFindResourceByTaskStatus() throws FourTServiceException {
		String taskStatus = "In Progress";
		fourTVetoService.findResourceByTaskStatus(taskStatus);
		verify(fourTResourceDAO).findResourceByTaskStatus(taskStatus);
	}

	@Test(groups = "integration")
	public void testFindResourceByTaskDuration() throws FourTServiceException {
		Float taskDuration = new Float(3.0);
		fourTVetoService.findResourceByTaskDuration(taskDuration);
		verify(fourTResourceDAO).findResourceByTaskDuration(taskDuration);
	}

	@Test(groups = "integration")
	public void testFindResourceByTaskDurationLessThan() throws FourTServiceException {
		Float taskDuration = new Float(3.0);
		fourTVetoService.findResourceByTaskDurationLessThan(taskDuration);
		verify(fourTResourceDAO).findResourceByTaskDurationLessThan(taskDuration);
	}

	@Test(groups = "integration")
	public void testFindResourceByTaskDurationGreaterThan() throws FourTServiceException {
		Float taskDuration = new Float(3.0);
		fourTVetoService.findResourceByTaskDurationGreaterThan(taskDuration);
		verify(fourTResourceDAO).findResourceByTaskDurationGreaterThan(taskDuration);
	}

	@Test(groups = "integration")
	public void testFindTaskDetailByCriteriaQuery() throws FourTServiceException {
		fourTVetoService.findTaskDetailByCriteriaQuery(criteria);
		verify(fourTTaskDetailDAO).findTaskDetailByCriteriaQuery(criteria);
	}

}
