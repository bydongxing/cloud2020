package com.xavier.dong.springcloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author XavierDong
 **/
@Configuration
public class ApplicationContextConfig {

//    @Bean
//    @LoadBalanced // 使用 @LoadBalanc 赋予 RestTemplate 具有负载均衡的能力
//    public RestTemplate restTemplate() {
//        return new RestTemplate();
//    }

    @Bean
//    @LoadBalanced // 使用 @LoadBalanc 赋予 RestTemplate 具有负载均衡的能力
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
