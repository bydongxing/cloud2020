package com.xavier.dong.springcloud.service;

import com.xavier.dong.springcloud.common.Result;
import com.xavier.dong.springcloud.entity.dto.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author XavierDong
 **/
@FeignClient("CLOUD-PAYMENT-SERVICE")
public interface PaymentFeignService {

    @PostMapping("/payment/add")
    public Result create(@RequestBody Payment payment);


    @GetMapping("/payment/get/{id}")
    public Result findById(@PathVariable("id") Long id);

    @GetMapping("payment/feign/timeout")
    public String paymentFeignTimout();
}
