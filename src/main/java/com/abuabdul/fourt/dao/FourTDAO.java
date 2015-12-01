package com.abuabdul.fourt.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;

/**
 * @author abuabdul
 *
 */
public interface FourTDAO<E, K extends Serializable> {

	public EntityManager getEntityManager();

	public List<E> listAll();

	public void save(E entity);

	public E find(K key);

	public void update(E entity);

	public boolean delete(E entity);

}
