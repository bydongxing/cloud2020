package com.xavier.dong.springcloud.service;

import com.xavier.dong.springcloud.common.Result;
import com.xavier.dong.springcloud.entity.dto.Payment;
import org.springframework.stereotype.Component;

/**
 * @author XavierDong
 **/
@Component
public class PaymentFallbackService implements PaymentFeignService {
    @Override
    public Result create(Payment payment) {
        return Result.createByErrorMessage("------ PaymentFallbackService fall back, o(╥﹏╥)o ");
    }

    @Override
    public Result findById(Long id) {
        return Result.createByErrorMessage("------ PaymentFallbackService fall back, o(╥﹏╥)o ");
    }

    @Override
    public String paymentFeignTimout() {
        return "------ PaymentFallbackService fall back, o(╥﹏╥)o ";
    }

    @Override
    public String paymentInfoTimeout(Integer id) {
        return "------ PaymentFallbackService fall back, o(╥﹏╥)o ";
    }
}
