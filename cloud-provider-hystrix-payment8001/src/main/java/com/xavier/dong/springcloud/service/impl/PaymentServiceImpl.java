package com.xavier.dong.springcloud.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xavier.dong.springcloud.dao.PaymentMapper;
import com.xavier.dong.springcloud.entity.po.Payment;
import com.xavier.dong.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl extends ServiceImpl<PaymentMapper, Payment> implements PaymentService{

}
