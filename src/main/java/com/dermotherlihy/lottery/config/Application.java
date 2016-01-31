package com.dermotherlihy.lottery.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

/**
 * Created by dermot.herlihy on 29/01/2016.
 */

@Configuration
@ComponentScan(basePackages = "com.dermotherlihy.lottery")
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages ="com.dermotherlihy.lottery")
@EntityScan(basePackages = "com.dermotherlihy.lottery")
@EnableSpringDataWebSupport
public class Application  {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }

}
