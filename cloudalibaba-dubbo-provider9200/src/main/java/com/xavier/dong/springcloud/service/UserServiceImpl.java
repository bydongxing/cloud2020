package com.xavier.dong.springcloud.service;

import com.alibaba.fastjson.JSON;
import com.xavier.dong.springcloud.common.Result;
import com.xavier.dong.springcloud.entity.dto.Payment;
import com.xavier.dong.springcloud.interfaces.UserService;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * @author XavierDong
 **/
@DubboService
public class UserServiceImpl implements UserService {
    @Override
    public Result getPayment(Payment payment) {
        System.out.println("收到的客户端的消息为: " + JSON.toJSONString(payment));
//        try {
//            TimeUnit.SECONDS.sleep(6);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        return Result.createBySuccess(payment);
    }
}
