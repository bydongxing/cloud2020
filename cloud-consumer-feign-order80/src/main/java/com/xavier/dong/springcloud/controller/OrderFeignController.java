package com.xavier.dong.springcloud.controller;

import com.xavier.dong.springcloud.common.Result;
import com.xavier.dong.springcloud.entity.dto.Payment;
import com.xavier.dong.springcloud.service.PaymentFeignService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * @author XavierDong
 **/
@RestController
@Slf4j
@RequestMapping("/consumer")
@RequiredArgsConstructor
public class OrderFeignController {


    private final PaymentFeignService paymentFeignService;

    @PostMapping("/payment/add")
    public Result addPayment(@RequestBody Payment payment) {
        return this.paymentFeignService.create(payment);
    }


    @GetMapping("/payment/get/{id}")
    public Result findById(@PathVariable("id") Long id) {
        return this.paymentFeignService.findById(id);
    }

    @GetMapping("payment/feign/timeout")
    public String paymentFeignTimout() {

        // open-feign-ribbon，客户端一般默认等待 1秒
        return this.paymentFeignService.paymentFeignTimout();
    }

}
