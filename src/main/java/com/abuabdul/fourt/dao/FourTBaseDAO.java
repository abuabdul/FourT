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
public class FourTBaseDAO<E, K extends Serializable> implements FourTDAO<E, K> {

	protected final Class<? extends E> genericClassType;

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public FourTBaseDAO() {
		genericClassType = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<E> listAll() {
		return (List<E>) entityManager.createQuery("from " + genericClassType.getName()).getResultList();
	}

	@Override
	public void save(E entity) {
		entityManager.persist(entity);
	}

	@Override
	public E find(K key) {
		return entityManager.find(genericClassType, key);
	}

	@Override
	public void update(E entity) {
		entityManager.merge(entity);
	}

	@Override
	public boolean delete(E entity) {
		entityManager.remove(entity);
		return true;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}
