package com.abuabdul.fourt.data.exporter;

import com.abuabdul.fourt.exception.FourTServiceException;

/**
 * @author abuabdul
 *
 */
public interface FourTFileWriterService {

	void exportDataAsFile() throws FourTServiceException;

}
