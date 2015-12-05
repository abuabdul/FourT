package com.abuabdul.fourt.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.abuabdul.fourt.config.FourTInMemoryDataSourceConfig;
import com.abuabdul.fourt.service.FourTReadOnlyService;
import com.abuabdul.fourt.service.FourTVetoService;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("dev")
@ContextConfiguration(locations = {
		"classpath:spring/fourt-spring-servlet.xml" }, classes = FourTInMemoryDataSourceConfig.class)
public class FourTLandingControllerTest {

	@Autowired
	private FourTReadOnlyService fourTReadOnlyService;

	@Autowired
	private FourTVetoService fourTVetoService;

	@Test
	public void testSaveResourceTaskDetails() {

	}

}
