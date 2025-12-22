package com.qiyuan.mapper;

import com.qiyuan.pojo.NoteType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoteTypeMapper {
    void insertNoteType(NoteType noteType);
    Integer deleteNoteTypeById(Integer id);
    Integer updateNoteType(NoteType noteType);
    NoteType getNoteTypeById(Integer id);
    List<NoteType> getAllNoteTypes();
}
