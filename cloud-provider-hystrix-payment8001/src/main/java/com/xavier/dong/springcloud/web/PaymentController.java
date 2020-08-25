package com.xavier.dong.springcloud.web;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSON;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.xavier.dong.springcloud.common.Result;
import com.xavier.dong.springcloud.entity.po.Payment;
import com.xavier.dong.springcloud.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author XavierDong
 **/
@RestController
@RequiredArgsConstructor
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class PaymentController {

    private final PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    private final DiscoveryClient discoveryClient;

    @PostMapping("/payment/add")
    public Result create(@RequestBody Payment payment) {
        this.paymentService.save(payment);
        log.info("*****插入结果******" + JSON.toJSONString(payment));
        return Result.createBySuccess("插入数据库成功,serverPort=" + serverPort, 1);

    }

    @GetMapping("/payment/get/{id}")
    public Result findById(@PathVariable("id") Long id) {
        return Result.createBySuccess("查询数据库成功,serverPort=" + serverPort, this.paymentService.getById(id));
    }

    @GetMapping("payment/discovery")
    public Object discovery() {
        // 获取到 Eureka 上面的 所有的 微服务 列表
        // 本例中：① cloud-payment-service
        //        ②  cloud-order-service
        List<String> services = this.discoveryClient.getServices();
        for (String service : services) {
            log.info("******* element: " + service);
        }

        // 获取 具体的 某个 服务的 详情
        List<ServiceInstance> instances = this.discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info(instance.getServiceId() + "\t" + instance.getHost() + "\t" + instance.getPort() + "\t" + instance.getUri());

        }

        return this.discoveryClient;

    }

    @GetMapping("payment/lb")
    public String getPaymentLB() {
        return serverPort;

    }

    @GetMapping("payment/hystrix/timeout")
    public String paymentFeignTimout() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;

    }

    /**
     * 熔断 服务 提供者 降级处理
     *
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "paymentInfoTimeoutHandler", commandProperties = {
//            3 秒 之内 代表正常的 逻辑，超过 3秒 ，就不是正常的 逻辑（超时了）
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    @GetMapping("payment/hystrix/timeout/{id}")
    public String paymentInfoTimeout(@PathVariable("id") Integer id) {
        int timeNumber = 2;

//        int age = 10 / 0;
        try {
            TimeUnit.SECONDS.sleep(timeNumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池：" + Thread.currentThread().getName() + " paymentInfo_Timeout, id: " + id + "\t" + "O(∩_∩)O哈哈~" + " 耗时（秒）" + timeNumber;
    }

    public String paymentInfoTimeoutHandler(Integer id) {
        return "线程池：" + Thread.currentThread().getName() + " 系统繁忙，请稍后再试, id: " + id + "\t" + "o(╥﹏╥)o";

    }


    //------------- 服务熔断
    @HystrixCommand(fallbackMethod = "paymentcircuitBreakerFallback", commandProperties = {
            //   3 秒 之内 代表正常的 逻辑，超过 3秒 ，就不是正常的 逻辑（超时了）
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000"),
            // 是否开启 断路器
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
            // 请求次数 ，默认为 20
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
            // 时间窗口期，时间范围, 在 这么长时间内 请求多少次，失败率达到多少 ，开始 断路，默认为 10秒
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
            // 失败率达到 多少后 跳闸， 默认为 50%
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")
    })
    @GetMapping("payment/circuitBreaker/{id}")
    public String paymentcircuitBreaker(@PathVariable("id") Integer id) {

        if (id < 0)
            throw new RuntimeException("******* id 不能为负数");

        String serialNumber = IdUtil.simpleUUID();
        return Thread.currentThread().getName() + "\t" + "调用成功，流水号： " + serialNumber;

    }

    public String paymentcircuitBreakerFallback(@PathVariable("id") Integer id) {
        return "id 不能为 负数，请 稍后再试，☺ id： " + id;
    }


}
