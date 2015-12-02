package com.abuabdul.fourt.config;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.hsqldb.util.DatabaseManagerSwing;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import com.abuabdul.fourt.exception.FourTServiceException;

/**
 * FourT Application Configuration
 * 
 * @author abuabdul
 *
 */
@Configuration
public class FourTConfig {

	@Value("${fourt.db.script.classpath.location}")
	protected String dbScript;

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

	@Bean(name = "embeddedDatabase")
	public EmbeddedDatabase embeddedDatabase() {
		return new EmbeddedDatabaseBuilder().setName(taskTrackerDBName).setType(EmbeddedDatabaseType.HSQL)
				.addScript(dbScript).build();
	}

	// Disable after development
	@PostConstruct
	public void startDBManager() throws FourTServiceException {
		try {
			MethodInvokingFactoryBean methodInvokingBean = new MethodInvokingFactoryBean();
			methodInvokingBean.setTargetClass(DatabaseManagerSwing.class);
			methodInvokingBean.setTargetMethod("main");
			methodInvokingBean.setArguments(
					new String[] { "--url", getInMemorySQLDBUrl(), "--user", username, "--password", password });
			methodInvokingBean.prepare();
			methodInvokingBean.invoke();
		} catch (Exception ex) {
			throw new FourTServiceException(ex.getMessage(), ex);
		}
	}

	@Bean(destroyMethod = "close")
	public DataSource dataSource() {
		BasicDataSource datasource = new BasicDataSource();
		datasource.setDriverClassName(driverClassName);
		datasource.setUrl(getInMemorySQLDBUrl());
		datasource.setUsername(username);
		datasource.setPassword(password);
		datasource.setInitialSize(initialSize);
		datasource.setMinIdle(minIdle);
		datasource.setMaxIdle(maxIdle);
		datasource.setMaxTotal(maxTotal);
		datasource.setMaxWaitMillis(maxWaitMillis);
		return datasource;
	}

	
	@Bean(name="entityManager")
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
		LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactory.setDataSource(dataSource());
		entityManagerFactory.setPersistenceUnitName("allPrivileges");
		/*
		 * If JpaVendorAdapter is set, persistence xml is not needed
		 * 
		 * entityManagerFactory.setPersistenceUnitName("fourtunit");
		 * entityManagerFactory.setPersistenceXmlLocation(
		 * "classpath:META-INF/persistence.xml");
		 * 
		 */
		entityManagerFactory.setJpaDialect(new HibernateJpaDialect());
		entityManagerFactory.setPersistenceProvider(new HibernatePersistenceProvider());
		entityManagerFactory.setJpaVendorAdapter(jpaVendorAdapter());
		// Need to specify when entity class is not specified in persistence.xml
		entityManagerFactory.setPackagesToScan(packagesToScan);
		return entityManagerFactory;
	}

	
	@Bean
	protected JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
		hibernateJpaVendorAdapter.setDatabasePlatform(taskTrackerDBDialect);
		hibernateJpaVendorAdapter.setGenerateDdl(false);
		hibernateJpaVendorAdapter.setShowSql(true);
		return hibernateJpaVendorAdapter;
	}

	@Bean(name = "transactionManager")
	public PlatformTransactionManager jpaTransactionManager() {
		JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
		jpaTransactionManager.setEntityManagerFactory(entityManagerFactoryBean().getObject());
		return jpaTransactionManager;
	}

	protected String getInMemorySQLDBUrl() {
		return new StringBuilder("jdbc:hsqldb:mem:").append(taskTrackerDBName).toString();
	}

	protected String getStandaloneSQLDBUrl() {
		return new StringBuilder("jdbc:hsqldb:hsql://").append(host).append(":").append(port).append("/")
				.append(taskTrackerDBName).toString();
	}
}
