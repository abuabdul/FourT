package com.abuabdul.fourt.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

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

}
