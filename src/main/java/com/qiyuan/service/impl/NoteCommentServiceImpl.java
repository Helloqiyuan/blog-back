package com.qiyuan.service.impl;

import com.qiyuan.constant.CommentConstant;
import com.qiyuan.exception.CommentException;
import com.qiyuan.mapper.NoteCommentMapper;
import com.qiyuan.pojo.CommentNode;
import com.qiyuan.pojo.NoteComment;
import com.qiyuan.service.NoteCommentService;
import com.qiyuan.utils.ThreadLocalUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class NoteCommentServiceImpl implements NoteCommentService {
    @Autowired
    private NoteCommentMapper noteCommentMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insert(NoteComment noteComment) {
        if (noteComment.getParentCommentId() == null) {
            noteComment.setParentCommentId(NoteComment.ROOT_COMMENT_ID);
        }
        noteComment.setCreateTime(LocalDateTime.now());
        noteComment.setUpdateTime(LocalDateTime.now());
        Integer i = noteCommentMapper.insert(noteComment);
        // 根评论的rootCommentId是数据库生成的主键的id,所以需要这里再次修改;
        // 如果不是根评论,前端会传rootCommentId,不需要更新
        if(noteComment.getParentCommentId() == -1){
            noteComment.setRootCommentId(noteComment.getId());
            Integer j = noteCommentMapper.update(noteComment);
            if (j != 1) {
                throw new CommentException(CommentConstant.COMMENT_FAILED);
            }
        }
        if (i != 1) {
            throw new CommentException(CommentConstant.COMMENT_FAILED);
        }
    }

    @Override
    public void update(NoteComment noteComment) {
        noteComment.setUpdateTime(LocalDateTime.now());
        Integer i = noteCommentMapper.update(noteComment);
        if (i != 1) {
            throw new CommentException(CommentConstant.UPDATE_FAILED);
        }
    }

    @Override
    public void delete(Integer id) {
//        当前用户
        Integer userId = ThreadLocalUtil.get();
//        获取目标id对应的评论相关信息,主要是获取noteId
        NoteComment target = noteCommentMapper.getById(id);
        if(target == null){
            throw new CommentException(CommentConstant.CAN_NOT_DELETE_NOT_EXIST_COMMENT);
        }
        if(!Objects.equals(target.getUserId(),userId)){
            throw new CommentException(CommentConstant.CAN_NOT_DELETE_OTHERS_COMMENT);
        }
//        目标节点及其所有子节点集合
        List<Integer> ids = new ArrayList<>();
//        获取该随笔的评论树
        CommentNode<NoteComment> commentTree = this.createCommentTreeByNoteIdAndCommentId(target.getNoteId(),id);
//        找到目标节点的子节点
        log.warn("asifhaiu{}",commentTree);
        findAllChildrenId(commentTree, ids);
        System.out.println("ids:" + ids);
        Integer i = noteCommentMapper.deleteBatch(ids);
        if (i == 0) {
            throw new CommentException(CommentConstant.DELETE_FAILED);
        }
    }

    @Override
    public NoteComment getByIdOnly(Integer id) {
        return noteCommentMapper.getById(id);
    }

    /**
     * 根据随笔id获取评论树
     *
     * @param noteId 随笔id
     * @return id对应的评论树
     */
    @Override
    public CommentNode<NoteComment> getByNoteId(Integer noteId) {
        /*CommentNode<NoteComment> res = new CommentNode<>();
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
        }*/
        return createCommentTreeByNoteId(noteId);
//        return res;

    }

    /**
     * 找到n的父节点
     *
     * @param res 整颗树的结构
     * @param n   需要查询父节点的节点
     * @return n的父节点
     */

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

    /**
     * 找到target节点的所有子节点id
     *
     * @param target 目标节点
     */
    private void findAllChildrenId(CommentNode<NoteComment> target, List<Integer> ids) {
        if (target.getE() != null) {
            ids.add(target.getE().getId());
        }
        for (CommentNode<NoteComment> c : target.getChildren()) {
            findAllChildrenId(c, ids);
        }
    }


    /**
     * 根据随笔id和评论id获取该评论对应的评论树
     *
     * @param noteId    随笔id
     * @param commentId 评论id
     * @return commentId所对应的节点
     */
    private CommentNode<NoteComment> createCommentTreeByNoteIdAndCommentId(Integer noteId, Integer commentId) {
        NoteComment e = noteCommentMapper.getById(commentId);
        List<NoteComment> noteComments;
        if (e == null) {
            // 说明构建的是该随笔的虚拟节点树(该节点的所有评论)
            noteComments = noteCommentMapper.getByNoteId(noteId);
        } else {
            // 是某个一级及以下的某个子节点的评论树
            noteComments = noteCommentMapper.getByNoteIdAndRootId(noteId, e.getRootCommentId());
        }
        // 删除目标节点
        List<NoteComment> filterNoteComments = noteComments.stream().filter(o -> !o.getId().equals(commentId)).toList();
        // 封装评论为节点
        List<CommentNode<NoteComment>> nodes = new ArrayList<>();
        for (NoteComment n : filterNoteComments) {
            CommentNode<NoteComment> node = new CommentNode<>();
            node.setE(n);
            nodes.add(node);
        }
        // 结果 此为第一个节点
        CommentNode<NoteComment> res = new CommentNode<>();
        res.setE(e);
        // 构造树
        for (CommentNode<NoteComment> n : nodes) {
            if (n.getE().getParentCommentId() == -1 ||(res.getE() != null && n.getE().getParentCommentId().equals(res.getE().getId()))) {
                res.getChildren().add(n);
            } else {
                for (CommentNode<NoteComment> m : nodes) {
                    if (m.getE().getId().equals(n.getE().getParentCommentId())) {
                        m.getChildren().add(n);
                    }
                }
            }
        }
        return res;
    }

    private CommentNode<NoteComment> createCommentTreeByNoteId(Integer noteId) {
        return createCommentTreeByNoteIdAndCommentId(noteId, -1);
    }
}
