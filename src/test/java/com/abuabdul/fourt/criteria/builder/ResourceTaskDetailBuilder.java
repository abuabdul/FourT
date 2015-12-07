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
