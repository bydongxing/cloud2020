package com.xavier.dong.springcloud.web;

import com.alibaba.fastjson.JSON;
import com.xavier.dong.springcloud.common.Result;
import com.xavier.dong.springcloud.entity.po.Payment;
import com.xavier.dong.springcloud.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * @author XavierDong
 **/
@RestController
@RequiredArgsConstructor
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/payment/add")
    public Result create(@RequestBody Payment payment) {
        this.paymentService.save(payment);
        log.info("*****插入结果******" + JSON.toJSONString(payment));
        return Result.createBySuccess("插入数据库成功", 1);

    }

    @GetMapping("/payment/get/{id}")
    public Result findById(@PathVariable("id") Long id) {
        return Result.createBySuccess(this.paymentService.getById(id));
    }
}
