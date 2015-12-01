package com.abuabdul.fourt.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.abuabdul.fourt.criteria.result.FourTResultCriteriaService;
import com.abuabdul.fourt.domain.Resource;
import com.abuabdul.fourt.exception.FourTException;
import com.abuabdul.fourt.exception.FourTServiceException;
import com.abuabdul.fourt.model.ResourceTask;
import com.abuabdul.fourt.model.ResourceTaskDetail;
import com.abuabdul.fourt.model.converter.FourTConverter;
import com.abuabdul.fourt.service.FourTService;
import com.google.common.collect.Lists;

/**
 * @author abuabdul
 *
 */
@Controller
public class FourTLandingController {

	// Define a static logger variable so that it references the
	// Logger instance named "FourTLandingController".
	private static final Logger log = LogManager.getLogger(FourTLandingController.class.getName());

	@Autowired
	private FourTConverter<ResourceTask, ResourceTaskDetail, Resource> fourTConverter;

	@Autowired
	private FourTService fourTService;

	@Value("${custom.view.file.name}")
	private String txtFileName;

	@RequestMapping(value = "/landing/fourTOverview.go")
	public String landingPage(ModelMap model) {
		log.debug("Entering landingPage() in the FourTLandingController");
		model.addAttribute("resourceTaskTrackerForm", new ResourceTask());
		return "landingPage";
	}

	@RequestMapping(value = "/secure/resource/taskTracker.go")
	public String saveResourceTaskDetails(@ModelAttribute("resourceTaskTrackerForm") ResourceTask resourceTask,
			ModelMap model) {
		log.debug("Entering saveResourceTaskDetails() in the FourTLandingController");
		try {
			Resource resource = fourTConverter.convert(resourceTask);
			fourTService.saveResourceTaskDetails(resource);
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
			ModelMap model) throws IOException {
		log.debug("Entering viewTaskResults() in the FourTLandingController");
		try {
			List<Resource> resources = new FourTResultCriteriaService(fourTService).findTasksBasedOn(resourceTaskDtl);
			List<ResourceTaskDetail> resourceTaskDetails = fourTConverter.convert(resources);
			model.addAttribute("resourceTaskDetails", resourceTaskDetails);
			model.addAttribute("resourceTaskDetailForm", resourceTaskDtl);
		} catch (FourTServiceException fse) {
			log.debug("FourTServiceException - " + fse.getMessage());
			throw new FourTException(fse.getMessage());
		}
		return "viewTasks";
	}

	@RequestMapping(value = "/landing/fourTCustomView.go")
	public String customView(ModelMap model) {
		log.debug("Entering customView() in the FourTLandingController");
		model.addAttribute("resourceTaskDetailForm", new ResourceTaskDetail());
		return "customView";
	}

	@RequestMapping(value = "/secure/resource/viewCustomTaskDetails.go")
	public void customViewTaskDetails(@ModelAttribute("resourceTaskDetailForm") ResourceTaskDetail resourceTaskDtl,
			HttpServletResponse response) {
		log.debug("Entering customViewTaskDetails() in the FourTLandingController");
		List<Object[]> resultList = Lists.newArrayList();
		try {
			resultList = fourTService.findCustomTaskResults(resourceTaskDtl.getCustomQuery());
			// TODO GET metadata also
			response.setContentType("text/plain");
			response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", txtFileName));
			response.setCharacterEncoding("UTF-8");

			for (Object[] objects : resultList) {
				String row = "";
				for (Object obj : objects) {
					row = row.concat(obj.toString()).concat("\t");
				}
				response.getWriter().write(row);
				response.getWriter().println();
			}
		} catch (IOException | FourTServiceException fse) {
			log.debug("FourTServiceException - " + fse.getMessage());
			throw new FourTException(fse.getMessage());
		}
	}

	@RequestMapping(value = "/landing/fourTDBManagerTool.go")
	public String dbManagerTool(ModelMap model) {
		log.debug("Entering dbManagerTool() in the FourTLandingController");
		model.addAttribute("resourceTaskTrackerForm", new ResourceTask());
		return "landingPage";
	}

}
