package com.xavier.dong.springcloud.service.impl;

import cn.hutool.core.util.IdUtil;
import com.xavier.dong.springcloud.service.IMessageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

/**
 * @author XavierDong
 **/
// 定义消息的 推送 管道
@EnableBinding(Source.class)
public class MessageProviderImpl implements IMessageProvider {

    @Autowired
    private MessageChannel output;   // 消息推送 通道


    @Override
    public String send() {
        String serial = IdUtil.simpleUUID();
        this.output.send(MessageBuilder.withPayload(serial).build());
        System.out.println("********** Serial: " + serial);
        return serial;
    }
}
