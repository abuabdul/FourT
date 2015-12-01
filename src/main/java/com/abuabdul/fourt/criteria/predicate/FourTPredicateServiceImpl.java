package com.abuabdul.fourt.criteria.predicate;

import static org.springframework.util.CollectionUtils.isEmpty;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;

import com.abuabdul.fourt.criteria.FourTCriteria;
import com.google.common.collect.Lists;

/**
 * @author abuabdul
 *
 * @param <Resource>
 */
public class FourTPredicateServiceImpl<Resource> implements FourTPredicateService<Resource> {

	public FourTPredicateServiceImpl() {
	}

	@Override
	public List<Predicate> generateCriteriaPredicates(List<FourTCriteria<Resource>> criteriaList) {
		List<Predicate> allPredicates = Lists.newArrayList();
		if (!isEmpty(criteriaList)) {
			for (FourTCriteria<Resource> fourTCriteria : criteriaList) {
				allPredicates.add(fourTCriteria.applyCriteria());
			}
		}
		return allPredicates;
	}

	@Override
	public Predicate applyAndOnPredicates(CriteriaBuilder criteriaBuilder, List<Predicate> allPredicates) {
		Predicate[] predicates = allPredicates.toArray(new Predicate[allPredicates.size()]);
		return criteriaBuilder.and(predicates);
	}

}
