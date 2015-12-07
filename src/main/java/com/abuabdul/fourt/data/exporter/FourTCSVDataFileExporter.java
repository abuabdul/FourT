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
package com.abuabdul.fourt.data.exporter;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.NoSuchMessageException;
import org.springframework.web.servlet.support.RequestContext;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.abuabdul.fourt.criteria.result.FourTResultCriteria;
import com.abuabdul.fourt.domain.Resource;
import com.abuabdul.fourt.domain.TaskDetail;
import com.abuabdul.fourt.exception.FourTServiceException;
import com.abuabdul.fourt.model.ResourceTask;
import com.abuabdul.fourt.model.ResourceTaskDetail;
import com.abuabdul.fourt.model.converter.FourTConverter;

/**
 * @author abuabdul
 *
 */
public class FourTCSVDataFileExporter implements FourTFileExporter<ResourceTaskDetail, TaskDetail> {

	private final static String ExcelFileName = "export.excel.file.name";

	private final static String NoResultString = "no.custom.result.message";

	private final FourTConverter<ResourceTask, Resource, TaskDetail, ResourceTaskDetail> fourTConverter;

	private final FourTResultCriteria fourTResultCriteria;

	public FourTCSVDataFileExporter(FourTResultCriteria fourTResultCriteria,
			FourTConverter<ResourceTask, Resource, TaskDetail, ResourceTaskDetail> fourTConverter) {
		this.fourTResultCriteria = fourTResultCriteria;
		this.fourTConverter = fourTConverter;
	}

	@Override
	public void setExportType(HttpServletResponse response, RequestContext context) {
		response.setContentType("text/csv");
		response.setHeader("Content-Disposition",
				String.format("attachment; filename=\"%s\"", context.getMessage(ExcelFileName)));
		response.setCharacterEncoding("UTF-8");
	}

	@Override
	public boolean isEmptyInput(ResourceTaskDetail taskDetail) {
		return false;
	}

	@Override
	public void handleEmptyInput(HttpServletResponse response, RequestContext context) throws FourTServiceException {
	}

	@Override
	public List<TaskDetail> fetchResults(ResourceTaskDetail taskDetail) throws FourTServiceException {
		return fourTResultCriteria.findTasksBasedOn(taskDetail);
	}

	@Override
	public void writeIfNoResults(HttpServletResponse response, RequestContext context) throws FourTServiceException {
		try {
			response.getWriter().write(context.getMessage(NoResultString));
		} catch (NoSuchMessageException | IOException e) {
			throw new FourTServiceException("FourTServiceException - " + e.getMessage());
		}
	}

	@Override
	public void writeFetchResults(HttpServletResponse response, List<TaskDetail> fetchResults)
			throws FourTServiceException {
		try {
			List<ResourceTaskDetail> resourceTaskDetails = fourTConverter.convert(fetchResults);
			String[] header = { "Task Date", "Resource Name", "Task Description", "Task Duration", "Task Status" };
			String[] headerMapping = { "TaskDate", "ResourceName", "TaskDesc", "Duration", "Status" };
			ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
			csvWriter.writeHeader(header);
			for (ResourceTaskDetail resourceTaskDetail : resourceTaskDetails) {
				csvWriter.write(resourceTaskDetail, headerMapping);
			}
			csvWriter.close();
		} catch (IOException e) {
			throw new FourTServiceException("FourTServiceException - " + e.getMessage());
		}
	}

}
