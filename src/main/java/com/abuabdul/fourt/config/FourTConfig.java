package com.abuabdul.fourt.config;

import java.util.Properties;

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
 * @author abuabdul
 *
 */
@Configuration
public class FourTConfig {

	@Value("${OPENSHIFT_HSQL_DB_USERNAME}")
	private String username;

	@Value("${OPENSHIFT_HSQL_DB_PASSWORD}")
	private String password;

	@Value("${jdbc.driverClassName}")
	private String driverClassName;

	@Value("${OPENSHIFT_HSQL_DB_HOST}")
	private String host;

	@Value("${OPENSHIFT_HSQL_DB_PORT}")
	private String port;

	@Value("${fourt.tasktracker.db.name}")
	private String taskTrackerDBName;

	@Value("${jdbc.poolInitialSize}")
	private int initialSize;

	@Value("${jdbc.poolMaxTotal}")
	private int maxTotal;

	@Value("${jdbc.poolMinIdle}")
	private int minIdle;

	@Value("${jdbc.poolMaxIdle}")
	private int maxIdle;

	@Value("${jdbc.poolMaxWaitMillis}")
	private int maxWaitMillis;

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
				.addScript("classpath:sql/db/hsqldb/create_db.sql").build();
	}

	// Disable after development
	@PostConstruct
	public void startDBManager() throws FourTServiceException {
		try {
			MethodInvokingFactoryBean methodInvokingBean = new MethodInvokingFactoryBean();
			methodInvokingBean.setTargetClass(DatabaseManagerSwing.class);
			methodInvokingBean.setTargetMethod("main");
			methodInvokingBean.setArguments(
					new String[] { "--url", getInMemoryHSQLDBUrl(), "--user", username, "--password", password });
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
		datasource.setUrl(getInMemoryHSQLDBUrl());
		datasource.setUsername(username);
		datasource.setPassword(password);
		datasource.setInitialSize(initialSize);
		datasource.setMinIdle(minIdle);
		datasource.setMaxIdle(maxIdle);
		datasource.setMaxTotal(maxTotal);
		datasource.setMaxWaitMillis(maxWaitMillis);
		return datasource;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
		LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactory.setDataSource(dataSource());

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
		//Need to specify when entity class is not specified in persistence.xml
		entityManagerFactory.setPackagesToScan("com.abuabdul.fourt.domain");
		return entityManagerFactory;
	}

	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
		hibernateJpaVendorAdapter.setDatabasePlatform("org.hibernate.dialect.HSQLDialect");
		hibernateJpaVendorAdapter.setGenerateDdl(false);
		return hibernateJpaVendorAdapter;
	}

	// NOT needed
	public Properties jpaProperties() {
		Properties prop = new Properties();
		/*
		 * prop.put("javax.persistence.jdbc.url", url);
		 * prop.put("javax.persistence.jdbc.user", "sa");
		 * prop.put("javax.persistence.jdbc.password", "");
		 * prop.put("javax.persistence.jdbc.driver", driverClassName);
		 */
		return prop;
	}

	@Bean(name = "transactionManager")
	public PlatformTransactionManager jpaTransactionManager() {
		JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
		jpaTransactionManager.setEntityManagerFactory(entityManagerFactoryBean().getObject());
		return jpaTransactionManager;
	}

	protected String getInMemoryHSQLDBUrl() {
		return new StringBuilder("jdbc:hsqldb:mem:").append(taskTrackerDBName).toString();
	}

	protected String getStandaloneHSQLDBUrl() {
		return new StringBuilder("jdbc:hsqldb:hsql://").append(host).append(":").append(port).append("/")
				.append(taskTrackerDBName).toString();
	}
}
