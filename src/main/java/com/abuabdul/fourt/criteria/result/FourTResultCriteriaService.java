package com.abuabdul.fourt.criteria.result;

import java.util.List;

import com.abuabdul.fourt.criteria.FourTCriteria;
import com.abuabdul.fourt.criteria.builder.FourTResourceTaskCriteriaBuilder;
import com.abuabdul.fourt.criteria.fallback.FourTDefaultCriteria;
import com.abuabdul.fourt.criteria.predicate.FourTPredicateService;
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

	private final FourTVetoService fourTVetoService;
	private final FourTPredicateService<TaskDetail> fourTPredicateService;
	private final FourTDefaultCriteria<TaskDetail> fourTDefaultCriteria;

	public FourTResultCriteriaService(FourTVetoService fourTService,
			FourTPredicateService<TaskDetail> fourTPredicateService, FourTDefaultCriteria<TaskDetail> fourTDefaultCriteria) {
		this.fourTVetoService = fourTService;
		this.fourTPredicateService = fourTPredicateService;
		this.fourTDefaultCriteria = fourTDefaultCriteria;
	}

	@Override
	public List<TaskDetail> findTasksBasedOn(ResourceTaskDetail resourceTaskDtl) throws FourTServiceException {
		final List<FourTCriteria<TaskDetail>> criteriaList = Lists.newArrayList();
		return new FourTResourceTaskCriteriaBuilder(fourTVetoService, criteriaList)
				.withInputResourceTaskDetail(resourceTaskDtl)
				.withPredicateService(fourTPredicateService)
				.addResourceNameCriteria()
				.addTaskDateCriteria()
				.addTaskDurationCriteria()
				.addTaskStatusCriteria()
				.withDefaultCriteria(fourTDefaultCriteria)
				.executeCriteria();
	}

}
