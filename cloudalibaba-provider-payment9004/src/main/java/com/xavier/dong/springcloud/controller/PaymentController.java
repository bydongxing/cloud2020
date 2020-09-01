package com.xavier.dong.springcloud.controller;

import com.google.common.collect.Maps;
import com.xavier.dong.springcloud.common.Result;
import com.xavier.dong.springcloud.entity.dto.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author XavierDong
 **/
@RestController
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    private static Map<Long, Payment> map = Maps.newHashMap();

    static {
        map.put(1L, new Payment(1L, "348fa72e3cf542b3948a5b2dea1e7990"));
        map.put(2L, new Payment(2L, "220c343fbfcf4dd9af31e00c8083ffff"));
        map.put(3L, new Payment(3L, "091abae6ca19464ca7d56f08f18ad8fc"));
    }

    @GetMapping("paymentSQL/{id}")
    public Result getPayment(@PathVariable("id") Long id) {
        return Result.createBySuccess("from mysql,serverPort: " + serverPort, map.get(id));
    }
}
