package com.abuabdul.fourt.criteria.result;

import java.util.List;

import com.abuabdul.fourt.criteria.builder.FourTResourceCriteriaBuilder;
import com.abuabdul.fourt.criteria.fallback.FourTSelectAllResourceCriteria;
import com.abuabdul.fourt.criteria.predicate.FourTPredicateServiceImpl;
import com.abuabdul.fourt.domain.TaskDetail;
import com.abuabdul.fourt.exception.FourTServiceException;
import com.abuabdul.fourt.model.ResourceTaskDetail;
import com.abuabdul.fourt.service.FourTService;

/**
 * @author abuabdul
 *
 */
public class FourTResultCriteriaService implements FourTResultCriteria {

	private final FourTService fourTService;

	public FourTResultCriteriaService(FourTService fourTService) {
		this.fourTService = fourTService;
	}

	@Override
	public List<TaskDetail> findTasksBasedOn(ResourceTaskDetail resourceTaskDtl) throws FourTServiceException {
		return new FourTResourceCriteriaBuilder(fourTService)
				.withInputResourceTaskDetail(resourceTaskDtl)
				.withPredicateService(new FourTPredicateServiceImpl<TaskDetail>())
				.addResourceNameCriteria()
				.addTaskDateCriteria()
				.addTaskDurationCriteria()
				.addTaskStatusCriteria()
				.withDefaultCriteria(new FourTSelectAllResourceCriteria(fourTService))
				.executeCriteria();
	}

}
