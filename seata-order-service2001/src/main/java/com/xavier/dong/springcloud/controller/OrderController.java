package com.xavier.dong.springcloud.controller;

import com.xavier.dong.springcloud.common.Result;
import com.xavier.dong.springcloud.entity.po.TOrder;
import com.xavier.dong.springcloud.service.TOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author XavierDong
 **/
@RestController
@RequiredArgsConstructor
public class OrderController {

    private final TOrderService orderService;


    @GetMapping("/order/create")
    public Result create(TOrder order) {
        this.orderService.create(order);
        return Result.createBySuccessMessage("创建订单成功");
    }
}
