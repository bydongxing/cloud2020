package com.xavier.dong.springcloud.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.xavier.dong.springcloud.common.Result;

/**
 * @author XavierDong
 **/
public class CustomerHandler {

    public static Result handlerException1(BlockException blockException) {
        return Result.createByErrorCodeMessage(444, "按客户自定义, global handlerException-----1");
    }

    public static Result handlerException2(BlockException blockException) {
        return Result.createByErrorCodeMessage(444, "按客户自定义, global handlerException-----2");
    }
}
