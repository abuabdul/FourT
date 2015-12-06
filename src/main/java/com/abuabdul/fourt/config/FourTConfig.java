package com.abuabdul.fourt.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import com.abuabdul.fourt.criteria.fallback.FourTDefaultCriteria;
import com.abuabdul.fourt.criteria.fallback.FourTSelectAllTaskDetailCriteria;
import com.abuabdul.fourt.criteria.predicate.FourTPredicateService;
import com.abuabdul.fourt.criteria.predicate.FourTPredicateServiceImpl;
import com.abuabdul.fourt.criteria.result.FourTResultCriteria;
import com.abuabdul.fourt.criteria.result.FourTResultCriteriaService;
import com.abuabdul.fourt.dao.FourTReadOnlyDBDAO;
import com.abuabdul.fourt.dao.FourTResourceDAO;
import com.abuabdul.fourt.dao.FourTTaskDetailDAO;
import com.abuabdul.fourt.dao.impl.FourTReadOnlyDBDAOImpl;
import com.abuabdul.fourt.dao.impl.FourTResourceDAOImpl;
import com.abuabdul.fourt.dao.impl.FourTTaskDetailDAOImpl;
import com.abuabdul.fourt.data.exporter.FourTCSVDataFileExporter;
import com.abuabdul.fourt.data.exporter.FourTFileExporter;
import com.abuabdul.fourt.data.exporter.FourTFileWriterService;
import com.abuabdul.fourt.data.exporter.FourTFileWriterServiceImpl;
import com.abuabdul.fourt.data.exporter.FourTPlainTextDataFileExporter;
import com.abuabdul.fourt.domain.Resource;
import com.abuabdul.fourt.domain.TaskDetail;
import com.abuabdul.fourt.model.ResourceTask;
import com.abuabdul.fourt.model.ResourceTaskDetail;
import com.abuabdul.fourt.model.converter.FourTConverter;
import com.abuabdul.fourt.model.converter.FourTConverterService;
import com.abuabdul.fourt.service.FourTReadOnlyService;
import com.abuabdul.fourt.service.FourTReadOnlyServiceImpl;
import com.abuabdul.fourt.service.FourTVetoService;
import com.abuabdul.fourt.service.FourTVetoServiceImpl;

/**
 * FourT Application Configuration
 * 
 * @author abuabdul
 *
 */
@Configuration
public class FourTConfig {

	@Value("${fourt.tasktracker.db.name}")
	protected String taskTrackerDBName;

	@Value("${fourt.tasktracker.db.dialect}")
	protected String taskTrackerDBDialect;

	@Value("${fourt.domain.packages.to.scan}")
	protected String packagesToScan;

	@Value("${jdbc.driverClassName}")
	protected String driverClassName;

	@Value("${OPENSHIFT_HSQL_DB_HOST}")
	protected String host;

	@Value("${OPENSHIFT_HSQL_DB_PORT}")
	protected String port;

	@Value("${OPENSHIFT_HSQL_DB_USERNAME}")
	protected String username;

	@Value("${OPENSHIFT_HSQL_DB_PASSWORD}")
	protected String password;

	@Value("${OPENSHIFT_HSQL_DB_READONLY_USERNAME}")
	protected String readOnlyUser;

	@Value("${OPENSHIFT_HSQL_DB_READONLY_PASSWORD}")
	protected String readOnlyPassword;

	@Value("${jdbc.poolInitialSize}")
	protected int initialSize;

	@Value("${jdbc.poolMaxTotal}")
	protected int maxTotal;

	@Value("${jdbc.poolMinIdle}")
	protected int minIdle;

	@Value("${jdbc.poolMaxIdle}")
	protected int maxIdle;

	@Value("${jdbc.poolMaxWaitMillis}")
	protected int maxWaitMillis;

	private static final String APPCONFIG_FILE_NAME = "Appconfig.properties";

	@Bean
	public static PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() {
		PropertyPlaceholderConfigurer propertyConfigurer = new PropertyPlaceholderConfigurer();
		propertyConfigurer.setLocation(new ClassPathResource(APPCONFIG_FILE_NAME));
		propertyConfigurer.setIgnoreResourceNotFound(false);
		propertyConfigurer.setIgnoreUnresolvablePlaceholders(false);
		propertyConfigurer.setSystemPropertiesMode(PropertyPlaceholderConfigurer.SYSTEM_PROPERTIES_MODE_OVERRIDE);
		return propertyConfigurer;
	}

	@Bean
	protected JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
		hibernateJpaVendorAdapter.setDatabasePlatform(taskTrackerDBDialect);
		hibernateJpaVendorAdapter.setGenerateDdl(false);
		hibernateJpaVendorAdapter.setShowSql(true);
		return hibernateJpaVendorAdapter;
	}

	@Bean
	public FourTResultCriteria fourTResultCriteria() {
		return new FourTResultCriteriaService(fourTVetoService(), fourTPredicateService(), fourTDefaultCriteria());
	}

	@Bean
	public FourTPredicateService<TaskDetail> fourTPredicateService() {
		return new FourTPredicateServiceImpl();
	}

	@Bean
	public FourTDefaultCriteria<TaskDetail> fourTDefaultCriteria() {
		return new FourTSelectAllTaskDetailCriteria(fourTVetoService());
	}

	@Bean
	public FourTVetoService fourTVetoService() {
		return new FourTVetoServiceImpl(fourTResourceDAO(), fourTTaskDetailDAO());
	}

	@Bean
	public FourTTaskDetailDAO fourTTaskDetailDAO() {
		return new FourTTaskDetailDAOImpl();
	}

	@Bean
	public FourTResourceDAO fourTResourceDAO() {
		return new FourTResourceDAOImpl();
	}

	@Bean
	public FourTReadOnlyService fourTReadOnlyService() {
		return new FourTReadOnlyServiceImpl(fourTReadOnlyDBDAO());
	}

	@Bean
	public FourTReadOnlyDBDAO fourTReadOnlyDBDAO() {
		return new FourTReadOnlyDBDAOImpl();
	}

	@Bean
	public FourTFileWriterService<ResourceTaskDetail> fourTCSVFileWriterService() {
		return new FourTFileWriterServiceImpl<ResourceTaskDetail, TaskDetail>(csvFileExporter());
	}

	@Bean
	public FourTFileWriterService<ResourceTaskDetail> fourTTextFileWriterService() {
		return new FourTFileWriterServiceImpl<ResourceTaskDetail, Object[]>(textFileExporter());
	}

	@Bean
	public FourTFileExporter<ResourceTaskDetail, TaskDetail> csvFileExporter() {
		return new FourTCSVDataFileExporter(fourTResultCriteria(), fourTConverterService());
	}

	@Bean
	public FourTFileExporter<ResourceTaskDetail, Object[]> textFileExporter() {
		return new FourTPlainTextDataFileExporter(fourTReadOnlyService());
	}

	@Bean
	public FourTConverter<ResourceTask, Resource, TaskDetail, ResourceTaskDetail> fourTConverterService() {
		return new FourTConverterService();
	}
}
