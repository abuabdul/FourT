package com.abuabdul.fourt.criteria.fallback;

import java.util.List;

import com.abuabdul.fourt.domain.TaskDetail;
import com.abuabdul.fourt.exception.FourTServiceException;
import com.abuabdul.fourt.service.FourTVetoService;

/**
 * Criteria to find Results for All Task Details
 * 
 * @author abuabdul
 *
 */
public class FourTSelectAllTaskDetailCriteria implements FourTDefaultCriteria<TaskDetail> {

	private final FourTVetoService fourTService;

	public FourTSelectAllTaskDetailCriteria(FourTVetoService fourTService) {
		this.fourTService = fourTService;
	}

	@Override
	public List<TaskDetail> defaultSelectAllCriteria() throws FourTServiceException {
		return fourTService.findAllTaskDetails();
	}

}
