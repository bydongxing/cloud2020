package com.xavier.dong.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.xavier.dong.springcloud.common.Result;
import com.xavier.dong.springcloud.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author XavierDong
 **/
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("consumer")
public class CircleBreakerController {

    public static final String SERVICE_URL = "http://nacos-payment-provider";

    private final RestTemplate restTemplate;
    private final PaymentService paymentService;

    @GetMapping("fallback/{id}")
//    @SentinelResource("fallback") // 没有配置
//    @SentinelResource(value = "fallback",fallback = "handlerFallback") // fallback 只负责业务异常
//    @SentinelResource(value = "fallback", blockHandler = "blockHandler") // blockHandler 只负责 sentinel 控制台配置违规
//    @SentinelResource(value = "fallback", blockHandler = "blockHandler", fallback = "handlerFallback")// handlerFallback 和 blockHandler 都配置，以 blockException 为准
    @SentinelResource(value = "fallback", blockHandler = "blockHandler", fallback = "handlerFallback", exceptionsToIgnore = {IllegalArgumentException.class})
// handlerFallback 和 blockHandler 都配置，以 blockException 为准
    public Result fallback(@PathVariable Long id) {

        Result forObject = this.restTemplate.getForObject(SERVICE_URL + "/paymentSQL/" + id, Result.class);
        if (id == 4)
            throw new IllegalArgumentException("IllegalArgumentException,非法参数异常.......");
        else if (forObject.getData() == null)
            throw new NullPointerException("NullPointerException,该ID没有对应的记录，空指针异常");
        return forObject;
    }

    //    本例是Fallback
    public Result handlerFallback(Long id, Throwable throwable) {
        return Result.createByErrorCodeMessage(444, "兜底异常 handlerFallback，exception 内容 " + throwable.getMessage());
    }

    //    本例是blockHandler
    public Result blockHandler(Long id, BlockException blockException) {
        return Result.createByErrorCodeMessage(444, "blockHandler-sentinel 限流，无此流水： blockException " + blockException.getMessage());
    }


//    ---openfeign

    @GetMapping("paymentSQL/{id}")
    public Result getPaymentSQL(@PathVariable("id") Long id) {
        return this.paymentService.getPaymentSQL(id);
    }

}
