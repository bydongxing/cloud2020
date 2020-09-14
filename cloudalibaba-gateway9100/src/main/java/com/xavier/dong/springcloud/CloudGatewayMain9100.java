package com.xavier.dong.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author XavierDong
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class CloudGatewayMain9100 {

    public static void main(String[] args) {
        SpringApplication.run(CloudGatewayMain9100.class, args);

    }
}
