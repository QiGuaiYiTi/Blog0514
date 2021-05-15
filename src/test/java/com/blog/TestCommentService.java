package com.blog;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.blog.entity.Comment;
import com.blog.service.CommentService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * Description :
 *
 * @author : Sam
 * @created : 2021/5/16
 */
@SpringBootTest
public class TestCommentService {

    @Autowired
    private CommentService commentService;
    @Test
    public void testService(){
        //查询该博客的评论列表
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("typeId",56);
        List<Comment> commentList = commentService.list(queryWrapper);
        for (Comment comment : commentList) {
            System.out.println(comment.getContent());
        }
    }
}
