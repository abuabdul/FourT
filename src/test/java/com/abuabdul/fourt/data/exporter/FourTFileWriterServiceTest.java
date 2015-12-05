package com.abuabdul.fourt.data.exporter;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.collections.Lists;

import com.abuabdul.fourt.domain.TaskDetail;
import com.abuabdul.fourt.exception.FourTServiceException;
import com.abuabdul.fourt.service.FourTService;

/**
 * @author abuabdul
 *
 */
public class FourTFileWriterServiceTest {

	@Mock
	FourTFileExporter<TaskDetail> fileExporter;

	@Mock
	private FourTService fourTService;

	@BeforeMethod
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}

	@Test(groups = "integration")
	public void testExportDataAsCSVFile() throws FourTServiceException {
		List<TaskDetail> fetchResults = Lists.newArrayList();

		when(fileExporter.isEmptyInput()).thenReturn(false);
		when(fileExporter.fetchResults(fourTService)).thenReturn(fetchResults);

		FourTFileWriterService fourTFileWriterService = new FourTFileWriterServiceImpl<TaskDetail>(fileExporter,
				fourTService);
		fourTFileWriterService.exportDataAsFile();

		verify(fileExporter, times(1)).setExportType();
		verify(fileExporter, times(1)).isEmptyInput();
		verify(fileExporter, never()).handleEmptyInput();
		verify(fileExporter, times(1)).fetchResults(fourTService);
		verify(fileExporter, times(1)).writeIfNoResults();
		verify(fileExporter, never()).writeFetchResults(fetchResults);
	}

	@Test(groups = "integration")
	public void testExportDataAsCSVFileWithResults() throws FourTServiceException {
		List<TaskDetail> fetchResults = Lists.newArrayList();
		TaskDetail taskDetail = new TaskDetail();
		fetchResults.add(taskDetail);

		when(fileExporter.isEmptyInput()).thenReturn(false);
		when(fileExporter.fetchResults(fourTService)).thenReturn(fetchResults);

		FourTFileWriterService fourTFileWriterService = new FourTFileWriterServiceImpl<TaskDetail>(fileExporter,
				fourTService);
		fourTFileWriterService.exportDataAsFile();

		verify(fileExporter, times(1)).setExportType();
		verify(fileExporter, times(1)).isEmptyInput();
		verify(fileExporter, never()).handleEmptyInput();
		verify(fileExporter, times(1)).fetchResults(fourTService);
		verify(fileExporter, never()).writeIfNoResults();
		verify(fileExporter, times(1)).writeFetchResults(fetchResults);
	}

	@Test(groups = "integration")
	public void testExportEmptyDataAsCSVFile() throws FourTServiceException {
		List<TaskDetail> fetchResults = Lists.newArrayList();

		when(fileExporter.isEmptyInput()).thenReturn(true);

		FourTFileWriterService fourTFileWriterService = new FourTFileWriterServiceImpl<TaskDetail>(fileExporter,
				fourTService);
		fourTFileWriterService.exportDataAsFile();

		verify(fileExporter, times(1)).setExportType();
		verify(fileExporter, times(1)).isEmptyInput();
		verify(fileExporter, times(1)).handleEmptyInput();
		verify(fileExporter, never()).fetchResults(fourTService);
		verify(fileExporter, never()).writeIfNoResults();
		verify(fileExporter, never()).writeFetchResults(fetchResults);
	}
}
