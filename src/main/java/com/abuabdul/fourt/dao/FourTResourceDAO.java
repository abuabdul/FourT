package com.abuabdul.fourt.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

import com.abuabdul.fourt.domain.Resource;
import com.abuabdul.fourt.exception.FourTServiceException;

/**
 * @author abuabdul
 *
 */
public interface FourTResourceDAO {

	public EntityManager getEntityManager();

	public void saveResource(Resource resource) throws FourTServiceException;

	public List<Resource> findAllResources() throws FourTServiceException;

	public List<Resource> findResourceByName(String resourceName) throws FourTServiceException;

	public List<Resource> findResourceByTaskDate(Date taskDate) throws FourTServiceException;

	public List<Resource> findResourceByTaskStatus(String taskStatus) throws FourTServiceException;

	public List<Resource> findResourceByTaskDuration(Float taskDuration) throws FourTServiceException;

	public List<Resource> findResourceByTaskDurationLessThan(Float taskDuration) throws FourTServiceException;

	public List<Resource> findResourceByTaskDurationGreaterThan(Float taskDuration) throws FourTServiceException;

	public List<Resource> findResourceByCriteriaQuery(CriteriaQuery<Resource> criteria) throws FourTServiceException;

	public List<Object[]> findCustomTaskResults(String nativeQuery) throws FourTServiceException;

}
