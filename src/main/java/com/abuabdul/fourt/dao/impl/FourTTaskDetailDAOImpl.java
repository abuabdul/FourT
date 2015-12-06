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
