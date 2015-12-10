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
package com.abuabdul.fourt.dao.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.abuabdul.fourt.dao.FourTBaseDAO;
import com.abuabdul.fourt.dao.FourTResourceDAO;
import com.abuabdul.fourt.domain.RefDetail;
import com.abuabdul.fourt.domain.Resource;
import com.abuabdul.fourt.exception.FourTServiceException;

/**
 * @author abuabdul
 *
 */
public class FourTResourceDAOImpl extends FourTBaseDAO<Resource, Long> implements FourTResourceDAO {

	@Override
	public void saveResource(Resource resource) throws FourTServiceException {
		super.save(resource);
	}

	@Override
	public List<Resource> findAllResources() throws FourTServiceException {
		return super.listAll();
	}

	@Override
	public List<Resource> findResourceByName(String resourceName) throws FourTServiceException {
		TypedQuery<Resource> query = getEntityManager().createQuery("from Resource r where r.name=:resourceName",
				Resource.class);
		query.setParameter("resourceName", resourceName);
		return query.getResultList();
	}

	@Override
	public List<Resource> findResourceByTaskDate(Date taskDate) throws FourTServiceException {
		TypedQuery<Resource> query = getEntityManager().createQuery("from Resource r where r.taskDate=:taskDate",
				Resource.class);
		query.setParameter("taskDate", taskDate);
		return query.getResultList();
	}

	@Override
	public List<Resource> findResourceByTaskStatus(String taskStatus) throws FourTServiceException {
		TypedQuery<Resource> query = getEntityManager().createQuery(
				"select r from Resource r join r.taskDetailList td where td.status=:taskStatus", Resource.class);
		query.setParameter("taskStatus", taskStatus);
		return query.getResultList();
	}

	@Override
	public List<Resource> findResourceByTaskDuration(Float taskDuration) throws FourTServiceException {
		TypedQuery<Resource> query = getEntityManager().createQuery(
				"select r from Resource r join r.taskDetailList td where td.duration=:taskDuration", Resource.class);
		query.setParameter("taskDuration", taskDuration);
		return query.getResultList();
	}

	@Override
	public List<Resource> findResourceByTaskDurationLessThan(Float taskDuration) throws FourTServiceException {
		TypedQuery<Resource> query = getEntityManager().createQuery(
				"select r from Resource r join r.taskDetailList td where td.duration<:taskDuration", Resource.class);
		query.setParameter("taskDuration", taskDuration);
		return query.getResultList();
	}

	@Override
	public List<Resource> findResourceByTaskDurationGreaterThan(Float taskDuration) throws FourTServiceException {
		TypedQuery<Resource> query = getEntityManager().createQuery(
				"select r from Resource r join r.taskDetailList td where td.duration>:taskDuration", Resource.class);
		query.setParameter("taskDuration", taskDuration);
		return query.getResultList();
	}

	@Override
	public List<RefDetail> findAllRefDetails(boolean active) throws FourTServiceException {
		TypedQuery<RefDetail> query = getEntityManager().createQuery("from RefDetail rf where rf.active=:active",
				RefDetail.class);
		query.setParameter("active", active);
		return query.getResultList();
	}

	@Override
	public EntityManager getEntityManager() {
		return super.getEntityManager();
	}

}
