package com.abuabdul.fourt.criteria.fallback;

import java.util.List;

import com.abuabdul.fourt.domain.TaskDetail;
import com.abuabdul.fourt.exception.FourTServiceException;

public interface FourTDefaultCriteria {

	List<TaskDetail> defaultSelectAllCriteria() throws FourTServiceException;

}
