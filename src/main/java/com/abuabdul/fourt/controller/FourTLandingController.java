package com.abuabdul.fourt.controller;

import static org.apache.commons.lang3.StringUtils.isEmpty;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hsqldb.util.DatabaseManagerSwing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.RequestContext;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.abuabdul.fourt.criteria.result.FourTResultCriteriaService;
import com.abuabdul.fourt.db.manager.service.FourTDBManager;
import com.abuabdul.fourt.db.manager.service.FourTDBManagerService;
import com.abuabdul.fourt.domain.Resource;
import com.abuabdul.fourt.domain.TaskDetail;
import com.abuabdul.fourt.exception.FourTException;
import com.abuabdul.fourt.exception.FourTServiceException;
import com.abuabdul.fourt.model.ResourceTask;
import com.abuabdul.fourt.model.ResourceTaskDetail;
import com.abuabdul.fourt.model.converter.FourTConverter;
import com.abuabdul.fourt.service.FourTReadOnlyService;
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

	private final static String ExcelFileName = "export.excel.file.name";

	private final static String TxtFileName = "custom.view.file.name";

	private final static String EmptyCustomQueryString = "empty.custom.query.message";

	private final static String NoResultString = "no.custom.result.message";

	@Autowired
	private FourTConverter<ResourceTask, Resource, TaskDetail, ResourceTaskDetail> fourTConverter;

	@Autowired
	private FourTService fourTService;

	@Autowired
	private FourTReadOnlyService fourTReadOnlyService;

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
			List<TaskDetail> resourcesTaskDetail = new FourTResultCriteriaService(fourTService)
					.findTasksBasedOn(resourceTaskDtl);
			List<ResourceTaskDetail> resourceTaskDetails = fourTConverter.convert(resourcesTaskDetail);
			model.addAttribute("resourceTaskDetails", resourceTaskDetails);
			model.addAttribute("resourceTaskDetailForm", resourceTaskDtl);
			return "viewTasks";
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
		List<Object[]> resultList = Lists.newArrayList();
		try {
			RequestContext requestContext = new RequestContext(request);
			response.setContentType("text/plain");
			response.setHeader("Content-Disposition",
					String.format("attachment; filename=\"%s\"", requestContext.getMessage(TxtFileName)));
			response.setCharacterEncoding("UTF-8");

			if (isEmpty(resourceTaskDtl.getCustomQuery())) {
				response.getWriter().write(requestContext.getMessage(EmptyCustomQueryString));
			} else {
				resultList = fourTReadOnlyService.findCustomTaskResults(resourceTaskDtl.getCustomQuery());
				if (resultList.isEmpty()) {
					response.getWriter().write(requestContext.getMessage(NoResultString));
				}
				for (Object[] objects : resultList) {
					String row = "";
					for (Object obj : objects) {
						row = row.concat(obj.toString()).concat("\t");
					}
					response.getWriter().write(row);
					response.getWriter().println();
				}
			}
		} catch (IOException | FourTServiceException fse) {
			log.debug("FourTServiceException - " + fse.getMessage());
			throw new FourTException(
					"FourTServiceException - Some error occurred. Please note: Custom Query is set to execute read-only queries only");
		}
	}

	@RequestMapping(value = "/secure/taskdetails/fourTExportToExcel.go")
	public void exportTaskResultToExcel(@ModelAttribute("resourceTaskDetailForm") ResourceTaskDetail resourceTaskDtl,
			HttpServletRequest request, HttpServletResponse response) {
		log.debug("Entering exportTaskResultToExcel() in the FourTLandingController");
		try {
			RequestContext requestContext = new RequestContext(request);
			response.setContentType("text/csv");
			response.setHeader("Content-Disposition",
					String.format("attachment; filename=\"%s\"", requestContext.getMessage(ExcelFileName)));
			response.setCharacterEncoding("UTF-8");
			List<TaskDetail> resourcesTaskDetail = new FourTResultCriteriaService(fourTService)
					.findTasksBasedOn(resourceTaskDtl);
			List<ResourceTaskDetail> resourceTaskDetails = fourTConverter.convert(resourcesTaskDetail);

			// uses the Super CSV API to generate CSV data from the model data
			ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);

			String[] header = { "Task Date", "Resource Name", "Task Description", "Task Duration", "Task Status" };
			String[] headerMapping = { "TaskDate", "ResourceName", "TaskDesc", "Duration", "Status" };

			csvWriter.writeHeader(header);

			for (ResourceTaskDetail savedResourceTaskDtl : resourceTaskDetails) {
				csvWriter.write(savedResourceTaskDtl, headerMapping);
			}
			csvWriter.close();
		} catch (FourTServiceException | IOException fse) {
			log.debug("FourTServiceException - " + fse.getMessage());
			throw new FourTException(fse.getMessage());
		}
	}

	@RequestMapping(value = "/landing/fourTDBManagerTool.go")
	public void dbManagerTool(ModelMap model) {
		log.debug("Entering dbManagerTool() in the FourTLandingController");
		try {
			FourTDBManager<DatabaseManagerSwing> fourTDBManager = new FourTDBManagerService(DatabaseManagerSwing.class,
					"main", new String[] { "--url", "jdbc:hsqldb:mem:fourtdb", "--user", "sa", "--password", "" });
			fourTDBManager.runDBManagerTool();
		} catch (FourTServiceException fse) {
			log.debug("FourTServiceException - " + fse.getMessage());
			throw new FourTException(fse.getMessage());
		}
	}
}
