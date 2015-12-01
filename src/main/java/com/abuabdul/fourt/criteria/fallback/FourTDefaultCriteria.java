package com.abuabdul.fourt.criteria.fallback;

import java.util.List;

import com.abuabdul.fourt.exception.FourTServiceException;

/**
 * @author abuabdul
 *
 * @param <T>
 */
public interface FourTDefaultCriteria<T> {

	List<T> defaultSelectAllCriteria() throws FourTServiceException;

}
