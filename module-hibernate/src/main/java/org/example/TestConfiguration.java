//package org.example;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.core.env.Environment;
//import org.springframework.orm.hibernate5.HibernateTransactionManager;
//import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import java.util.Properties;
//
//import static org.hibernate.cfg.AvailableSettings.*;
//
//
//@Configuration
//@PropertySource("classpath:application.properties")
//@ComponentScan("org.example")
//@EnableTransactionManagement
//public class TestConfiguration {
//
//    @Autowired
//    private Environment env;
//
//
//    @Bean
//    public LocalSessionFactoryBean getSessionFactory() {
//        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
//
//        Properties props = new Properties();
//        props.put(DRIVER, env.getProperty("postgresql.driver"));
//        props.put(URL, env.getProperty("postgresql.url"));
//        props.put(USER, env.getProperty("postgresql.user"));
//        props.put(PASS, env.getProperty("postgresql.password"));
//
//        props.put(SHOW_SQL, env.getProperty("hibernate.show_sql"));
//        props.put(HBM2DDL_AUTO, env.getProperty("hibernate.hbm2ddl.auto"));
//
//        localSessionFactoryBean.setHibernateProperties(props);
//        localSessionFactoryBean.setPackagesToScan("org.example");
//
//        return localSessionFactoryBean;
//    }
//
//
//    @Bean
//    public HibernateTransactionManager getTransactionManager() {
//        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
//        transactionManager.setSessionFactory(getSessionFactory().getObject());
//        return transactionManager;
//    }
//
//}
