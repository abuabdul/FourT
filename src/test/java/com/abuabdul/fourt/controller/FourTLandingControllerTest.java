package com.abuabdul.fourt.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.abuabdul.fourt.criteria.result.FourTResultCriteria;
import com.abuabdul.fourt.data.exporter.FourTFileWriterService;
import com.abuabdul.fourt.domain.Resource;
import com.abuabdul.fourt.domain.TaskDetail;
import com.abuabdul.fourt.model.ResourceTask;
import com.abuabdul.fourt.model.ResourceTaskDetail;
import com.abuabdul.fourt.model.converter.FourTConverter;
import com.abuabdul.fourt.service.FourTVetoService;

@RunWith(MockitoJUnitRunner.class)
public class FourTLandingControllerTest {

	@Mock
	private FourTConverter<ResourceTask, Resource, TaskDetail, ResourceTaskDetail> fourTConverter;

	@Mock
	private FourTVetoService fourTVetoService;

	@Mock
	private FourTResultCriteria fourTResultCriteria;

	@Mock
	private FourTFileWriterService<ResourceTaskDetail> fourTCSVFileWriterService;

	@Mock
	private FourTFileWriterService<ResourceTaskDetail> fourTTextFileWriterService;

	@InjectMocks
	private FourTLandingController fourTLandingController;

	private MockMvc mockMvc;

	@Before
	public void init() {
		mockMvc = standaloneSetup(this.fourTLandingController).build();
	}

	@Test
	public void testLandingPage() throws Exception {
		mockMvc.perform(post("/landing/fourTOverview.go")).andExpect(status().isOk())
				.andExpect(model().attributeExists("resourceTaskTrackerForm")).andExpect(view().name("landingPage"));
	}

	@Test
	public void testSaveResourceTaskDetails() throws Exception {
		mockMvc.perform(
				post("/secure/resource/taskTracker.go").sessionAttr("resourceTaskTrackerForm", new ResourceTask()))
				.andExpect(status().isOk()).andExpect(model().attributeExists("resourceTaskTrackerForm"))
				.andExpect(model().attribute("saveTaskDetails", true)).andExpect(view().name("landingPage"));
	}

	@Test
	public void testViewTasks() throws Exception {
		mockMvc.perform(post("/landing/fourTViewResults.go")).andExpect(status().isOk())
				.andExpect(model().attributeExists("resourceTaskDetailForm"))
				.andExpect(model().attribute("viewTasksLanding", true)).andExpect(view().name("viewTasks"));
	}

	@Test
	public void testViewTaskResults() throws Exception {
		mockMvc.perform(post("/secure/resource/viewTaskDetailResults.go").sessionAttr("resourceTaskDetailForm",
				new ResourceTaskDetail())).andExpect(status().isOk())
				.andExpect(model().attributeExists("resourceTaskDetails"))
				.andExpect(model().attributeExists("resourceTaskDetailForm")).andExpect(view().name("viewTasks"));
	}

	@Test
	public void testExportTaskResultToExcel() throws Exception {
		mockMvc.perform(post("/secure/taskdetails/fourTExportToExcel.go").sessionAttr("resourceTaskDetailForm",
				new ResourceTaskDetail())).andExpect(status().isOk());
	}

	@Test
	public void testCustomView() throws Exception {
		mockMvc.perform(post("/landing/fourTCustomView.go")).andExpect(status().isOk())
				.andExpect(model().attributeExists("resourceTaskDetailForm")).andExpect(view().name("customView"));
	}

	@Test
	public void testCustomViewTaskDetails() throws Exception {
		mockMvc.perform(post("/secure/resource/viewCustomTaskDetails.go").sessionAttr("resourceTaskDetailForm",
				new ResourceTaskDetail())).andExpect(status().isOk());
	}
}
