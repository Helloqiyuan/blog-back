package com.qiyuan.controller;

import com.qiyuan.vo.Result;
import com.qiyuan.pojo.Tips;
import com.qiyuan.service.TipsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tips")
@Slf4j
@Tag(name = "提示接口")
public class TipsController {
    @Autowired
    private TipsService tipsService;

    /**
     * 新增提示
     */
    @PostMapping
    @Operation(summary = "新增提示")
    public Result insert(@RequestBody Tips tips) {
        log.info("新增提示:{}", tips);
        tipsService.insertTips(tips);
        return Result.success();
    }

    /**
     * 删除提示
     */
    @DeleteMapping
    @Operation(summary = "删除提示")
    public Result delete(@RequestParam Integer id) {
        log.info("删除提示:{}", id);
        tipsService.deleteTips(id);
        return Result.success();
    }

    /**
     * 根据id查询提示
     */
    @GetMapping
    @Operation(summary = "根据id查询提示")
    public Result getTipsById(@RequestParam Integer id) {
        log.info("查询提示:{}", id);
        Tips tips = tipsService.getTipsById(id);
        return Result.success(tips);
    }

    /**
     * 更新提示
     */
    @PutMapping
    @Operation(summary = "更新提示")
    public Result update(@RequestBody Tips tips) {
        log.info("更新提示:{}", tips);
        tipsService.updateTips(tips);
        return Result.success();
    }

    /**
     * 随机获取count条小提示
     */
    @GetMapping("/random")
    @Operation(summary = "随机获取count条小提示")
    public Result getRandomTips(@RequestParam Integer count) {
        log.info("获取{}条小提示",count);
        List<Tips> tips = tipsService.getRandomTips(count);
        return Result.success(tips);
    }
}
