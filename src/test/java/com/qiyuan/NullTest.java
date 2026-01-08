package com.qiyuan;

import com.qiyuan.pojo.CommentNode;
import com.qiyuan.pojo.NoteComment;
import org.junit.jupiter.api.Test;

public class NullTest {
    @Test
    public void test(){
        CommentNode<NoteComment> n = new CommentNode<>();
        System.out.println(true || n.getE().getId() == 1);
    }
}
