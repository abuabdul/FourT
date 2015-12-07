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

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

import org.springframework.transaction.annotation.Transactional;

import com.abuabdul.fourt.dao.FourTResourceDAO;
import com.abuabdul.fourt.dao.FourTTaskDetailDAO;
import com.abuabdul.fourt.domain.Resource;
import com.abuabdul.fourt.domain.TaskDetail;
import com.abuabdul.fourt.exception.FourTServiceException;

/**
 * @author abuabdul
 *
 */
@Transactional(value = "transactionManager")
public class FourTVetoServiceImpl implements FourTVetoService {

	private FourTResourceDAO fourTResourceDAO;

	private FourTTaskDetailDAO fourTTaskDetailDAO;

	public FourTVetoServiceImpl(FourTResourceDAO fourTResourceDAO, FourTTaskDetailDAO fourTTaskDetailDAO) {
		this.fourTResourceDAO = fourTResourceDAO;
		this.fourTTaskDetailDAO = fourTTaskDetailDAO;
	}

	@Override
	public void saveResourceTaskDetails(Resource resource) throws FourTServiceException {
		fourTResourceDAO.saveResource(resource);
	}

	@Override
	public List<Resource> findAllResourceWithTaskDetails() throws FourTServiceException {
		return fourTResourceDAO.findAllResources();
	}

	@Override
	public List<TaskDetail> findAllTaskDetails() throws FourTServiceException {
		return fourTTaskDetailDAO.findAllTaskDetails();
	}

	@Override
	public List<Resource> findResourceByName(String resourceName) throws FourTServiceException {
		return fourTResourceDAO.findResourceByName(resourceName);
	}

	@Override
	public List<Resource> findResourceByTaskDate(Date taskDate) throws FourTServiceException {
		return fourTResourceDAO.findResourceByTaskDate(taskDate);
	}

	@Override
	public List<Resource> findResourceByTaskStatus(String taskStatus) throws FourTServiceException {
		return fourTResourceDAO.findResourceByTaskStatus(taskStatus);
	}

	@Override
	public List<Resource> findResourceByTaskDuration(Float taskDuration) throws FourTServiceException {
		return fourTResourceDAO.findResourceByTaskDuration(taskDuration);
	}

	@Override
	public List<Resource> findResourceByTaskDurationLessThan(Float taskDuration) throws FourTServiceException {
		return fourTResourceDAO.findResourceByTaskDurationLessThan(taskDuration);
	}

	@Override
	public List<Resource> findResourceByTaskDurationGreaterThan(Float taskDuration) throws FourTServiceException {
		return fourTResourceDAO.findResourceByTaskDurationGreaterThan(taskDuration);
	}

	@Override
	public EntityManager getEntityManager() {
		return fourTResourceDAO.getEntityManager();
	}

	@Override
	public List<TaskDetail> findTaskDetailByCriteriaQuery(CriteriaQuery<TaskDetail> criteria)
			throws FourTServiceException {
		return fourTTaskDetailDAO.findTaskDetailByCriteriaQuery(criteria);
	}

}
