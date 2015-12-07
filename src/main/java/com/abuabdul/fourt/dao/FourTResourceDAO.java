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

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import com.abuabdul.fourt.domain.Resource;
import com.abuabdul.fourt.exception.FourTServiceException;

/**
 * @author abuabdul
 *
 */
public interface FourTResourceDAO {

	public EntityManager getEntityManager();

	public void saveResource(Resource resource) throws FourTServiceException;

	public List<Resource> findAllResources() throws FourTServiceException;

	public List<Resource> findResourceByName(String resourceName) throws FourTServiceException;

	public List<Resource> findResourceByTaskDate(Date taskDate) throws FourTServiceException;

	public List<Resource> findResourceByTaskStatus(String taskStatus) throws FourTServiceException;

	public List<Resource> findResourceByTaskDuration(Float taskDuration) throws FourTServiceException;

	public List<Resource> findResourceByTaskDurationLessThan(Float taskDuration) throws FourTServiceException;

	public List<Resource> findResourceByTaskDurationGreaterThan(Float taskDuration) throws FourTServiceException;

}
