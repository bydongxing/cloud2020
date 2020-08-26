package com.xavier.dong.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author XavierDong
 **/
@SpringBootApplication
@EnableEurekaClient
public class MainAppConfigClient3366 {

    public static void main(String[] args) {
        SpringApplication.run(MainAppConfigClient3366.class, args);
    }
}
