package com.blog.controller.foreign;


import com.alibaba.fastjson.JSON;
import com.blog.entity.Comment;
import com.blog.service.CommentService;
import com.blog.utils.SystemConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.HashMap;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Sam
 * @since 2021-05-14
 */
@Controller
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     * 添加评论（对应博客详情页下方点击评论功能）
     * @param comment
     * @return
     */
    @ResponseBody
    @PostMapping("/addComment")
    public String addComment(Comment comment){
        HashMap<String, Object> map = new HashMap<>();
        try {
            //设置评论用户IP
            comment.setUserIp(InetAddress.getLocalHost().getHostAddress());
            //设置评论时间
            comment.setCommentDate(new Date());
            //设置评论状态
            comment.setState(SystemConstants.COMMENT_STATE_NO);//未审核
            //若保存评论成功
            if(commentService.save(comment)){
                map.put(SystemConstants.SUCCESS,true);
            }else{
                map.put(SystemConstants.SUCCESS,false);
            }
            return JSON.toJSONString(map);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return null;
    }

}

