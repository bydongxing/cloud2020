package com.xavier.dong.springcloud.service;

import com.xavier.dong.springcloud.common.Result;
import org.springframework.stereotype.Component;

/**
 * @author XavierDong
 **/
@Component
public class PaymentFallbackService implements PaymentService {
    @Override
    public Result getPaymentSQL(Long id) {
        return Result.createByErrorCodeMessage(44222224, "服务降级返回,----PaymentFallbackService");
    }
}
