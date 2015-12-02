package com.abuabdul.fourt.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;

/**
 * @author abuabdul
 *
 */
public interface FourTReadOnlyDAO<E, K extends Serializable> {

	public EntityManager getEntityManager();

	public List<E> listAll();

	public E find(K key);

}
