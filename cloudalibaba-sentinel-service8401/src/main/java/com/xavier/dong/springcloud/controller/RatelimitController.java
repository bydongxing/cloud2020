package com.xavier.dong.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.xavier.dong.springcloud.common.Result;
import com.xavier.dong.springcloud.entity.dto.Payment;
import com.xavier.dong.springcloud.myhandler.CustomerHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author XavierDong
 **/
@RestController
public class RatelimitController {

    @GetMapping("/byResource")
    @SentinelResource(value = "byResource", blockHandler = "handlerException")
    public Result byResource() {
        return Result.createBySuccess("按资源名称限流测试OK", Payment.builder().id(2020L).serial("serial001").build());
    }

    public Result handlerException(BlockException blockException) {
        return Result.createByError(444, blockException.getClass().getCanonicalName() + "\t 服务不可用");
    }


    @GetMapping("/rateLimit/byUrl")
//    @SentinelResource(value = "byUrl")
    public Result byUrl() {
        return Result.createBySuccess("按Url限流测试OK", Payment.builder().id(2020L).serial("serial002").build());
    }


    // customerBlockHandler

    @GetMapping("/rateLimit/customerBlockHandler")
    @SentinelResource(value = "customerBlockHandler", blockHandlerClass = CustomerHandler.class, blockHandler = "handlerException2")
    public Result customerBlockHandler() {
        return Result.createBySuccess("按客户自定义", Payment.builder().id(2020L).serial("serial003").build());
    }
}
