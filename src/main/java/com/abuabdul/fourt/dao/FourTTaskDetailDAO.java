package com.abuabdul.fourt.dao;

import java.util.List;

import javax.persistence.EntityManager;

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
}
