package ru.stepanov.spring_crud.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = "ru.stepanov.spring_crud")
@EnableWebMvc
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
public class SpringConfig implements WebMvcConfigurer {

    private static final String DATABASE_DRIVER = "db.driver";
    private static final String DATABASE_PASSWORD = "db.password";
    private static final String DATABASE_URL = "db.url";
    private static final String DATABASE_USERNAME = "db.username";

    private static final String HIBERNATE_DIALECT = "hibernate.dialect";
    private static final String HIBERNATE_SHOW_SQL = "hibernate.show_sql";
    private static final String HIBERNATE_FORMAT_SQL = "hibernate.format_sql";
    private static final String HIBERNATE_HBM2DDL_AUTO = "hibernate.hbm2ddl.auto";

    private static final String ENTITYMANAGER_PACKAGES_TO_SCAN = "entitymanager.packages.to.scan";

    @Resource
    private Environment env;

    // define database data source
    @Bean
    public DataSource myDataSource() {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        try {
            dataSource.setDriverClass(env.getRequiredProperty(DATABASE_DRIVER));
            dataSource.setJdbcUrl(env.getRequiredProperty(DATABASE_URL));
            dataSource.setUser(env.getRequiredProperty(DATABASE_USERNAME));
            dataSource.setPassword(env.getRequiredProperty(DATABASE_PASSWORD));
            dataSource.setMinPoolSize(5);
            dataSource.setMaxPoolSize(20);
            dataSource.setMaxIdleTime(30000);
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        return dataSource;
    }

    // setup entity manager
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
        entityManager.setDataSource(myDataSource());
        entityManager.setPackagesToScan(env.getRequiredProperty(ENTITYMANAGER_PACKAGES_TO_SCAN));

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        entityManager.setJpaVendorAdapter(vendorAdapter);
        entityManager.setJpaProperties(hibernateProperties());

        return entityManager;
    }

    // some additional Hibernate properties
    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put(HIBERNATE_DIALECT, env.getRequiredProperty(HIBERNATE_DIALECT));
        properties.put(HIBERNATE_SHOW_SQL, env.getRequiredProperty(HIBERNATE_SHOW_SQL));
        properties.put(HIBERNATE_FORMAT_SQL, env.getRequiredProperty(HIBERNATE_FORMAT_SQL));
        properties.put(HIBERNATE_HBM2DDL_AUTO, env.getRequiredProperty(HIBERNATE_HBM2DDL_AUTO));
        return properties;
    }

    // setup transaction manager
    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());

        return transactionManager;
    }

    // set spring exception processor
    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    // define Spring MVC view resolver
    @Bean
    public UrlBasedViewResolver setupViewResolver() {
        UrlBasedViewResolver resolver = new UrlBasedViewResolver();
        resolver.setPrefix("/WEB-INF/view/");
        resolver.setSuffix(".jsp");
        resolver.setViewClass(JstlView.class);
        return resolver;
    }

    // add support for web resources
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/resources/**")
                .addResourceLocations("/resources/");
    }
}
