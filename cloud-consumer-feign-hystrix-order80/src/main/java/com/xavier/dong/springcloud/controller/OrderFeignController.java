package com.xavier.dong.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.xavier.dong.springcloud.common.Result;
import com.xavier.dong.springcloud.entity.dto.Payment;
import com.xavier.dong.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

/**
 * @author XavierDong
 **/
@RestController
@Slf4j
@RequestMapping("consumer")
@DefaultProperties(defaultFallback = "paymentGlobalFallbackMethod")
public class OrderFeignController {

    @Autowired
    private PaymentFeignService paymentFeignService;

    @PostMapping("/payment/add")
    public Result addPayment(@RequestBody Payment payment) {
        return this.paymentFeignService.create(payment);
    }


    @GetMapping("/payment/get/{id}")
    public Result findById(@PathVariable("id") Long id) {
        return this.paymentFeignService.findById(id);
    }

    @GetMapping("payment/hystrix/timeout")
    public String paymentFeignTimout() {

        // open-feign-ribbon，客户端一般默认等待 1秒
        return this.paymentFeignService.paymentFeignTimout();
    }

    @GetMapping("payment/hystrix/timeout/{id}")
    public String paymentFeignTimout(@PathVariable("id") Integer id) {

        return this.paymentFeignService.paymentInfoTimeout(id);
    }


    /**
     * 熔断 服务 提供者 降级处理
     *
     * @param id
     * @return
     */
//    @HystrixCommand(fallbackMethod = "paymentInfoTimeoutFallbackMethod", commandProperties = {
////            3 秒 之内 代表正常的 逻辑，超过 3秒 ，就不是正常的 逻辑（超时了）
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
//    })
    @HystrixCommand
    @GetMapping("payment/hystrix/timeout/1/{id}")
    public String paymentInfoTimeout(@PathVariable("id") Integer id) {

//        int age = 10 / 0;

        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this.paymentFeignService.paymentInfoTimeout(id);
    }

    public String paymentInfoTimeoutFallbackMethod(Integer id) {
        return "我是消费者80，对方支付系统繁忙 ，请 10秒后 再试 或者 自己运行出错 ，请检查自己 o(╥﹏╥)o";

    }


    // 下面是 全局 fallback
    public String paymentGlobalFallbackMethod() {
        return "Global Fallback 方法 信息，O(∩_∩)O哈哈~";
    }


}
