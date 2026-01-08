package com.qiyuan.service;

import com.qiyuan.pojo.CommentNode;
import com.qiyuan.pojo.NoteComment;

public interface NoteCommentService {
    void insert(NoteComment noteComment);
    void update(NoteComment noteComment);
    CommentNode<NoteComment> getByNoteId(Integer noteId);

    void delete(Integer id);

    NoteComment getByIdOnly(Integer id);
}
