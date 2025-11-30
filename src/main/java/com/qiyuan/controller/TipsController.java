package com.qiyuan.controller;

import com.qiyuan.pojo.Result;
import com.qiyuan.pojo.Tips;
import com.qiyuan.service.TipsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tips")
@Slf4j
public class TipsController {
    @Autowired
    private TipsService tipsService;

    /**
     * 新增提示
     */
    @PostMapping
    public Result insertTips(@RequestBody Tips tips) {
        log.info("新增提示:{}", tips);
        tipsService.insertTips(tips);
        return Result.success();
    }

    /**
     * 删除提示
     */
    @DeleteMapping
    public Result deleteTips(@RequestParam Integer id) {
        log.info("删除提示:{}", id);
        tipsService.deleteTips(id);
        return Result.success();
    }

    /**
     * 根据id查询提示
     */
    @GetMapping
    public Result getTipsById(@RequestParam Integer id) {
        log.info("查询提示:{}", id);
        Tips tips = tipsService.getTipsById(id);
        return Result.success(tips);
    }

    /**
     * 更新提示
     */
    @PutMapping
    public Result updateTips(@RequestBody Tips tips) {
        log.info("更新提示:{}", tips);
        tipsService.updateTips(tips);
        return Result.success();
    }

    /**
     * 随机获取count条小提示
     */
    @GetMapping("/random")
    public Result getRandomTips(@RequestParam Integer count) {
        log.info("获取{}条小提示",count);
        List<Tips> tips = tipsService.getRandomTips(count);
        return Result.success(tips);
    }
}
