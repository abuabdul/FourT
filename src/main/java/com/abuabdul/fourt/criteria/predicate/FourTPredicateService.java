package com.abuabdul.fourt.criteria.predicate;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;

import com.abuabdul.fourt.criteria.FourTCriteria;

/**
 * @author abuabdul
 *
 * @param <T>
 */
public interface FourTPredicateService<T> {

	List<Predicate> generateCriteriaPredicates(List<FourTCriteria<T>> criteriaList);

	Predicate applyAndOnPredicates(CriteriaBuilder criteriaBuilder, List<Predicate> predicates);

}
