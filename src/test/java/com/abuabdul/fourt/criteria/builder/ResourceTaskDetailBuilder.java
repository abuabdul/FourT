package com.abuabdul.fourt.criteria.builder;

import com.abuabdul.fourt.model.ResourceTaskDetail;

/**
 * @author abuabdul
 *
 */
public class ResourceTaskDetailBuilder {

	private final ResourceTaskDetail resourceTaskDetail = new ResourceTaskDetail();

	public ResourceTaskDetailBuilder() {
	}

	public ResourceTaskDetailBuilder(String name, String taskDate, String duration, String status) {
		resourceTaskDetail.setResourceName(name);
		resourceTaskDetail.setTaskDate(taskDate);
		resourceTaskDetail.setDuration(duration);
		resourceTaskDetail.setStatus(status);
	}

	public ResourceTaskDetailBuilder withOnlyResourceNameCriteria(String name) {
		resourceTaskDetail.setResourceName(name);
		return this;
	}

	public ResourceTaskDetailBuilder withOnlyTaskDateCriteria(String taskDate) {
		resourceTaskDetail.setTaskDate(taskDate);
		return this;
	}

	public ResourceTaskDetailBuilder withOnlyDurationCriteria(String duration) {
		resourceTaskDetail.setDuration(duration);
		return this;
	}

	public ResourceTaskDetailBuilder withOnlyStatusCriteria(String status) {
		resourceTaskDetail.setStatus(status);
		return this;
	}

	public ResourceTaskDetail build() {
		return resourceTaskDetail;
	}
}
