package com.qiyuan.controller.common;

import com.qiyuan.pojo.FriendLink;
import com.qiyuan.service.FriendLinkService;
import com.qiyuan.vo.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("commonFriendLinkController")
@RequestMapping("/common/friendLink")
@Slf4j
@Tag(name = "友链接口")
public class FriendLinkController {
    @Autowired
    private FriendLinkService friendLinkService;

    /**
     * 获取启用的友链
     */
    @GetMapping("/enable")
    @Operation(summary = "获取启用的友链")
    public Result getEnableFriendLink() {
        log.info("获取启用的友链");
        List<FriendLink> friendLinkList = friendLinkService.getEnableFriendLink();
        return Result.success(friendLinkList);
    }

    /**
     * 添加友链
     */
    @PostMapping()
    @Operation(summary = "添加友链")
    public Result insert(@RequestBody FriendLink friendLink) {
        log.info("添加友链");
        friendLinkService.insertFriendLink(friendLink);
        return Result.success();
    }

    /**
     * 根据id获取友链
     */
    @GetMapping
    @Operation(summary = "根据id获取友链")
    public Result getFriendLinkById(@RequestParam Integer id) {
        log.info("根据id获取友链{}", id);
        FriendLink friendLink = friendLinkService.getFriendLinkById(id);
        return Result.success(friendLink);
    }
}
