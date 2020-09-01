package com.xavier.dong.springcloud.service;

import com.xavier.dong.springcloud.common.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author XavierDong
 **/
@FeignClient(value = "nacos-payment-provider", fallback = PaymentFallbackService.class)
public interface PaymentService {

    @GetMapping("paymentSQL/{id}")
    public Result getPaymentSQL(@PathVariable("id") Long id);

}
