package com.abuabdul.fourt.dao;

import static com.abuabdul.fourt.util.FourTUtils.getUTCDateTime;
import static com.abuabdul.fourt.util.FourTUtils.simpleDateWithDDMMYYYY;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.abuabdul.fourt.config.FourTConfig;
import com.abuabdul.fourt.config.FourTInMemoryDataSourceConfig;
import com.abuabdul.fourt.config.FourTStandaloneDataSourceConfig;
import com.abuabdul.fourt.domain.Resource;
import com.abuabdul.fourt.domain.TaskDetail;
import com.abuabdul.fourt.exception.FourTServiceException;
import com.google.common.collect.Lists;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { FourTInMemoryDataSourceConfig.class, FourTStandaloneDataSourceConfig.class,
		FourTConfig.class })
// @ContextConfiguration(locations = {"classpath:fourt-spring-servlet.xml"})
@Transactional
public class FourTReadOnlyDBDAOTest {

	@Autowired
	private FourTReadOnlyDBDAO fourTReadOnlyDBDAO;

	@Autowired
	private FourTResourceDAO fourTResourceDAO;

	@SuppressWarnings("unchecked")
	@Test
	public void testFindCustomTaskResults() throws FourTServiceException {
		// Save a resource
		Resource resource = this.populateResource();
		fourTResourceDAO.saveResource(resource);

		// Query using readonly dao
		String nativeQuery = "Select * from resource";
		List<Object[]> resultList = fourTReadOnlyDBDAO.findCustomTaskResults(nativeQuery);
	}

	private Resource populateResource() {
		Resource resource = new Resource();
		resource.setName("Abu");
		resource.setTaskDate(simpleDateWithDDMMYYYY("23/07/2015"));
		resource.setCreatedBy("Abu");
		resource.setCreatedDate(getUTCDateTime());
		resource.setUpdatedBy("Abu");
		resource.setUpdatedDate(getUTCDateTime());
		List<TaskDetail> taskDetailList = Lists.newArrayList();
		TaskDetail taskDetail = new TaskDetail();
		taskDetail.setResource(resource);
		taskDetail.setDuration(Float.valueOf("4.0"));
		taskDetail.setStatus("In Progress");
		taskDetail.setTaskDesc("Hello Task");
		taskDetailList.add(taskDetail);
		resource.setTaskDetailList(taskDetailList);
		return resource;
	}
}
