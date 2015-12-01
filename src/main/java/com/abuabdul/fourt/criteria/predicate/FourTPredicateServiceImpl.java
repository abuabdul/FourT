package com.abuabdul.fourt.criteria.predicate;

import static org.springframework.util.CollectionUtils.isEmpty;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.abuabdul.fourt.criteria.FourTCriteria;
import com.abuabdul.fourt.domain.TaskDetail;
import com.google.common.collect.Lists;

/**
 * @author abuabdul
 *
 * @param <Resource>
 */
public class FourTPredicateServiceImpl implements FourTPredicateService<TaskDetail> {

	public FourTPredicateServiceImpl() {
	}

	@Override
	public List<Predicate> generateCriteriaPredicates(List<FourTCriteria<TaskDetail>> criteriaList) {
		List<Predicate> allPredicates = Lists.newArrayList();
		if (!isEmpty(criteriaList)) {
			for (FourTCriteria<TaskDetail> fourTCriteria : criteriaList) {
				allPredicates.add(fourTCriteria.applyCriteria());
			}
		}
		return allPredicates;
	}

	@Override
	public Predicate applyOROnPredicates(CriteriaBuilder criteriaBuilder, List<Predicate> allPredicates) {
		Predicate[] predicates = allPredicates.toArray(new Predicate[allPredicates.size()]);
		return criteriaBuilder.or(predicates);
	}

	@Override
	public CriteriaQuery<TaskDetail> whereClauseAndSelect(CriteriaQuery<TaskDetail> criteria, Predicate finalPredicate,
			Root<TaskDetail> root) {
		criteria.where(finalPredicate);
		criteria.select(root);
		return criteria;
	}

}
