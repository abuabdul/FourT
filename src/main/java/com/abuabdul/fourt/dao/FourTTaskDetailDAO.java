package com.abuabdul.fourt.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

import com.abuabdul.fourt.domain.TaskDetail;
import com.abuabdul.fourt.exception.FourTServiceException;

/**
 * @author abuabdul
 *
 */
public interface FourTTaskDetailDAO {

	public EntityManager getEntityManager();

	public List<TaskDetail> findAllTaskDetails() throws FourTServiceException;

	public void saveTaskDetail(TaskDetail taskDetail) throws FourTServiceException;

	public List<TaskDetail> findTaskDetailByCriteriaQuery(CriteriaQuery<TaskDetail> criteria)
			throws FourTServiceException;

}
