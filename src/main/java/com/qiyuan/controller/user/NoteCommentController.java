package com.qiyuan.controller.user;


import com.qiyuan.pojo.NoteComment;
import com.qiyuan.service.NoteCommentService;
import com.qiyuan.vo.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("userNoteCommentController")
@RequestMapping("/user/noteComment")
@Slf4j
@Tag(name = "随笔评论接口")
public class NoteCommentController {
    @Autowired
    private NoteCommentService noteCommentService;
    /**
     * 新增评论
     */
    @PostMapping()
    @Operation(summary = "新增评论")
    public Result insert(@RequestBody NoteComment noteComment) {
        log.info("新增评论:{}", noteComment);
        noteCommentService.insert(noteComment);
        return Result.success();
    }
    /**
     * 修改评论
     */
    @PutMapping()
    @Operation(summary = "修改评论")
    public Result update(@RequestBody NoteComment noteComment) {
        log.info("修改评论:{}", noteComment);
//        noteCommentService.update(noteComment);
        return Result.success();
    }
    /**
     * 删除评论
     */
    @DeleteMapping()
    @Operation(summary = "删除评论")
    public Result delete(@RequestParam Integer id) {
        log.info("删除评论:{}", id);
        noteCommentService.delete(id);
        return Result.success();
    }
}
