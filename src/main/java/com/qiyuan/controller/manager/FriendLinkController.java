package com.qiyuan.controller.manager;

import com.qiyuan.pojo.FriendLink;
import com.qiyuan.service.FriendLinkService;
import com.qiyuan.vo.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("managerFriendLinkController")
@RequestMapping("/manager/friendLink")
@Slf4j
@Tag(name = "友链接口")
public class FriendLinkController {
    @Autowired
    private FriendLinkService friendLinkService;



    /**
     * 获取所有友链
     */
    @GetMapping("/all")
    @Operation(summary = "获取所有友链")
    public Result getAllFriendLink() {
        log.info("获取所有友链");
        List<FriendLink> friendLinkList = friendLinkService.getAllFriendLink();
        return Result.success(friendLinkList);
    }



    /**
     * 删除友链
     */
    @DeleteMapping
    @Operation(summary = "删除友链")
    public Result delete(@RequestParam Integer id) {
        log.info("删除友链{}", id);
        friendLinkService.deleteFriendLink(id);
        return Result.success();
    }

    /**
     * 修改友链
     */
    @PutMapping
    @Operation(summary = "修改友链")
    public Result update(@RequestBody FriendLink friendLink) {
        log.info("修改友链{}", friendLink);
        friendLinkService.updateFriendLink(friendLink);
        return Result.success();
    }


}
