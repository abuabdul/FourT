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
