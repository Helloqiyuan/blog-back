package com.qiyuan.controller.manager;


import com.qiyuan.pojo.Note;
import com.qiyuan.service.NoteService;

import com.qiyuan.vo.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController("managerNoteController")
@RequestMapping("/manager/note")
@Slf4j
@Tag(name="随笔接口")
public class NoteController {
    @Autowired
    private NoteService noteService;



    /**
     * 新增随笔
     */
    @PostMapping
    @Operation(summary = "新增随笔")
    public Result insert(@RequestBody Note note) {
        log.info("新增随笔:{}", note);
        noteService.insertNote(note);
        return Result.success();
    }

    /**
     * 删除随笔
     */
    @DeleteMapping
    @Operation(summary = "删除随笔")
    public Result delete(@RequestParam Integer id) {
        log.info("删除随笔:{}", id);
        noteService.deleteNoteById(id);
        return Result.success();
    }
    /**
     * 修改随笔
     */
    @PutMapping
    @Operation(summary = "修改随笔")
    public Result update(@RequestBody Note note) {
        log.info("修改随笔:{}", note);
        noteService.updateNote(note);
        return Result.success();
    }

}
