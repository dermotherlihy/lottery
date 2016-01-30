package com.dermotherlihy.lottery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

/**
 * Created by dermot.herlihy on 29/01/2016.
 */

@Configuration
@ComponentScan(basePackages = "com.dermotherlihy.lottery")
@EnableAutoConfiguration
@EnableSpringDataWebSupport
public class Application {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }
}
