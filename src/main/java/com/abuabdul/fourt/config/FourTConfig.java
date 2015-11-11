package com.abuabdul.fourt.config;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
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
import org.springframework.orm.jpa.vendor.Database;
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

	private String url = "jdbc:hsqldb:hsql://" + host + ":" + port + "/" + taskTrackerDBName;

	@Bean
	public static PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() {
		PropertyPlaceholderConfigurer propertyConfigurer = new PropertyPlaceholderConfigurer();
		propertyConfigurer.setLocation(new ClassPathResource("Appconfig.properties"));
		propertyConfigurer.setIgnoreResourceNotFound(false);
		propertyConfigurer.setIgnoreUnresolvablePlaceholders(false);
		propertyConfigurer.setSystemPropertiesMode(PropertyPlaceholderConfigurer.SYSTEM_PROPERTIES_MODE_OVERRIDE);
		return propertyConfigurer;
	}

	@Bean(destroyMethod = "close")
	public DataSource dataSource() {
		BasicDataSource datasource = new BasicDataSource();
		datasource.setUsername(username);
		datasource.setPassword(password);
		datasource.setDriverClassName(driverClassName);
		datasource.setUrl(url);
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
		//entityManagerFactory.setPersistenceUnitName("fourt");
		entityManagerFactory.setJpaDialect(new HibernateJpaDialect());
		entityManagerFactory.setJpaVendorAdapter(jpaVendorAdapter());
		entityManagerFactory.setPackagesToScan(new String[] {"com.abuabdul.fourt.domain"});
		return entityManagerFactory;
	}

	@Bean
	public PlatformTransactionManager jpaTransactionManager() {
		JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
		jpaTransactionManager.setEntityManagerFactory(entityManagerFactoryBean().getObject());
		return jpaTransactionManager;
	}

	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
		jpaVendorAdapter.setDatabase(Database.HSQL);
		jpaVendorAdapter.setDatabasePlatform("org.hibernate.dialect.HSQLDialect");
		return jpaVendorAdapter;
	}

	@Bean
	public EmbeddedDatabase embeddedDatabase() {
		return new EmbeddedDatabaseBuilder().setName(taskTrackerDBName).setType(EmbeddedDatabaseType.HSQL).build();
	}

	// Disable after development
	@PostConstruct
	public void startDBManager() throws FourTServiceException {
		try {
			MethodInvokingFactoryBean methodInvokingBean = new MethodInvokingFactoryBean();
			methodInvokingBean.setTargetClass(DatabaseManagerSwing.class);
			methodInvokingBean.setTargetMethod("main");
			methodInvokingBean.setArguments(new String[] { "--url", "jdbc:hsqldb:mem:" + taskTrackerDBName, "--user", "sa", "--password", "" });
			methodInvokingBean.prepare();
			methodInvokingBean.invoke();
		} catch (Exception ex) {
			throw new FourTServiceException(ex.getMessage(), ex);
		}
	}

}
