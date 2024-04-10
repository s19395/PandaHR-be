package com.s1935.pandahr;

import com.s1935.pandahr.config.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeyProperties.class)
public class PandahrApplication {

    public static void main(String[] args) {
        SpringApplication.run(PandahrApplication.class, args);
    }

}
