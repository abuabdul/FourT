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

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

import com.abuabdul.fourt.dao.FourTBaseDAO;
import com.abuabdul.fourt.dao.FourTTaskDetailDAO;
import com.abuabdul.fourt.domain.TaskDetail;
import com.abuabdul.fourt.exception.FourTServiceException;

/**
 * @author abuabdul
 *
 */
public class FourTTaskDetailDAOImpl extends FourTBaseDAO<TaskDetail, Long> implements FourTTaskDetailDAO {

	@Override
	public EntityManager getEntityManager() {
		return super.getEntityManager();
	}

	@Override
	public void saveTaskDetail(TaskDetail taskDetail) throws FourTServiceException {
		super.save(taskDetail);
	}

	@Override
	public List<TaskDetail> findAllTaskDetails() throws FourTServiceException {
		return super.listAll();
	}

	@Override
	public List<TaskDetail> findTaskDetailByCriteriaQuery(CriteriaQuery<TaskDetail> criteria)
			throws FourTServiceException {
		return getEntityManager().createQuery(criteria).getResultList();
	}

}
