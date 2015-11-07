package com.abuabdul.fourt.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.abuabdul.fourt.domain.Resource;
import com.abuabdul.fourt.exception.FourTException;
import com.abuabdul.fourt.service.FourTService;

/**
 * @author abuabdul
 *
 */
@Service
@Transactional
public class FourTServiceImpl implements FourTService {

	@Override
	public void saveTaskDetails(Resource resource) throws FourTException {
		// TODO Auto-generated method stub

	}

	@Override
	public void viewTaskDetails() throws FourTException {
		// TODO Auto-generated method stub

	}

	@Override
	public void viewCustomTaskDetails(String query) throws FourTException {
		// TODO Auto-generated method stub

	}

}
