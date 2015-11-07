package com.abuabdul.fourt.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.abuabdul.fourt.model.ResourceTask;

/**
 * @author abuabdul
 *
 */
@Controller
public class FourTLandingController {

	// Define a static logger variable so that it references the
	// Logger instance named "FourTLandingController".
	private static final Logger log = LogManager.getLogger(FourTLandingController.class.getName());

	@RequestMapping(value = "/landing/fourTOverview.go")
	public String landingPage(ModelMap model) {
		log.debug("Entering landingPage() in the MpmprofLandingController");
		model.addAttribute("resourceTaskTrackerForm", new ResourceTask());
		return "landingPage";
	}

	@RequestMapping(value = "/secure/resource/taskTracker.go")
	public String saveResourceTaskDetails(@ModelAttribute("resourceTaskTrackerForm") ResourceTask resourceTask, BindingResult result, ModelMap model, HttpServletRequest request) {
		System.out.println(resourceTask.getResourceName());
		//model.addAttribute("resourceTaskTrackerForm", new ResourceTask());
		return "landingPage";
	}

}