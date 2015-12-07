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
package com.abuabdul.fourt.model.converter;

import static com.abuabdul.fourt.util.FourTUtils.getUTCDateTime;
import static com.abuabdul.fourt.util.FourTUtils.simpleDateStringWithDDMMYYYY;
import static com.abuabdul.fourt.util.FourTUtils.simpleDateWithDDMMYYYY;
import static org.springframework.util.CollectionUtils.isEmpty;

import java.util.List;

import com.abuabdul.fourt.domain.Resource;
import com.abuabdul.fourt.domain.TaskDetail;
import com.abuabdul.fourt.exception.FourTException;
import com.abuabdul.fourt.model.ResourceTask;
import com.abuabdul.fourt.model.ResourceTaskDetail;
import com.google.common.collect.Lists;

/**
 * 
 * Converter that converts Resource Task Details to Resource entity
 * 
 * @author abuabdul
 *
 */
public class FourTConverterService implements FourTConverter<ResourceTask, Resource, TaskDetail, ResourceTaskDetail> {

	public FourTConverterService() {
	}

	@Override
	public Resource convert(ResourceTask resourceTask) throws FourTException {
		Resource resource = new Resource();
		if (resourceTask != null) {
			resource.setName(resourceTask.getResourceName());
			resource.setTaskDate(simpleDateWithDDMMYYYY(resourceTask.getTaskDate()));
			resource.setCreatedBy(resourceTask.getResourceName());
			resource.setCreatedDate(getUTCDateTime());
			resource.setUpdatedBy(resourceTask.getResourceName());
			resource.setUpdatedDate(getUTCDateTime());
			List<TaskDetail> taskDetailList = Lists.newArrayList();
			for (ResourceTaskDetail restaskDetail : resourceTask.getTaskDetailList()) {
				TaskDetail taskDetail = new TaskDetail();
				taskDetail.setResource(resource);
				taskDetail.setDuration(Float.valueOf(restaskDetail.getDuration()));
				taskDetail.setStatus(restaskDetail.getStatus());
				taskDetail.setTaskDesc(restaskDetail.getTaskDesc());
				taskDetailList.add(taskDetail);
			}
			resource.setTaskDetailList(taskDetailList);
		}
		return resource;
	}

	@Override
	public List<ResourceTaskDetail> convert(List<TaskDetail> taskDetails) throws FourTException {
		List<ResourceTaskDetail> resourceTaskDetails = Lists.newArrayList();
		if (!isEmpty(taskDetails)) {
			for (TaskDetail savedTaskDetail : taskDetails) {
				ResourceTaskDetail viewTaskDtl = new ResourceTaskDetail();
				viewTaskDtl.setResourceName(savedTaskDetail.getResource().getName());
				viewTaskDtl.setTaskDate(simpleDateStringWithDDMMYYYY(savedTaskDetail.getResource().getTaskDate()));
				viewTaskDtl.setTaskDesc(savedTaskDetail.getTaskDesc());
				viewTaskDtl.setDuration(savedTaskDetail.getDuration().toString());
				viewTaskDtl.setStatus(savedTaskDetail.getStatus());
				resourceTaskDetails.add(viewTaskDtl);
			}
		}
		return resourceTaskDetails;
	}

}
