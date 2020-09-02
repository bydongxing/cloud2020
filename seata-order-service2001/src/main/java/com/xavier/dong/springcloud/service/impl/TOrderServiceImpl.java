package com.xavier.dong.springcloud.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xavier.dong.springcloud.dao.TOrderMapper;
import com.xavier.dong.springcloud.entity.po.TOrder;
import com.xavier.dong.springcloud.service.TAccountService;
import com.xavier.dong.springcloud.service.TOrderService;
import com.xavier.dong.springcloud.service.TStorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class TOrderServiceImpl extends ServiceImpl<TOrderMapper, TOrder> implements TOrderService {

    private final TStorageService tStorageService;
    private final TAccountService tAccountService;


    @Override
    // name 保持属性唯一就行
    @GlobalTransactional(name = "business-service-group",rollbackFor = Exception.class)
    public void create(TOrder tOrder) {

        // 1.新建订单
        log.info("------》开始创建订单");
        this.baseMapper.insert(tOrder);

        // 2.扣减库存
        log.info("------》订单微服务开始调用库存，做扣减");
        this.tStorageService.decrease(tOrder.getProductId(), tOrder.getCount());
        log.info("------》订单微服务开始调用库存，做扣减end");

        // 3.扣减账户
        log.info("------》订单微服务开始调用账户，做扣减");
        this.tAccountService.decrease(tOrder.getUserId(), tOrder.getMoney());
        log.info("------》订单微服务开始调用账户，做扣减end");

        // 4.修改订单状态，从 0 到 1，1代表已经完成
        log.info("------》修改订单状态开始");
        this.update(TOrder.builder().status(1).build(), new LambdaUpdateWrapper<TOrder>().eq(TOrder::getUserId, tOrder.getUserId()));
        log.info("------》修改订单状态结束");


        log.info("------》下订单结束了，(*￣︶￣)");


    }
}
