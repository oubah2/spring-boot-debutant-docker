package org.sid;

import org.sid.entities.AppUser;
import org.sid.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Stream;

@SpringBootApplication
public class SpringBootDebutantApplication {

    private static Logger logger = Logger.getLogger("SpringBootDebutantApplication");

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDebutantApplication.class, args);

        logger.info("Configuration initial de mon projet Spring Boot");

    }

    @Bean
    CommandLineRunner start(UserService userService) {

        return args -> {
            logger.info("init data)");
            userService.initData().stream().forEach(System.out::println);

        };

    }

    @Bean
    BCryptPasswordEncoder getBCPE() {

        return new BCryptPasswordEncoder();
    }

}