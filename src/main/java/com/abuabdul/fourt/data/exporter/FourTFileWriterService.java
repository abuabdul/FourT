package com.abuabdul.fourt.data.exporter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.support.RequestContext;

import com.abuabdul.fourt.exception.FourTServiceException;

/**
 * @author abuabdul
 *
 */
public interface FourTFileWriterService<U> {

	void exportDataAsFile(RequestContext requestContext, HttpServletResponse response, U u)
			throws FourTServiceException;

}
