package com.xavier.dong.springcloud.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author XavierDong
 **/
@RestController
@RequiredArgsConstructor
@RequestMapping("consumer")
public class OrderNacosController {


    private final RestTemplate restTemplate;
    @Value("${server-url.nacos-user-service}")
    private String serverUrl;

    @GetMapping("payment/nacos/{id}")
    public String getPayment(@PathVariable("id") Integer id) {
        return this.restTemplate.getForObject(this.serverUrl + "/payment/nacos/" + id, String.class);
    }
}
