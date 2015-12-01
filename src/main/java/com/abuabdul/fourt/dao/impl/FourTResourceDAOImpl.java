package com.abuabdul.fourt.dao.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.abuabdul.fourt.dao.FourTBaseDAO;
import com.abuabdul.fourt.dao.FourTResourceDAO;
import com.abuabdul.fourt.domain.Resource;
import com.abuabdul.fourt.exception.FourTServiceException;

/**
 * @author abuabdul
 *
 */
@Repository
public class FourTResourceDAOImpl extends FourTBaseDAO<Resource, Long>implements FourTResourceDAO {

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

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> findCustomTaskResults(String nativeQuery) throws FourTServiceException {
		Query customQuery = getEntityManager().createNativeQuery(nativeQuery);
		return customQuery.getResultList();
	}

	@Override
	public EntityManager getEntityManager() {
		return super.getEntityManager();
	}
}
