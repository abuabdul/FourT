package com.abuabdul.fourt.data.exporter;

import static org.apache.commons.lang3.StringUtils.isEmpty;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.NoSuchMessageException;
import org.springframework.web.servlet.support.RequestContext;

import com.abuabdul.fourt.exception.FourTServiceException;
import com.abuabdul.fourt.model.ResourceTaskDetail;
import com.abuabdul.fourt.service.FourTReadOnlyService;

/**
 * @author abuabdul
 *
 */
public class FourTPlainTextDataFileExporter implements FourTFileExporter<ResourceTaskDetail, Object[]> {

	private final static String TxtFileName = "custom.view.file.name";

	private final static String EmptyCustomQueryString = "empty.custom.query.message";

	private final static String NoResultString = "no.custom.result.message";

	private final FourTReadOnlyService fourTReadOnlyService;

	public FourTPlainTextDataFileExporter(FourTReadOnlyService fourTReadOnlyService) {
		this.fourTReadOnlyService = fourTReadOnlyService;
	}

	@Override
	public void setExportType(HttpServletResponse response, RequestContext context) {
		response.setContentType("text/plain");
		response.setHeader("Content-Disposition",
				String.format("attachment; filename=\"%s\"", context.getMessage(TxtFileName)));
		response.setCharacterEncoding("UTF-8");
	}

	@Override
	public boolean isEmptyInput(ResourceTaskDetail taskDetail) {
		return isEmpty(taskDetail.getCustomQuery());
	}

	@Override
	public void handleEmptyInput(HttpServletResponse response, RequestContext context) throws FourTServiceException {
		try {
			response.getWriter().write(context.getMessage(EmptyCustomQueryString));
		} catch (NoSuchMessageException | IOException e) {
			throw new FourTServiceException("FourTServiceException - " + e.getMessage());
		}
	}

	@Override
	public List<Object[]> fetchResults(ResourceTaskDetail taskDetail) throws FourTServiceException {
		return fourTReadOnlyService.findCustomTaskResults(taskDetail.getCustomQuery());
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
	public void writeFetchResults(HttpServletResponse response, List<Object[]> fetchResults)
			throws FourTServiceException {
		try {
			for (Object[] objects : fetchResults) {
				String row = "";
				for (Object obj : objects) {
					row = row.concat(obj.toString()).concat("\t");
				}
				response.getWriter().write(row);
				response.getWriter().println();
			}
		} catch (IOException e) {
			throw new FourTServiceException("FourTServiceException - " + e.getMessage());
		}
	}
}
