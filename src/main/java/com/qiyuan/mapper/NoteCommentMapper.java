package com.qiyuan.mapper;



import com.qiyuan.pojo.NoteComment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoteCommentMapper {
    Integer insert(NoteComment noteComment);
    Integer update(NoteComment noteComment);
    List<NoteComment> getByNoteId(Integer noteId);
    NoteComment getById(Integer id);

    Integer deleteById(Integer id);

    Integer deleteBatch(List<Integer> ids);

    /**
     * 获取一组评论
     * 该组评论属于序号为noteId的随笔
     * 该组评论中除了id=rootId的评论，其他都是序号rootId的子评论
     * @param noteId 随笔id
     * @param rootId 根节点id
     * @return 一级评论及其所有子评论的集合
     */

    List<NoteComment> getByNoteIdAndRootId(Integer noteId, Integer rootId);
}
