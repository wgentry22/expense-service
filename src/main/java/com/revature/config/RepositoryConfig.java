package com.revature.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories("com.revature.repository")
public class RepositoryConfig {

	@Value("${hibernate.connection.url}")
	private String connectionUrl;
	
	@Value("${hibernate.connection.username}")
	private String username;
	
	@Value("${hibernate.connection.password}")
	private String password;
	
	@Value("${hibernate.connection.driver-class}")
	private String driverClass;
	
	@Value("${hibernate.dialect}")
	private String dialect;
	
	@Value("${hibernate.show_sql}")
	private String showSql;
	
	@Value("${hibernate.hbm2ddl.auto}")
	private String hbm2ddl;
	
	@Value("${hibernate.c3p0.min_size}")
	private String minSize;
	
	@Value("${hibernate.c3p0.max_size}")
	private String maxSize;
	
	@Value("${hibernate.c3p0.acquire_increment}")
	private String acquireIncrement;
	
	@Value("${hibernate.c3p0.timeout}")
	private String timeout;
	
	@Value("${hibernate.c3p0.max_statements}")
	private String maxStatements;
	
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUrl(connectionUrl);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		dataSource.setDriverClassName(driverClass);
		return dataSource;
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		emf.setPackagesToScan(new String[] { "com.revature.model" });
		emf.setDataSource(dataSource());
		JpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		emf.setJpaVendorAdapter(adapter);
		emf.setJpaProperties(getHibernateProperties());
		return emf;
	}
	
	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory);
		return transactionManager;
	}
	
	@Bean 
	public PersistenceExceptionTranslationPostProcessor exceptionTranslator() {
		return new PersistenceExceptionTranslationPostProcessor();
	}
	
	private Properties getHibernateProperties() {
		Properties props = new Properties();
		props.setProperty("hibernate.hbm2ddl.auto", hbm2ddl);
		props.setProperty("hibernate.dialect", dialect);
		props.setProperty("hibernate.c3p0.min_size", minSize);
		props.setProperty("hibernate.c3p0.max_size", maxSize);
		props.setProperty("hibernate.show_sql", showSql);
		props.setProperty("hibernate.c3p0.acquire_timeout", acquireIncrement);
		props.setProperty("hibernate.c3p0.timeout", timeout);
		props.setProperty("hibernate.c3p0.max_statements", maxStatements);
		return props;
	}
}
