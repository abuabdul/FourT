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
package com.abuabdul.fourt.criteria.fallback;

import static org.mockito.Mockito.verify;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.abuabdul.fourt.dao.FourTTaskDetailDAO;
import com.abuabdul.fourt.domain.TaskDetail;
import com.abuabdul.fourt.exception.FourTServiceException;
import com.abuabdul.fourt.service.FourTVetoServiceImpl;

/**
 * @author abuabdul
 *
 */
public class FourTSelectAllTaskDetailCriteriaTest {

	@Mock
	private FourTTaskDetailDAO fourTTaskDetailDAO;

	@Spy
	@InjectMocks
	private FourTVetoServiceImpl fourTVetoService;

	@BeforeMethod
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}

	@Test(groups = "integration")
	public void testDefaultSelectAllCriteria() throws FourTServiceException {
		FourTDefaultCriteria<TaskDetail> fourTDefaultCriteria = new FourTSelectAllTaskDetailCriteria(fourTVetoService);
		fourTDefaultCriteria.defaultSelectAllCriteria();

		verify(fourTVetoService).findAllTaskDetails();
		verify(fourTTaskDetailDAO).findAllTaskDetails();
	}

}
