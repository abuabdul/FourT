package com.abuabdul.fourt.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author abuabdul
 *
 * @param <E>
 * @param <K>
 */
public abstract class FourTReadOnlyBaseDAO<E, K extends Serializable> implements FourTReadOnlyDAO<E, K> {

	protected final Class<? extends E> genericClassType;
	
	@PersistenceContext(unitName="readOnly")
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public FourTReadOnlyBaseDAO() {
		genericClassType = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<E> listAll() {
		return (List<E>) entityManager.createQuery("from " + genericClassType.getName()).getResultList();
	}

	@Override
	public E find(K key) {
		return entityManager.find(genericClassType, key);
	}

	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}

}
