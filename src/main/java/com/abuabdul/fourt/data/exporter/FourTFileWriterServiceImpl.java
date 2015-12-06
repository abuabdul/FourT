package com.abuabdul.fourt.data.exporter;

import static org.springframework.util.CollectionUtils.isEmpty;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.support.RequestContext;

import com.abuabdul.fourt.exception.FourTServiceException;

/**
 * @author abuabdul
 *
 */
public class FourTFileWriterServiceImpl<U, V> implements FourTFileWriterService<U> {

	private final FourTFileExporter<U, V> fileExporter;

	public FourTFileWriterServiceImpl(FourTFileExporter<U, V> fileExporter) {
		this.fileExporter = fileExporter;
	}

	@Override
	public void exportDataAsFile(RequestContext context, HttpServletResponse response, U u)
			throws FourTServiceException {
		fileExporter.setExportType(response, context);

		if (fileExporter.isEmptyInput(u)) {
			fileExporter.handleEmptyInput(response, context);
			return;
		}

		List<V> fetchResults = fileExporter.fetchResults(u);
		if (isEmpty(fetchResults)) {
			fileExporter.writeIfNoResults(response, context);
			return;
		}

		fileExporter.writeFetchResults(response, fetchResults);
	}
}
