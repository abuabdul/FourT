package com.abuabdul.fourt.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.abuabdul.fourt.dao.FourTReadOnlyBaseDAO;
import com.abuabdul.fourt.dao.FourTReadOnlyDBDAO;
import com.abuabdul.fourt.domain.Resource;
import com.abuabdul.fourt.exception.FourTServiceException;

/**
 * @author abuabdul
 *
 */
public class FourTReadOnlyDBDAOImpl extends FourTReadOnlyBaseDAO<Resource, Long>implements FourTReadOnlyDBDAO {

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
