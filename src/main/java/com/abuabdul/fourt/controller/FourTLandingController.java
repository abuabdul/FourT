package com.abuabdul.fourt.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.abuabdul.fourt.domain.Resource;
import com.abuabdul.fourt.exception.FourTException;
import com.abuabdul.fourt.exception.FourTServiceException;
import com.abuabdul.fourt.model.ResourceTask;
import com.abuabdul.fourt.model.converter.FourTConverter;
import com.abuabdul.fourt.service.FourTService;

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
	private FourTConverter<ResourceTask, Resource> fourTConverter;

	@Autowired
	private FourTService fourTService;

	@RequestMapping(value = "/landing/fourTOverview.go")
	public String landingPage(ModelMap model) {
		log.debug("Entering landingPage() in the FourTLandingController");
		model.addAttribute("resourceTaskTrackerForm", new ResourceTask());
		return "landingPage";
	}

	@RequestMapping(value = "/secure/resource/taskTracker.go")
	public String saveResourceTaskDetails(@ModelAttribute("resourceTaskTrackerForm") ResourceTask resourceTask,
			BindingResult result, ModelMap model) {
		log.debug("Entering saveResourceTaskDetails() in the FourTLandingController");
		try {
			Resource resource = fourTConverter.convert(resourceTask);
			fourTService.saveResourceTaskDetails(resource);
			model.addAttribute("resourceTaskTrackerForm", new ResourceTask());
			return "landingPage";
		} catch (FourTServiceException fse) {
			fse.printStackTrace();
			throw new FourTException(fse.getMessage());
		}
	}

	@RequestMapping(value = "/landing/fourTViewResults.go")
	public String viewResults(ModelMap model) {
		log.debug("Entering landingPage() in the FourTLandingController");
		model.addAttribute("resourceTaskTrackerForm", new ResourceTask());
		return "viewResults";
	}

	@RequestMapping(value = "/landing/fourTCustomView.go")
	public String customView(ModelMap model) {
		log.debug("Entering landingPage() in the FourTLandingController");
		model.addAttribute("resourceTaskTrackerForm", new ResourceTask());
		return "customView";
	}

	@RequestMapping(value = "/secure/resource/viewCustomTaskDetails.go")
	public String customViewTaskDetails(@ModelAttribute("resourceTaskTrackerForm") ResourceTask resourceTask,
			BindingResult result, ModelMap model) {
		log.debug("Entering customViewTaskDetails() in the FourTLandingController");
		try {
		//	ValidationUtils.rejectIfEmptyOrWhitespace(result, "frgtEmail", "email.required");

			if (result.hasErrors()) {
				return null;
			}
			fourTService.viewCustomTaskResults(resourceTask.getCustomQuery());
			model.addAttribute("resourceTaskTrackerForm", new ResourceTask());
			return "customView";
		} catch (FourTServiceException fse) {
			fse.printStackTrace();
			throw new FourTException(fse.getMessage());
		}
	}
}
