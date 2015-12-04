package com.abuabdul.fourt.data.exporter;

import java.util.List;

import com.abuabdul.fourt.exception.FourTServiceException;
import com.abuabdul.fourt.service.FourTService;

/**
 * @author abuabdul
 *
 * @param <T>
 */
public interface FourTFileExporter<U> {

	void setExportType();

	boolean isEmptyInput();

	void handleEmptyInput() throws FourTServiceException;

	List<U> fetchResults(FourTService fourTService) throws FourTServiceException;

	void writeIfNoResults() throws FourTServiceException;

	void writeFetchResults(List<U> fetchResults) throws FourTServiceException;

}
