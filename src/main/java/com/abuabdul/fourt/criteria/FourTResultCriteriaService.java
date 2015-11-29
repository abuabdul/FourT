package com.abuabdul.fourt.criteria;

import java.util.List;

import com.abuabdul.fourt.domain.Resource;
import com.abuabdul.fourt.model.ResourceTaskDetail;
import com.abuabdul.fourt.service.FourTService;
import com.google.common.collect.Lists;

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
	public List<Resource> findTasksBasedOn(ResourceTaskDetail resourceTaskDtl) {
		return Lists.newArrayList();
	}

}
