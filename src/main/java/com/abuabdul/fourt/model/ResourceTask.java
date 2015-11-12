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
	private List<ResourceTaskDetail> taskDetailList = Lists.newArrayList();
	
	//Custom View Query Textbox
	private String customQuery;

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

	public List<ResourceTaskDetail> getTaskDetailList() {
		return taskDetailList;
	}

	public void setTaskDetailList(List<ResourceTaskDetail> taskDetailList) {
		this.taskDetailList = taskDetailList;
	}

	public String getCustomQuery() {
		return customQuery;
	}

	public void setCustomQuery(String customQuery) {
		this.customQuery = customQuery;
	}

}
