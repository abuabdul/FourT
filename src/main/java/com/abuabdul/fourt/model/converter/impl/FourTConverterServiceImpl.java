package com.abuabdul.fourt.model.converter.impl;

import static com.abuabdul.fourt.util.FourTUtils.getUTCDateTime;
import static com.abuabdul.fourt.util.FourTUtils.simpleDateStringWithDDMMYYYY;
import static com.abuabdul.fourt.util.FourTUtils.simpleDateWithDDMMYYYY;
import static org.springframework.util.CollectionUtils.isEmpty;

import java.util.List;

import org.springframework.stereotype.Component;

import com.abuabdul.fourt.domain.Resource;
import com.abuabdul.fourt.domain.TaskDetail;
import com.abuabdul.fourt.exception.FourTException;
import com.abuabdul.fourt.model.ResourceTask;
import com.abuabdul.fourt.model.ResourceTaskDetail;
import com.abuabdul.fourt.model.converter.FourTConverter;
import com.google.common.collect.Lists;

/**
 * 
 * Converter that converts Resource Task Details to Resource entity
 * 
 * @author abuabdul
 *
 */
@Component
public class FourTConverterServiceImpl
		implements FourTConverter<ResourceTask, Resource, TaskDetail, ResourceTaskDetail> {

	public FourTConverterServiceImpl() {
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
