package com.abuabdul.fourt.service;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
@Service
@Transactional(value="transactionManager")
public class FourTServiceImpl implements FourTService {

	@Autowired
	private FourTResourceDAO fourTResourceDAO;

	@Autowired
	private FourTTaskDetailDAO fourTTaskDetailDAO;

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
