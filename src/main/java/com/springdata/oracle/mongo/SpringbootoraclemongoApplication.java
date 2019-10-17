package com.springdata.oracle.mongo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@ComponentScan(basePackages = { "com.springdata" })
@EnableMongoRepositories(basePackages={"com.springdata.dao.mongo"})

public class SpringbootoraclemongoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootoraclemongoApplication.class, args);
    }
}

