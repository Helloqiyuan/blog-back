package com.qiyuan.service;

import com.qiyuan.dto.NotePageQueryDTO;
import com.qiyuan.pojo.Note;
import com.qiyuan.vo.PageResult;

import java.util.List;

public interface NoteService {
    void insertNote(Note note);
    void deleteNoteById(Integer id);

    void updateNote(Note note);
    Note getNoteById(Integer id);

    PageResult<Note> pageQuery(NotePageQueryDTO notePageQueryDTO);

    List<Note> getAllNotes();
}
