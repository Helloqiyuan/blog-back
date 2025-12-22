package com.qiyuan.controller;

import com.qiyuan.pojo.Note;
import com.qiyuan.dto.NotePageQueryDTO;
import com.qiyuan.service.NoteService;
import com.qiyuan.vo.PageResult;
import com.qiyuan.vo.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/note")
@Slf4j
@Tag(name="随笔接口")
public class NoteController {
    @Autowired
    private NoteService noteService;

    /**
     * 获取单条随笔
     */
    @GetMapping
    @Operation(summary = "获取单条随笔")
    public Result getNoteById(@RequestParam Integer id) {
        log.info("获取单条随笔:{}", id);
        Note note = noteService.getNoteById(id);
        return Result.success(note);
    }
    /**
     * 查询随笔
     */
    @GetMapping("/page")
    @Operation(summary = "分页查询随笔")
    public Result getNotesByPage(NotePageQueryDTO notePageQueryDTO) {
        log.info("条件查询随笔:{}", notePageQueryDTO);
        PageResult<Note> notes = noteService.pageQuery(notePageQueryDTO);
        return Result.success(notes);
    }
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
     * 获取所有随笔
     */
    @GetMapping("/all")
    @Operation(summary = "获取所有随笔")
    public Result getAllNotes() {
        log.info("获取所有随笔");
        List<Note> notes = noteService.getAllNotes();
        return Result.success(notes);
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
