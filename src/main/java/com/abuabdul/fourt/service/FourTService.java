package com.abuabdul.fourt.service;

import java.util.Date;
import java.util.List;

import com.abuabdul.fourt.domain.Resource;
import com.abuabdul.fourt.exception.FourTServiceException;

/**
 * @author abuabdul
 *
 */
public interface FourTService {

	public void saveResourceTaskDetails(Resource resource) throws FourTServiceException;

	public List<Resource> findResourceByName(String resourceName) throws FourTServiceException;

	public List<Resource> findResourceByTaskDate(Date taskDate) throws FourTServiceException;

	public List<Resource> findResourceByTaskStatus(String taskStatus) throws FourTServiceException;

	public List<Resource> findResourceByTaskDuration(Float taskDuration) throws FourTServiceException;

	public List<Resource> findResourceByTaskDurationLessThan(Float taskDuration) throws FourTServiceException;

	public List<Resource> findResourceByTaskDurationGreaterThan(Float taskDuration) throws FourTServiceException;

	public List<Object[]> viewCustomTaskResults(String nativeQuery) throws FourTServiceException;

}
