package com.qiyuan.service.impl;

import com.qiyuan.constant.CommentConstant;
import com.qiyuan.exception.CommentException;
import com.qiyuan.mapper.NoteCommentMapper;
import com.qiyuan.pojo.CommentNode;
import com.qiyuan.pojo.NoteComment;
import com.qiyuan.service.NoteCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class NoteCommentServiceImpl implements NoteCommentService {
    @Autowired
    private NoteCommentMapper noteCommentMapper;

    @Override
    public void insert(NoteComment noteComment) {
        if(noteComment.getParentCommentId() == null){
            noteComment.setParentCommentId(NoteComment.ROOT_COMMENT_ID);
        }
        noteComment.setCreateTime(LocalDateTime.now());
        noteComment.setUpdateTime(LocalDateTime.now());
        Integer i = noteCommentMapper.insert(noteComment);
        if(i != 1){
            throw new CommentException(CommentConstant.COMMENT_FAILED);
        }
    }

    @Override
    public void update(NoteComment noteComment) {
        noteComment.setUpdateTime(LocalDateTime.now());
        Integer i = noteCommentMapper.update(noteComment);
        if(i != 1){
            throw new CommentException(CommentConstant.UPDATE_FAILED);
        }
    }
    @Override
    public void delete(Integer id) {
        Integer i = noteCommentMapper.deleteById(id);
        if(i != 1){
            throw new CommentException(CommentConstant.DELETE_FAILED);
        }
    }

    @Override
    public CommentNode<NoteComment> getByNoteId(Integer noteId) {
        CommentNode<NoteComment> res = new CommentNode<>();
        res.setE(null);
        List<NoteComment> allComments = noteCommentMapper.getByNoteId(noteId);
        for (NoteComment n : allComments) {
            CommentNode<NoteComment> o = new CommentNode<>();
//            根据已有树结构查找n的父节点
            CommentNode<NoteComment> parentCommentNode = findParentComment(res, n);
            o.setE(n);
            if (parentCommentNode == null) {
//                一级评论
                res.getChildren().add(o);
            } else {
//                非一级评论
                parentCommentNode.getChildren().add(o);

            }
        }
        return res;

    }

    private CommentNode<NoteComment> findParentComment(CommentNode<NoteComment> res, NoteComment n) {
        Integer targetNoteCommentId = n.getParentCommentId();
        if (res.getE() != null && Objects.equals(res.getE().getId(), targetNoteCommentId)) {
//            找到了n的父节点
            return res;
        }
        if (!res.getChildren().isEmpty()) {
            for (CommentNode<NoteComment> m : res.getChildren()) {
                if (m.getE().getId().equals(targetNoteCommentId)) {
                    return m;
                } else {
                    CommentNode<NoteComment> c = findParentComment(m, n);
                    if (c != null) {
                        return c;
                    }
                }
            }
        }
//        null表示没有父节点，即根节点
        return null;
    }
}
