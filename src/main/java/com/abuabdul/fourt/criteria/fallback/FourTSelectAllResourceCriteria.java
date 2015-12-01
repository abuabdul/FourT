package com.abuabdul.fourt.criteria.fallback;

import java.util.List;

import com.abuabdul.fourt.domain.TaskDetail;
import com.abuabdul.fourt.exception.FourTServiceException;
import com.abuabdul.fourt.service.FourTService;

/**
 * Criteria to find Results for All Resources
 * 
 * @author abuabdul
 *
 */
public class FourTSelectAllResourceCriteria implements FourTDefaultCriteria {

	private final FourTService fourTService;

	public FourTSelectAllResourceCriteria(FourTService fourTService) {
		this.fourTService = fourTService;
	}

	@Override
	public List<TaskDetail> defaultSelectAllCriteria() throws FourTServiceException {
		return fourTService.findAllTaskDetails();
	}

}
