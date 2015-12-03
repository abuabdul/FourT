package com.abuabdul.fourt.config;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.hsqldb.util.DatabaseManagerSwing;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.transaction.PlatformTransactionManager;

import com.abuabdul.fourt.exception.FourTServiceException;

/**
 * @author abuabdul
 *
 */
@Configuration
@Profile("dev")
public class FourTInMemoryDataSourceConfig extends FourTConfig {

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
					new String[] { "--url", getSQLDBUrl(), "--user", username, "--password", password });
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
		datasource.setUrl(getSQLDBUrl());
		datasource.setUsername(username);
		datasource.setPassword(password);
		datasource.setInitialSize(initialSize);
		datasource.setMinIdle(minIdle);
		datasource.setMaxIdle(maxIdle);
		datasource.setMaxTotal(maxTotal);
		datasource.setMaxWaitMillis(maxWaitMillis);
		return datasource;
	}

	@Bean(name = "entityManager")
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

	@Bean(name = "transactionManager")
	public PlatformTransactionManager jpaTransactionManager() {
		JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
		jpaTransactionManager.setEntityManagerFactory(entityManagerFactoryBean().getObject());
		return jpaTransactionManager;
	}
	
	protected String getSQLDBUrl() {
		return new StringBuilder("jdbc:hsqldb:mem:").append(taskTrackerDBName).toString();
	}
	
	@Bean(destroyMethod = "close")
	public DataSource readOnlyDataSource() {
		BasicDataSource datasource = new BasicDataSource();
		datasource.setDriverClassName(driverClassName);
		datasource.setUrl(getSQLDBUrl());
		datasource.setUsername(readOnlyUser);
		datasource.setPassword(readOnlyPassword);
		datasource.setInitialSize(initialSize);
		datasource.setMinIdle(minIdle);
		datasource.setMaxIdle(maxIdle);
		datasource.setMaxTotal(maxTotal);
		datasource.setMaxWaitMillis(maxWaitMillis);
		return datasource;
	}
	
	@Bean(name = "readOnlyEntityManager")
	public LocalContainerEntityManagerFactoryBean readOnlyEntityManagerFactoryBean() {
		LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactory.setDataSource(readOnlyDataSource());
		entityManagerFactory.setPersistenceUnitName("readOnly");
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

	@Bean(name = "readOnlyTransactionManager")
	public PlatformTransactionManager readOnlyJpaTransactionManager() {
		JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
		jpaTransactionManager.setEntityManagerFactory(readOnlyEntityManagerFactoryBean().getObject());
		return jpaTransactionManager;
	}
}
