package com.s1935.pandahr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class PandahrApplication {

    public static void main(String[] args) {
        SpringApplication.run(PandahrApplication.class, args);
    }

}
