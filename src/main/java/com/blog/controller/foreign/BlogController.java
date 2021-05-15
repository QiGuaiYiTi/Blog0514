package com.blog.controller.foreign;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.blog.entity.Blog;
import com.blog.entity.Comment;
import com.blog.service.BlogService;
import com.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Sam
 * @since 2021-05-14
 */
@Controller
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private CommentService commentService;

    /**
     * 根据日期分组，查询每个月博客的数量,（对应首页显示按日期分组统计博客数功能）
     * @return
     */
    @ResponseBody
    @RequestMapping("/blogDateList")
    public String blogDateList(){
        try {
            return blogService.findBlogDateAndCount();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 查询博客详情（对应首页点击标题，发布日期等超链接时，调整到博客详情页面）
     * @return
     */
    @RequestMapping("/view/{id}")
    public String view(@PathVariable Integer id, Model model){
        //调用根据id查询博客的方法
        Blog blogWithTypeName = blogService.findBlogContentById(id);
        //判断博客中的是否含有关键字
        if(blogWithTypeName!=null && blogWithTypeName.getKeyWord()!=null && blogWithTypeName.getKeyWord().length()!=0){
            //获取博客中的关键字
            String[] keyWords = blogWithTypeName.getKeyWord().split(" ");
            //保存到model中
            model.addAttribute("keyWordList",keyWords);
        }else{
            //无关键字
            model.addAttribute("keyWordList",null);
        }
        //保存到模型中
        model.addAttribute("blog",blogWithTypeName);

        //上一篇下一篇数据
        model.addAttribute("pageCode",getUpAndDownBlog(blogService.findPreBlogById(id),blogService.findNextBlogById(id)));
        //动态插入博客详情代码块
        model.addAttribute("pageContent","foreign/blog/view");
        //查询该博客的评论列表
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("blogId",id);
        List<Comment> commentList = commentService.list(queryWrapper);
        //保存到model中，传输数据到页面
        model.addAttribute("commentList",commentList);
        return "index";

    }

    /**
     * 上一篇，下一篇功能
     * @param pre
     * @param next
     * @return
     */
    public String getUpAndDownBlog(Blog pre,Blog next){
        StringBuffer sb = new StringBuffer();
        if(pre==null || pre.getId()==null){
            sb.append("<p>上一篇：没有上一篇了</p>");
        }else{
            sb.append("<p>上一篇：<a href='/blog/view/"+pre.getId()+"'>"+pre.getTitle()+"</a></p>");
        }
        if(next==null || next.getId()==null){
            sb.append("<p>下一篇：没有下一篇了</p>");
        }else{
            sb.append("<p>下一篇：<a href='/blog/view/"+next.getId()+"'>"+next.getTitle()+"</a></p>");
        }
        return sb.toString();
    }

}

