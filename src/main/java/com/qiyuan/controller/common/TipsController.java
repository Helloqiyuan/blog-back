package com.qiyuan.controller.common;

import com.qiyuan.vo.Result;
import com.qiyuan.pojo.Tips;
import com.qiyuan.service.TipsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("commonTipsController")
@RequestMapping("/common/tips")
@Slf4j
@Tag(name = "提示接口")
public class TipsController {
    @Autowired
    private TipsService tipsService;


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
