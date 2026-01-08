package com.qiyuan.controller.common;

import com.qiyuan.pojo.CommentNode;
import com.qiyuan.pojo.NoteComment;
import com.qiyuan.service.NoteCommentService;
import com.qiyuan.vo.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("commonNoteCommentController")
@RequestMapping("/common/noteComment")
@Slf4j
@Tag(name = "随笔评论接口")
public class NoteCommentController {
    @Autowired
    private NoteCommentService noteCommentService;
    /**
     * 根据随笔ID查询评论
     */
    @GetMapping()
    @Operation(summary = "根据随笔ID查询评论")
    public Result getByNoteId(@RequestParam Integer noteId) {
        log.info("查询随笔评论:{}", noteId);
        CommentNode<NoteComment> res = noteCommentService.getByNoteId(noteId);
        return Result.success(res);
    }
    /**
     * 获取单条评论 不包含子节点
     */
    @GetMapping("/{id}")
    @Operation(summary = "获取单条评论 不包含子节点")
    public Result getByIdOnly(@PathVariable Integer id) {
        log.info("获取单条评论:{}", id);
        NoteComment res = noteCommentService.getByIdOnly(id);
        return Result.success(res);
    }
}
