package com.abuabdul.fourt.data.exporter;

import static org.springframework.util.CollectionUtils.isEmpty;

import java.util.List;

import com.abuabdul.fourt.exception.FourTServiceException;
import com.abuabdul.fourt.service.FourTService;

/**
 * @author abuabdul
 *
 */
public class FourTFileWriterServiceImpl<U> implements FourTFileWriterService {

	private final FourTFileExporter<U> fileExporter;
	private final FourTService fourTService;

	public FourTFileWriterServiceImpl(FourTFileExporter<U> fileExporter, FourTService fourTService) {
		this.fileExporter = fileExporter;
		this.fourTService = fourTService;
	}

	@Override
	public void exportDataAsFile() throws FourTServiceException {
		fileExporter.setExportType();

		if (fileExporter.isEmptyInput()) {
			fileExporter.handleEmptyInput();
			return;
		}

		List<U> fetchResults = fileExporter.fetchResults(fourTService);
		if (isEmpty(fetchResults)) {
			fileExporter.writeIfNoResults();
			return;
		}

		fileExporter.writeFetchResults(fetchResults);
	}
}
