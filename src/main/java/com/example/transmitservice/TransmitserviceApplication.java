package com.example.transmitservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author 祥玉
 */
@EnableScheduling
@EntityScan(value = "com.example.*")
@ComponentScan(value = "com.example.*")
@EnableJpaRepositories(value = "com.example.repository")
@SpringBootApplication
public class TransmitserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TransmitserviceApplication.class, args);
    }

}
