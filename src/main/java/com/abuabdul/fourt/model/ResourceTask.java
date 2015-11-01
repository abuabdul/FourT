package com.abuabdul.fourt.model;

import java.util.List;

import com.google.common.collect.Lists;

/**
 * @author abuabdul
 *
 */
public class ResourceTask {

	private String resourceName;
	private String taskDate;
	private List<TaskDetail> taskDetailList = Lists.newArrayList();

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getTaskDate() {
		return taskDate;
	}

	public void setTaskDate(String taskDate) {
		this.taskDate = taskDate;
	}

	public List<TaskDetail> getTaskDetailList() {
		return taskDetailList;
	}

	public void setTaskDetailList(List<TaskDetail> taskDetailList) {
		this.taskDetailList = taskDetailList;
	}

}
