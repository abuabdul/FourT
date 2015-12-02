package com.abuabdul.fourt.service;

import java.util.List;

import javax.persistence.EntityManager;

import com.abuabdul.fourt.exception.FourTServiceException;

/**
 * @author abuabdul
 *
 */
public interface FourTReadOnlyService {

	public EntityManager getEntityManager();

	public List<Object[]> findCustomTaskResults(String nativeQuery) throws FourTServiceException;

}
