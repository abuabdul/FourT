package com.abuabdul.fourt.service;

import com.abuabdul.fourt.domain.Resource;
import com.abuabdul.fourt.exception.FourTException;

/**
 * @author abuabdul
 *
 */
public interface FourTService {

	public void saveTaskDetails(Resource resource) throws FourTException;

	public void viewTaskDetails() throws FourTException;

	public void viewCustomTaskDetails(String query) throws FourTException;

}
