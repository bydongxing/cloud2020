package com.xavier.dong.springcloud.controller;

import com.xavier.dong.springcloud.common.Result;
import com.xavier.dong.springcloud.dubbo.interfaces.PersonModel;
import com.xavier.dong.springcloud.dubbo.interfaces.TestServiceDubbo;
import com.xavier.dong.springcloud.entity.dto.Payment;
import com.xavier.dong.springcloud.interfaces.UserService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author XavierDong
 **/
@RestController
public class DubboConsumerController {

    @DubboReference
    private UserService userService;

    @DubboReference
    private TestServiceDubbo.ITestService testService;

    @PostMapping("consumer/info")
    public Result getConfigInfo(@RequestBody Payment payment) {
        return this.userService.getPayment(payment);
    }

    @GetMapping("consumer/proto")
    public PersonModel.Person testProto() {
        PersonModel.Person person = PersonModel.Person.newBuilder()
                .setId(22)
                .setEmail("222@qq.com")
                .setName("你好")
                .build();
        return this.testService.testPerson(person);
    }

}
