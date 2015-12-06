package com.abuabdul.fourt.service;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.transaction.annotation.Transactional;

import com.abuabdul.fourt.dao.FourTReadOnlyDBDAO;
import com.abuabdul.fourt.exception.FourTServiceException;

/**
 * @author abuabdul
 *
 */
@Transactional(value = "readOnlyTransactionManager")
public class FourTReadOnlyServiceImpl implements FourTReadOnlyService {

	private FourTReadOnlyDBDAO fourTReadOnlyDBDAO;

	public FourTReadOnlyServiceImpl(FourTReadOnlyDBDAO fourTReadOnlyDBDAO) {
		this.fourTReadOnlyDBDAO = fourTReadOnlyDBDAO;
	}

	@Override
	public List<Object[]> findCustomTaskResults(String nativeQuery) throws FourTServiceException {
		return fourTReadOnlyDBDAO.findCustomTaskResults(nativeQuery);
	}

	@Override
	public EntityManager getEntityManager() {
		return fourTReadOnlyDBDAO.getEntityManager();
	}

}
