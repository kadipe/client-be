package com.kadipe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EntityScan(basePackages = "com.kadipe")
@ComponentScan(basePackages = "com.kadipe")
@ServletComponentScan(basePackages = "com.kadipe")
@EnableScheduling
@EnableCaching
public class Application {

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }

}