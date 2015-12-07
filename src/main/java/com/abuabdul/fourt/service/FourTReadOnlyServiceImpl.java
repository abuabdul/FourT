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

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.transaction.annotation.Transactional;

import com.abuabdul.fourt.dao.FourTReadOnlyDBDAO;
import com.abuabdul.fourt.exception.FourTServiceException;

/**
 * @author abuabdul
 *
 */
@Transactional(value = "readOnlyTransactionManager")
public class FourTReadOnlyServiceImpl implements FourTReadOnlyService {

	private FourTReadOnlyDBDAO fourTReadOnlyDBDAO;

	public FourTReadOnlyServiceImpl(FourTReadOnlyDBDAO fourTReadOnlyDBDAO) {
		this.fourTReadOnlyDBDAO = fourTReadOnlyDBDAO;
	}

	@Override
	public List<Object[]> findCustomTaskResults(String nativeQuery) throws FourTServiceException {
		return fourTReadOnlyDBDAO.findCustomTaskResults(nativeQuery);
	}

	@Override
	public EntityManager getEntityManager() {
		return fourTReadOnlyDBDAO.getEntityManager();
	}
}
