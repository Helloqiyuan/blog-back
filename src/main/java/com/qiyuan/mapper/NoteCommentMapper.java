package com.qiyuan.mapper;


import com.qiyuan.pojo.NoteComment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoteCommentMapper {
    Integer insert(NoteComment noteComment);
    Integer update(NoteComment noteComment);
    List<NoteComment> getByNoteId(Integer noteId);

    Integer deleteById(Integer id);
}
