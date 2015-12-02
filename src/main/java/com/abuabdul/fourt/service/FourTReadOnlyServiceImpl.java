package com.abuabdul.fourt.service;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.abuabdul.fourt.dao.FourTReadOnlyDBDAO;
import com.abuabdul.fourt.exception.FourTServiceException;

/**
 * @author abuabdul
 *
 */
@Service
@Transactional(value = "readOnlyTransactionManager")
public class FourTReadOnlyServiceImpl implements FourTReadOnlyService {

	@Autowired
	private FourTReadOnlyDBDAO fourTReadOnlyDBDAO;

	@Override
	public List<Object[]> findCustomTaskResults(String nativeQuery) throws FourTServiceException {
		return fourTReadOnlyDBDAO.findCustomTaskResults(nativeQuery);
	}

	@Override
	public EntityManager getEntityManager() {
		return fourTReadOnlyDBDAO.getEntityManager();
	}

}
