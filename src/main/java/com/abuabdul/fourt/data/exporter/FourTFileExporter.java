package com.abuabdul.fourt.data.exporter;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.support.RequestContext;

import com.abuabdul.fourt.exception.FourTServiceException;

/**
 * @author abuabdul
 *
 * @param <T>
 */
public interface FourTFileExporter<U, V> {

	void setExportType(HttpServletResponse response, RequestContext context);

	boolean isEmptyInput(U u);

	void handleEmptyInput(HttpServletResponse response, RequestContext context) throws FourTServiceException;

	List<V> fetchResults(U u) throws FourTServiceException;

	void writeIfNoResults(HttpServletResponse response, RequestContext context) throws FourTServiceException;

	void writeFetchResults(HttpServletResponse response, List<V> fetchResults) throws FourTServiceException;

}
