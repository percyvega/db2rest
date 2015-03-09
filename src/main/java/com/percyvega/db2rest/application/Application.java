package com.percyvega.db2rest.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Arrays;

@Configuration
@EnableAutoConfiguration
@ComponentScan("com.percyvega.db2rest")
@EnableJpaRepositories("com.percyvega.db2rest.repository")
@EntityScan("com.percyvega.db2rest.model")
@PropertySource(value = {"application.properties", "sensitive.properties"}, ignoreResourceNotFound = true)
public class Application {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        logger.debug("Starting main(" + Arrays.toString(args) + ")");
        SpringApplication.run(Application.class, args);
    }

}
