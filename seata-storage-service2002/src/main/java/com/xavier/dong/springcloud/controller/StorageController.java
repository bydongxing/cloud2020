package com.xavier.dong.springcloud.controller;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.xavier.dong.springcloud.common.Result;
import com.xavier.dong.springcloud.entity.po.TStorage;
import com.xavier.dong.springcloud.service.TStorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author XavierDong
 **/
@RestController
@RequiredArgsConstructor
@Slf4j
public class StorageController {

    private final TStorageService tStorageService;

    @PostMapping("/storage/decrease")
    public Result decrease(@RequestParam("productId") Long productId, @RequestParam("count") Integer count) {
        TStorage storageServiceById = this.tStorageService.getById(productId);
        storageServiceById.setUsed(storageServiceById.getUsed() + count);
        storageServiceById.setResidue(storageServiceById.getResidue() - count);

        log.info("------》storage-Service 扣减库存开始");
        this.tStorageService.update(storageServiceById, new LambdaUpdateWrapper<TStorage>().eq(TStorage::getProductId, productId));
        log.info("------》storage-Service 扣减库存结束");
        return Result.createBySuccessMessage("扣减库存成功!");

    }


}
