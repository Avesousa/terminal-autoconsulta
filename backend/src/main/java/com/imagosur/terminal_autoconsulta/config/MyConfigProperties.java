package com.imagosur.terminal_autoconsulta.config;

import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfigProperties implements ApplicationContextAware {
    @SuppressWarnings("unused")
    private ApplicationContext appContext;

    @Bean(name = "localProperties")
    public Properties getLocalProperties() {
        Properties props = new Properties();
        return props;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.appContext = applicationContext;
    }
}
