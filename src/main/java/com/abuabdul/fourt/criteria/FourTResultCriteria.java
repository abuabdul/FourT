package com.abuabdul.fourt.criteria;

import java.util.List;

import com.abuabdul.fourt.domain.Resource;
import com.abuabdul.fourt.model.ResourceTaskDetail;

/**
 * @author abuabdul
 *
 */
public interface FourTResultCriteria {
	
	List<Resource> findTasksBasedOn(ResourceTaskDetail resourceTaskDtl);

}
