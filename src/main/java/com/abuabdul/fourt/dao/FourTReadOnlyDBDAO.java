package com.abuabdul.fourt.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.abuabdul.fourt.exception.FourTServiceException;

/**
 * @author abuabdul
 *
 */
public interface FourTReadOnlyDBDAO {

	EntityManager getEntityManager();
	
	List<Object[]> findCustomTaskResults(String nativeQuery) throws FourTServiceException;
}
