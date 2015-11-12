package com.abuabdul.fourt.model.converter.impl;

import static com.abuabdul.fourt.util.FourTUtils.getHyphensInDateFormat;
import static com.abuabdul.fourt.util.FourTUtils.getUTCDateTime;
import static com.abuabdul.fourt.util.FourTUtils.simpleDateWithDDMMYYYY;

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
 * @author abuabdul
 *
 */
@Component
public class FourTConverterServiceImpl implements FourTConverter<ResourceTask, Resource> {

	@Override
	public Resource convert(ResourceTask resourceTask) throws FourTException {
		Resource resource = new Resource();
		if (resourceTask != null) {
			//resource.setId(new Double(Math.random()).longValue());
			resource.setName(resourceTask.getResourceName());
			resource.setTaskDate(getUTCDateTime());//simpleDateWithDDMMYYYY(getHyphensInDateFormat(resourceTask.getTaskDate())));
			resource.setCreatedBy(resourceTask.getResourceName());
			resource.setCreatedDate(getUTCDateTime());
			resource.setUpdatedBy(resourceTask.getResourceName());
			resource.setUpdatedDate(getUTCDateTime());
			List<TaskDetail> taskDetailList = Lists.newArrayList();
			for (ResourceTaskDetail restaskDetail : resourceTask.getTaskDetailList()) {
				TaskDetail taskDetail = new TaskDetail();
				//taskDetail.setId(new Double(Math.random()).longValue());
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

}
