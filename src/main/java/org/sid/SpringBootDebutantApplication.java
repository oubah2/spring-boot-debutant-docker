package org.sid;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.logging.Logger;

@SpringBootApplication
public class SpringBootDebutantApplication {

    private static Logger logger = Logger.getLogger("SpringBootDebutantApplication");

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDebutantApplication.class, args);


        logger.info("Configuration initial de mon projet Spring Boot");

    }

}