package com.abuabdul.fourt.data.exporter;

import static org.apache.commons.lang3.StringUtils.isEmpty;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.NoSuchMessageException;
import org.springframework.web.servlet.support.RequestContext;

import com.abuabdul.fourt.exception.FourTServiceException;
import com.abuabdul.fourt.model.ResourceTaskDetail;
import com.abuabdul.fourt.service.FourTReadOnlyService;
import com.abuabdul.fourt.service.FourTService;

/**
 * @author abuabdul
 *
 */
public class FourTPlainTextDataFileExporter implements FourTFileExporter<Object[]> {

	private final static String TxtFileName = "custom.view.file.name";

	private final static String EmptyCustomQueryString = "empty.custom.query.message";

	private final static String NoResultString = "no.custom.result.message";

	protected final RequestContext requestContext;
	protected final HttpServletResponse response;
	protected final ResourceTaskDetail taskDetail;

	public FourTPlainTextDataFileExporter(HttpServletRequest request, HttpServletResponse response,
			ResourceTaskDetail taskDetail) {
		this.requestContext = new RequestContext(request);
		this.response = response;
		this.taskDetail = taskDetail;
	}

	@Override
	public void setExportType() {
		response.setContentType("text/plain");
		response.setHeader("Content-Disposition",
				String.format("attachment; filename=\"%s\"", requestContext.getMessage(TxtFileName)));
		response.setCharacterEncoding("UTF-8");
	}

	@Override
	public boolean isEmptyInput() {
		return isEmpty(taskDetail.getCustomQuery());
	}

	@Override
	public void handleEmptyInput() throws FourTServiceException {
		try {
			response.getWriter().write(requestContext.getMessage(EmptyCustomQueryString));
		} catch (NoSuchMessageException | IOException e) {
			throw new FourTServiceException("FourTServiceException - " + e.getMessage());
		}
	}

	@Override
	public List<Object[]> fetchResults(FourTService fourTService) throws FourTServiceException {
		return ((FourTReadOnlyService) fourTService).findCustomTaskResults(taskDetail.getCustomQuery());
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
	public void writeFetchResults(List<Object[]> fetchResults) throws FourTServiceException {
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
