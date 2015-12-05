package com.abuabdul.fourt.criteria.result;

import java.util.List;

import com.abuabdul.fourt.criteria.FourTCriteria;
import com.abuabdul.fourt.criteria.builder.FourTResourceTaskCriteriaBuilder;
import com.abuabdul.fourt.criteria.fallback.FourTSelectAllTaskDetailCriteria;
import com.abuabdul.fourt.criteria.predicate.FourTPredicateServiceImpl;
import com.abuabdul.fourt.domain.TaskDetail;
import com.abuabdul.fourt.exception.FourTServiceException;
import com.abuabdul.fourt.model.ResourceTaskDetail;
import com.abuabdul.fourt.service.FourTVetoService;
import com.google.common.collect.Lists;

/**
 * @author abuabdul
 *
 */
public class FourTResultCriteriaService implements FourTResultCriteria {

	private final List<FourTCriteria<TaskDetail>> criteriaList = Lists.newArrayList();
	
	private final FourTVetoService fourTVetoService;

	public FourTResultCriteriaService(FourTVetoService fourTService) {
		this.fourTVetoService = fourTService;
	}

	@Override
	public List<TaskDetail> findTasksBasedOn(ResourceTaskDetail resourceTaskDtl) throws FourTServiceException {
		return new FourTResourceTaskCriteriaBuilder(fourTVetoService, criteriaList)
				.withInputResourceTaskDetail(resourceTaskDtl)
				.withPredicateService(new FourTPredicateServiceImpl())
				.addResourceNameCriteria()
				.addTaskDateCriteria()
				.addTaskDurationCriteria()
				.addTaskStatusCriteria()
				.withDefaultCriteria(new FourTSelectAllTaskDetailCriteria(fourTVetoService))
				.executeCriteria();
	}

}
