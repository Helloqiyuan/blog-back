package com.qiyuan.mapper;

import com.github.pagehelper.Page;
import com.qiyuan.dto.NotePageQueryDTO;
import com.qiyuan.pojo.Note;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoteMapper {
    void insertNote(Note note);
    Integer deleteNoteById(Integer id);
    Integer updateNote(Note note);
    List<Note> getAllNotes();
    Note getNoteById(Integer id);

    Page<Note> pageQuery(NotePageQueryDTO notePageQueryDTO);
}
