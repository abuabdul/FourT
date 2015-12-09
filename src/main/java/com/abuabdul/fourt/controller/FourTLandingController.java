/*
 * Copyright 2013-2016 abuabdul.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */
package com.abuabdul.fourt.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hsqldb.util.DatabaseManagerSwing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.RequestContext;

import com.abuabdul.fourt.criteria.result.FourTResultCriteria;
import com.abuabdul.fourt.data.exporter.FourTFileWriterService;
import com.abuabdul.fourt.db.manager.service.FourTDBManager;
import com.abuabdul.fourt.db.manager.service.FourTDBManagerService;
import com.abuabdul.fourt.domain.Resource;
import com.abuabdul.fourt.domain.TaskDetail;
import com.abuabdul.fourt.exception.FourTException;
import com.abuabdul.fourt.exception.FourTServiceException;
import com.abuabdul.fourt.model.ResourceTask;
import com.abuabdul.fourt.model.ResourceTaskDetail;
import com.abuabdul.fourt.model.converter.FourTConverter;
import com.abuabdul.fourt.service.FourTVetoService;

/**
 * @author abuabdul
 *
 */
@Controller
public class FourTLandingController extends FourTAbstractController {

	// Define a static logger variable so that it references the
	// Logger instance named "FourTLandingController".
	private static final Logger log = LogManager.getLogger(FourTLandingController.class.getName());

	@Autowired
	private FourTConverter<ResourceTask, Resource, TaskDetail, ResourceTaskDetail> fourTConverter;

	@Autowired
	private FourTVetoService fourTVetoService;

	@Autowired
	private FourTResultCriteria fourTResultCriteria;

	@Autowired
	private FourTFileWriterService<ResourceTaskDetail> fourTCSVFileWriterService;

	@Autowired
	private FourTFileWriterService<ResourceTaskDetail> fourTTextFileWriterService;

	@RequestMapping(value = "/landing/fourTOverview.go")
	public String landingPage(ModelMap model, HttpSession session) {
		log.debug("Entering landingPage() in the FourTLandingController");
		super.bootstrapRefData(session);
		model.addAttribute("resourceTaskTrackerForm", new ResourceTask());
		return "landingPage";
	}

	@RequestMapping(value = "/secure/resource/taskTracker.go")
	public String saveResourceTaskDetails(@ModelAttribute("resourceTaskTrackerForm") ResourceTask resourceTask,
			ModelMap model) {
		log.debug("Entering saveResourceTaskDetails() in the FourTLandingController");
		try {
			Resource resource = fourTConverter.convert(resourceTask);
			fourTVetoService.saveResourceTaskDetails(resource);
			model.addAttribute("resourceTaskTrackerForm", new ResourceTask());
			model.addAttribute("saveTaskDetails", true);
			return "landingPage";
		} catch (FourTServiceException fse) {
			log.debug("FourTServiceException - " + fse.getMessage());
			throw new FourTException(fse.getMessage());
		}
	}

	@RequestMapping(value = "/landing/fourTViewResults.go")
	public String viewTasks(ModelMap model) {
		log.debug("Entering viewTasks() in the FourTLandingController");
		model.addAttribute("resourceTaskDetailForm", new ResourceTaskDetail());
		model.addAttribute("viewTasksLanding", true);
		return "viewTasks";
	}

	@RequestMapping(value = "/secure/resource/viewTaskDetailResults.go")
	public String viewTaskResults(@ModelAttribute("resourceTaskDetailForm") ResourceTaskDetail resourceTaskDtl,
			ModelMap model) {
		log.debug("Entering viewTaskResults() in the FourTLandingController");
		try {
			List<TaskDetail> savedTaskDetail = fourTResultCriteria.findTasksBasedOn(resourceTaskDtl);
			List<ResourceTaskDetail> resourceTaskDetails = fourTConverter.convert(savedTaskDetail);
			model.addAttribute("resourceTaskDetails", resourceTaskDetails);
			model.addAttribute("resourceTaskDetailForm", resourceTaskDtl);
			return "viewTasks";
		} catch (FourTServiceException fse) {
			log.debug("FourTServiceException - " + fse.getMessage());
			throw new FourTException(fse.getMessage());
		}
	}

	@RequestMapping(value = "/secure/taskdetails/fourTExportToExcel.go")
	public void exportTaskResultToExcel(@ModelAttribute("resourceTaskDetailForm") ResourceTaskDetail resourceTaskDtl,
			HttpServletRequest request, HttpServletResponse response) {
		log.debug("Entering exportTaskResultToExcel() in the FourTLandingController");
		try {
			fourTCSVFileWriterService.exportDataAsFile(new RequestContext(request), response, resourceTaskDtl);
		} catch (FourTServiceException fse) {
			log.debug("FourTServiceException - " + fse.getMessage());
			throw new FourTException(fse.getMessage());
		}
	}

	@RequestMapping(value = "/landing/fourTCustomView.go")
	public String customView(ModelMap model) {
		log.debug("Entering customView() in the FourTLandingController");
		model.addAttribute("resourceTaskDetailForm", new ResourceTaskDetail());
		return "customView";
	}

	@RequestMapping(value = "/secure/resource/viewCustomTaskDetails.go")
	public void customViewTaskDetails(@ModelAttribute("resourceTaskDetailForm") ResourceTaskDetail resourceTaskDtl,
			HttpServletRequest request, HttpServletResponse response) {
		log.debug("Entering customViewTaskDetails() in the FourTLandingController");
		try {
			fourTTextFileWriterService.exportDataAsFile(new RequestContext(request), response, resourceTaskDtl);
		} catch (FourTServiceException fse) {
			log.debug("FourTServiceException - " + fse.getMessage());
			throw new FourTException(
					"FourTServiceException - Some error occurred. Please note: Custom Query is set to execute read-only queries only");
		}
	}

	@RequestMapping(value = "/landing/fourTDBManagerTool.go")
	@Deprecated
	public void dbManagerTool(ModelMap model, HttpServletResponse response) {
		log.debug("Entering dbManagerTool() in the FourTLandingController");
		try {
			FourTDBManager fourTDBManager = new FourTDBManagerService(DatabaseManagerSwing.class, "main",
					new String[] { "--url", "jdbc:hsqldb:mem:fourtdb", "--user", "sa", "--password", "" });
			fourTDBManager.runDBManagerTool();
		} catch (FourTServiceException fse) {
			log.debug("FourTServiceException - " + fse.getMessage());
			throw new FourTException(fse.getMessage());
		}
	}
}
