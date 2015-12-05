package com.abuabdul.fourt.data.exporter;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.NoSuchMessageException;
import org.springframework.web.servlet.support.RequestContext;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.abuabdul.fourt.criteria.result.FourTResultCriteriaService;
import com.abuabdul.fourt.domain.Resource;
import com.abuabdul.fourt.domain.TaskDetail;
import com.abuabdul.fourt.exception.FourTServiceException;
import com.abuabdul.fourt.model.ResourceTask;
import com.abuabdul.fourt.model.ResourceTaskDetail;
import com.abuabdul.fourt.model.converter.FourTConverter;
import com.abuabdul.fourt.service.FourTService;
import com.abuabdul.fourt.service.FourTVetoService;

/**
 * @author abuabdul
 *
 */
public class FourTCSVDataFileExporter implements FourTFileExporter<TaskDetail> {

	private final static String ExcelFileName = "export.excel.file.name";

	private final static String NoResultString = "no.custom.result.message";

	private FourTConverter<ResourceTask, Resource, TaskDetail, ResourceTaskDetail> fourTConverter;
	protected final RequestContext requestContext;
	protected final HttpServletResponse response;
	protected final ResourceTaskDetail taskDetail;

	public FourTCSVDataFileExporter(HttpServletRequest request, HttpServletResponse response,
			ResourceTaskDetail taskDetail) {
		this.requestContext = new RequestContext(request);
		this.response = response;
		this.taskDetail = taskDetail;
	}

	public FourTCSVDataFileExporter withFourTConverter(
			FourTConverter<ResourceTask, Resource, TaskDetail, ResourceTaskDetail> fourTConverter) {
		this.fourTConverter = fourTConverter;
		return this;
	}
	
	@Override
	public void setExportType() {
		response.setContentType("text/csv");
		response.setHeader("Content-Disposition",
				String.format("attachment; filename=\"%s\"", requestContext.getMessage(ExcelFileName)));
		response.setCharacterEncoding("UTF-8");
	}

	@Override
	public boolean isEmptyInput() {
		return false;
	}

	@Override
	public void handleEmptyInput() throws FourTServiceException {
	}

	@Override
	public List<TaskDetail> fetchResults(FourTService fourTService) throws FourTServiceException {
		return new FourTResultCriteriaService((FourTVetoService) fourTService).findTasksBasedOn(taskDetail);
	}

	@Override
	public void writeIfNoResults() throws FourTServiceException {
		try {
			response.getWriter().write(requestContext.getMessage(NoResultString));
		} catch (NoSuchMessageException | IOException e) {
			throw new FourTServiceException("FourTServiceException - " + e.getMessage());
		}
	}

	@Override
	public void writeFetchResults(List<TaskDetail> fetchResults) throws FourTServiceException {
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
