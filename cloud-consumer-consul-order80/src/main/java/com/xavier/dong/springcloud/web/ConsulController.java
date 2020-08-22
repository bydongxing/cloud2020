package com.xavier.dong.springcloud.web;

import com.xavier.dong.springcloud.common.Result;
import com.xavier.dong.springcloud.entity.dto.Payment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * @author XavierDong
 **/
@RestController
@Slf4j
@RequestMapping("/consumer")
@RequiredArgsConstructor
public class ConsulController {

    private static final String PAYMENT_URL = "http://cloud-payment-service";  // 服务提供者

    private final RestTemplate restTemplate;

    @PostMapping("/payment/add")
    public Result addPayment(@RequestBody Payment payment) {
        return this.restTemplate.postForObject(PAYMENT_URL + "/payment/add", payment, Result.class);
    }


    @GetMapping("/payment/get/{id}")
    public Result findById(@PathVariable("id") Long id) {
        return this.restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id, Result.class);
    }

    @GetMapping("payment/consul")
    public String paymentInfo() {
        return this.restTemplate.getForObject(PAYMENT_URL + "/payment/consul", String.class);
    }

}
