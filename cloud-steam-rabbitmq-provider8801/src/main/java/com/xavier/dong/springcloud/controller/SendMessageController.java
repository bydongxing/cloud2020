package com.xavier.dong.springcloud.controller;

import com.xavier.dong.springcloud.service.IMessageProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author XavierDong
 **/
@RestController
public class SendMessageController {


    @Resource
    private IMessageProvider messageProvider;


    @GetMapping("sendMessage")
    public String sendMessage() {
        return this.messageProvider.send();
    }

}
