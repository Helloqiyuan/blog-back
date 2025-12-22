package com.qiyuan.service;

import com.qiyuan.pojo.NoteType;

import java.util.List;

public interface NoteTypeService {
    NoteType getNoteTypeById(Integer id);

    List<NoteType> getAllNoteTypes();
}
