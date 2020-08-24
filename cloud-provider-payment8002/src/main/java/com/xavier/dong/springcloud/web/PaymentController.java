package com.xavier.dong.springcloud.web;

import com.alibaba.fastjson.JSON;
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
            // // CLOUD-PAYMENT-SERVICE    192.168.3.7    8002    http://192.168.3.7:8002
            log.info(instance.getServiceId() + "\t" + instance.getHost() + "\t" + instance.getPort() + "\t" + instance.getUri());

        }

        return this.discoveryClient;

    }

    @GetMapping("payment/lb")
    public String getPaymentLB() {
        return serverPort;

    }
}
