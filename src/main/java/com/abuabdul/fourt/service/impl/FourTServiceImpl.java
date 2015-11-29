package com.abuabdul.fourt.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.abuabdul.fourt.dao.FourTResourceDAO;
import com.abuabdul.fourt.domain.Resource;
import com.abuabdul.fourt.exception.FourTServiceException;
import com.abuabdul.fourt.service.FourTService;

/**
 * @author abuabdul
 *
 */
@Service
@Transactional
public class FourTServiceImpl implements FourTService {

	@Autowired
	private FourTResourceDAO fourTResourceDAO;

	@Override
	public void saveResourceTaskDetails(Resource resource) throws FourTServiceException {
		fourTResourceDAO.saveResource(resource);
	}

	@Override
	public List<Resource> findAllResourceTaskDetails() throws FourTServiceException {
		return fourTResourceDAO.findAllResources();
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
	public List<Object[]> viewCustomTaskResults(String nativeQuery) throws FourTServiceException {
		return fourTResourceDAO.findCustomTaskResults(nativeQuery);
	}
}
