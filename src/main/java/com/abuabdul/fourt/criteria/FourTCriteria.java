package com.abuabdul.fourt.criteria;

import javax.persistence.criteria.Predicate;

/**
 * @author abuabdul
 *
 */
public interface FourTCriteria<T> {

	Predicate applyCriteria();

}
