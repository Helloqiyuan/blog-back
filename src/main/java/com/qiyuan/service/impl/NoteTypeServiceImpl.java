package com.qiyuan.service.impl;


import com.qiyuan.constant.NoteTypeConstant;
import com.qiyuan.exception.NoteTypeException;
import com.qiyuan.mapper.NoteTypeMapper;
import com.qiyuan.pojo.NoteType;
import com.qiyuan.service.NoteTypeService;
import io.jsonwebtoken.lang.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteTypeServiceImpl implements NoteTypeService {
    @Autowired
    private NoteTypeMapper noteTypeMapper;
    @Override
    public NoteType getNoteTypeById(Integer id) {
        NoteType noteType = noteTypeMapper.getNoteTypeById(id);
        if(noteType == null){
            throw new NoteTypeException(NoteTypeConstant.NOTE_TYPE_NOT_EXIST);
        }
        return noteType;
    }

    @Override
    public List<NoteType> getAllNoteTypes() {

        List<NoteType> notes = noteTypeMapper.getAllNoteTypes();
        if(Collections.isEmpty( notes)){
            throw new NoteTypeException(NoteTypeConstant.NOTE_LIST_IS_EMPTY);
        }
        return notes;
    }
}
