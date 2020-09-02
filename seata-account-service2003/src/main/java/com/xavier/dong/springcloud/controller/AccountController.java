package com.xavier.dong.springcloud.controller;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.xavier.dong.springcloud.common.Result;
import com.xavier.dong.springcloud.entity.po.TAccount;
import com.xavier.dong.springcloud.service.TAccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

/**
 * @author XavierDong
 **/
@RestController
@RequiredArgsConstructor
@Slf4j
public class AccountController {

    private final TAccountService tAccountService;


    @PostMapping("/account/decrease")
    public Result decrease(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money) {
        TAccount tAccount = this.tAccountService.getById(userId);

        tAccount.setResidue(tAccount.getResidue().subtract(money));
        tAccount.setUsed(tAccount.getUsed().add(money));

        log.info("-------》 account-service 中扣减账户余额开始");

        // 模拟超时异常，全局事务回滚
        try {
            TimeUnit.SECONDS.sleep(70);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.tAccountService.update(tAccount
                , new LambdaUpdateWrapper<TAccount>().eq(TAccount::getUserId, userId));
        log.info("-------》 account-service 中扣减账户余额结束");
        return Result.createBySuccessMessage("扣减账户余额成功!");
    }


}
