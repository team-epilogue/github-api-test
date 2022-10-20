package com.example.gitapitest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class GitApiTestApplication {
    public static void main(String[] args) {
        SpringApplication.run(GitApiTestApplication.class, args);
    }
}
