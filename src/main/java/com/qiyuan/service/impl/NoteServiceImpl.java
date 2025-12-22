package com.qiyuan.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qiyuan.constant.NoteConstant;
import com.qiyuan.dto.NotePageQueryDTO;
import com.qiyuan.exception.NoteException;
import com.qiyuan.mapper.NoteMapper;
import com.qiyuan.pojo.Note;
import com.qiyuan.service.NoteService;
import com.qiyuan.vo.PageResult;
import io.jsonwebtoken.lang.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {
    @Autowired
    private NoteMapper noteMapper;
    @Override
    public void insertNote(Note note) {
        note.setUpdateTime(LocalDateTime.now());
        note.setCreateTime(LocalDateTime.now());
        noteMapper.insertNote(note);
    }

    @Override
    public void deleteNoteById(Integer id) {
        Integer i = noteMapper.deleteNoteById(id);
        if(i == 0){
            throw new NoteException(NoteConstant.CAN_NOT_DELETE_NOT_EXIST_NOTE);
        }
    }

    @Override
    public void updateNote(Note note) {
        note.setUpdateTime(LocalDateTime.now());
        Integer i = noteMapper.updateNote(note);
        if(i == 0){
            throw new NoteException(NoteConstant.CAN_NOT_UPDATE_NOT_EXIST_NOTE);
        }
    }

    @Override
    public Note getNoteById(Integer id) {
        Note note = noteMapper.getNoteById(id);
        if(note == null) {
            throw new NoteException(NoteConstant.NOTE_NOT_EXIST);
        }
        return note;
    }


    @Override
    public PageResult<Note> pageQuery(NotePageQueryDTO notePageQueryDTO) {
        PageHelper.startPage(notePageQueryDTO.getPage(), notePageQueryDTO.getPageSize());
        Page< Note> p = noteMapper.pageQuery(notePageQueryDTO);
        return new PageResult<>(p.getTotal(), p.getResult());
    }

    @Override
    public List<Note> getAllNotes() {
        List<Note> notes = noteMapper.getAllNotes();
        if(Collections.isEmpty(notes)){
            throw new NoteException(NoteConstant.NOTE_LIST_IS_EMPTY);
        }
        return notes;
    }
}
