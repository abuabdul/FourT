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

}
