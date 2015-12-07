/*
 * Copyright 2013-2016 abuabdul.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */
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
