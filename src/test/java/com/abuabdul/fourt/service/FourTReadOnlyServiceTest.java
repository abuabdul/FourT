package com.abuabdul.fourt.service;

import static org.mockito.Mockito.verify;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.abuabdul.fourt.dao.FourTReadOnlyDBDAO;
import com.abuabdul.fourt.exception.FourTServiceException;

public class FourTReadOnlyServiceTest {

	@Mock
	private FourTReadOnlyDBDAO fourTReadOnlyDBDAO;

	@InjectMocks
	private FourTReadOnlyServiceImpl fourTReadOnlyService;

	@BeforeMethod
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}

	@Test(groups = "integration")
	public void testFindCustomTaskResults() throws FourTServiceException{
		String nativeQuery = "Select * from Resource";
		fourTReadOnlyService.findCustomTaskResults(nativeQuery);
		verify(fourTReadOnlyDBDAO).findCustomTaskResults(nativeQuery);
	}

}
