package com.abuabdul.fourt.data.exporter;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.support.RequestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.collections.Lists;

import com.abuabdul.fourt.domain.TaskDetail;
import com.abuabdul.fourt.exception.FourTServiceException;
import com.abuabdul.fourt.model.ResourceTaskDetail;

/**
 * @author abuabdul
 *
 */
public class FourTFileWriterServiceTest {

	@Mock
	RequestContext context;

	@Mock
	MockHttpServletResponse response;

	@Mock
	private FourTFileExporter<ResourceTaskDetail, TaskDetail> csvfileExporter;

	@Mock
	private FourTFileExporter<ResourceTaskDetail, Object[]> textfileExporter;

	@BeforeMethod
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}

	@Test(groups = "integration")
	public void testExportDataAsCSVFile() throws FourTServiceException {
		ResourceTaskDetail taskDetail = new ResourceTaskDetail();
		List<TaskDetail> fetchResults = Lists.newArrayList();

		when(csvfileExporter.isEmptyInput(taskDetail)).thenReturn(false);
		when(csvfileExporter.fetchResults(taskDetail)).thenReturn(fetchResults);

		FourTFileWriterServiceImpl<ResourceTaskDetail, TaskDetail> fourTCSVFileWriterService = new FourTFileWriterServiceImpl<>(
				csvfileExporter);
		fourTCSVFileWriterService.exportDataAsFile(context, response, taskDetail);

		verify(csvfileExporter, times(1)).setExportType(response, context);
		verify(csvfileExporter, times(1)).isEmptyInput(taskDetail);
		verify(csvfileExporter, never()).handleEmptyInput(response, context);
		verify(csvfileExporter, times(1)).fetchResults(taskDetail);
		verify(csvfileExporter, times(1)).writeIfNoResults(response, context);
		verify(csvfileExporter, never()).writeFetchResults(response, fetchResults);
	}

	@Test(groups = "integration")
	public void testExportDataAsCSVFileWithResults() throws FourTServiceException {
		ResourceTaskDetail resourceTaskDetail = new ResourceTaskDetail();
		List<TaskDetail> fetchResults = Lists.newArrayList();
		TaskDetail taskDetail = new TaskDetail();
		fetchResults.add(taskDetail);

		when(csvfileExporter.isEmptyInput(resourceTaskDetail)).thenReturn(false);
		when(csvfileExporter.fetchResults(resourceTaskDetail)).thenReturn(fetchResults);

		FourTFileWriterServiceImpl<ResourceTaskDetail, TaskDetail> fourTCSVFileWriterService = new FourTFileWriterServiceImpl<>(
				csvfileExporter);
		fourTCSVFileWriterService.exportDataAsFile(context, response, resourceTaskDetail);

		verify(csvfileExporter, times(1)).setExportType(response, context);
		verify(csvfileExporter, times(1)).isEmptyInput(resourceTaskDetail);
		verify(csvfileExporter, never()).handleEmptyInput(response, context);
		verify(csvfileExporter, times(1)).fetchResults(resourceTaskDetail);
		verify(csvfileExporter, never()).writeIfNoResults(response, context);
		verify(csvfileExporter, times(1)).writeFetchResults(response, fetchResults);
	}

	@Test(groups = "integration")
	public void testExportEmptyDataAsCSVFile() throws FourTServiceException {
		ResourceTaskDetail taskDetail = new ResourceTaskDetail();
		List<TaskDetail> fetchResults = Lists.newArrayList();

		when(csvfileExporter.isEmptyInput(taskDetail)).thenReturn(true);

		FourTFileWriterServiceImpl<ResourceTaskDetail, TaskDetail> fourTCSVFileWriterService = new FourTFileWriterServiceImpl<>(
				csvfileExporter);
		fourTCSVFileWriterService.exportDataAsFile(context, response, taskDetail);

		verify(csvfileExporter, times(1)).setExportType(response, context);
		verify(csvfileExporter, times(1)).isEmptyInput(taskDetail);
		verify(csvfileExporter, times(1)).handleEmptyInput(response, context);
		verify(csvfileExporter, never()).fetchResults(taskDetail);
		verify(csvfileExporter, never()).writeIfNoResults(response, context);
		verify(csvfileExporter, never()).writeFetchResults(response, fetchResults);
	}

	@Test(groups = "integration")
	public void testExportDataAsTextFile() throws FourTServiceException {
		ResourceTaskDetail taskDetail = new ResourceTaskDetail();
		List<Object[]> fetchResults = Lists.newArrayList();

		when(textfileExporter.isEmptyInput(taskDetail)).thenReturn(false);
		when(textfileExporter.fetchResults(taskDetail)).thenReturn(fetchResults);

		FourTFileWriterServiceImpl<ResourceTaskDetail, Object[]> fourTCSVFileWriterService = new FourTFileWriterServiceImpl<>(
				textfileExporter);
		fourTCSVFileWriterService.exportDataAsFile(context, response, taskDetail);

		verify(textfileExporter, times(1)).setExportType(response, context);
		verify(textfileExporter, times(1)).isEmptyInput(taskDetail);
		verify(textfileExporter, never()).handleEmptyInput(response, context);
		verify(textfileExporter, times(1)).fetchResults(taskDetail);
		verify(textfileExporter, times(1)).writeIfNoResults(response, context);
		verify(textfileExporter, never()).writeFetchResults(response, fetchResults);
	}

	@Test(groups = "integration")
	public void testExportDataAsTextFileWithResults() throws FourTServiceException {
		ResourceTaskDetail resourceTaskDetail = new ResourceTaskDetail();
		List<Object[]> fetchResults = Lists.newArrayList();
		Object[] obj = new Object[0];
		fetchResults.add(obj);

		when(textfileExporter.isEmptyInput(resourceTaskDetail)).thenReturn(false);
		when(textfileExporter.fetchResults(resourceTaskDetail)).thenReturn(fetchResults);

		FourTFileWriterServiceImpl<ResourceTaskDetail, Object[]> fourTCSVFileWriterService = new FourTFileWriterServiceImpl<>(
				textfileExporter);
		fourTCSVFileWriterService.exportDataAsFile(context, response, resourceTaskDetail);

		verify(textfileExporter, times(1)).setExportType(response, context);
		verify(textfileExporter, times(1)).isEmptyInput(resourceTaskDetail);
		verify(textfileExporter, never()).handleEmptyInput(response, context);
		verify(textfileExporter, times(1)).fetchResults(resourceTaskDetail);
		verify(textfileExporter, never()).writeIfNoResults(response, context);
		verify(textfileExporter, times(1)).writeFetchResults(response, fetchResults);
	}

	@Test(groups = "integration")
	public void testExportEmptyDataAsTextFile() throws FourTServiceException {
		ResourceTaskDetail taskDetail = new ResourceTaskDetail();
		List<Object[]> fetchResults = Lists.newArrayList();

		when(textfileExporter.isEmptyInput(taskDetail)).thenReturn(true);

		FourTFileWriterServiceImpl<ResourceTaskDetail, Object[]> fourTCSVFileWriterService = new FourTFileWriterServiceImpl<>(
				textfileExporter);
		fourTCSVFileWriterService.exportDataAsFile(context, response, taskDetail);

		verify(textfileExporter, times(1)).setExportType(response, context);
		verify(textfileExporter, times(1)).isEmptyInput(taskDetail);
		verify(textfileExporter, times(1)).handleEmptyInput(response, context);
		verify(textfileExporter, never()).fetchResults(taskDetail);
		verify(textfileExporter, never()).writeIfNoResults(response, context);
		verify(textfileExporter, never()).writeFetchResults(response, fetchResults);
	}
}
