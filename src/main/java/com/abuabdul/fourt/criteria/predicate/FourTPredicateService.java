package com.abuabdul.fourt.criteria.predicate;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.abuabdul.fourt.criteria.FourTCriteria;

/**
 * @author abuabdul
 *
 * @param <T>
 */
public interface FourTPredicateService<T> {

	List<Predicate> generateCriteriaPredicates(List<FourTCriteria<T>> criteriaList);

	Predicate applyOROnPredicates(CriteriaBuilder criteriaBuilder, List<Predicate> predicates);

	CriteriaQuery<T> whereClauseAndSelect(CriteriaQuery<T> criteria, Predicate finalPredicate, Root<T> root);

}
