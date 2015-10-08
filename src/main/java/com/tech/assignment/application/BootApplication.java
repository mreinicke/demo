package com.tech.assignment.application;

/**
* Created by a543086 on 10/01/2015.
*/

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@ComponentScan(basePackages = {"com.tech.assignment"})
public class BootApplication {
    private static final Logger LOG = LogManager.getLogger(BootApplication.class);

    public static void main(String[] args){
        LOG.debug("Loading spring context");
        ApplicationContext ctx = SpringApplication.run(BootApplication.class, args);
        LOG.debug("Finished loading spring context");

        LOG.info("Initializing spring beans.");
        List<String> beanNames = Arrays.asList(ctx.getBeanDefinitionNames());
        beanNames.parallelStream().sorted((a, b) -> b.compareTo(a)).forEach(s -> LOG.info("Initialized Bean: " + s));

        LOG.debug("Finished initializing spring beans.");
    }


}