package com.abuabdul.fourt.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.abuabdul.fourt.dao.FourTBaseDAO;
import com.abuabdul.fourt.dao.FourTTaskDetailDAO;
import com.abuabdul.fourt.domain.TaskDetail;
import com.abuabdul.fourt.exception.FourTServiceException;

/**
 * @author abuabdul
 *
 */
@Repository
public class FourTTaskDetailDAOImpl extends FourTBaseDAO<TaskDetail, Long>implements FourTTaskDetailDAO {

	@Override
	public void saveTaskDetail(TaskDetail taskDetail) throws FourTServiceException {
		save(taskDetail);
	}

	@Override
	public List<TaskDetail> findAllTaskDetails() throws FourTServiceException {
		return super.listAll();
	}

	@Override
	public EntityManager getEntityManager() {
		return super.getEntityManager();
	}

}
