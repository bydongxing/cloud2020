package com.xavier.dong.springcloud.controller;

import cn.hutool.core.collection.CollectionUtil;
import com.xavier.dong.springcloud.common.Result;
import com.xavier.dong.springcloud.entity.dto.Payment;
import com.xavier.dong.springcloud.lb.LoadBalancer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

/**
 * @author XavierDong
 **/
@RestController
@Slf4j
@RequestMapping("/consumer")
@RequiredArgsConstructor
public class OrderController {
    //    private static final String PAYMENT_URL = "http://localhost:8001";
    private static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";  // 服务提供者，在 EurekaServer 上的名称


    private final RestTemplate restTemplate;
    private final LoadBalancer loadBalancer;
    private final DiscoveryClient discoveryClient;

    @PostMapping("/payment/add")
    public Result addPayment(@RequestBody Payment payment) {
        return this.restTemplate.postForObject(PAYMENT_URL + "/payment/add", payment, Result.class);
    }


    @GetMapping("/payment/get/{id}")
    public Result findById(@PathVariable("id") Long id) {
        return this.restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id, Result.class);
    }

    @GetMapping("payment/lb")
    public String getPaymentLB() {
        List<ServiceInstance> instances = this.discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if (CollectionUtil.isEmpty(instances))
            return null;
        ServiceInstance instance = this.loadBalancer.INSTANCE(instances);
        URI uri = instance.getUri();
        return this.restTemplate.getForObject(uri + "/payment/lb", String.class);

    }

    //     zipkin+ sleuth
    @GetMapping("/payment/zipkin")
    public String paymentZipkin() {
        return this.restTemplate.getForObject("http://localhost:8001/payment/zipkin", String.class);
    }

}
