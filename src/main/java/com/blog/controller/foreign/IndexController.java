package com.blog.controller.foreign;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blog.entity.Blog;
import com.blog.service.BlogService;
import com.blog.utils.PageUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

/**
 * Description :
 *
 * @author : Sam
 * @created : 2021/5/15
 */
@Controller
public class IndexController {

    @Resource
    private BlogService blogService;

    /**
     * 分页查询博客列表（对应首页显示博客标题，摘要等信息）
     * @param blog
     * @param current
     * @param model
     * @return
     */
    @RequestMapping(value = {"/","index","index.html"})
    public String index(Blog blog, @RequestParam(value = "page",defaultValue = "1") Long current, Model model){
        //设置每页记录数
        int size = 5;
        //创建分页对象
        Page<Blog> page = new Page<>(current,size);
        //定义条件查询拼接字符串
        StringBuffer param = new StringBuffer();
        //判断条件查询是否为空
        if(blog!=null){
            //不为空则拼接查询条件
            if(blog.getTypeId()!=null){
                param.append("typeId="+blog.getTypeId());
            }
            if(blog.getReleaseDateStr()!=null && !blog.getReleaseDateStr().equals("")){
                param.append("releaseDateStr="+blog.getReleaseDateStr());
            }
        }
        //调用查询博客列表的方法
        IPage<Blog> iPage = blogService.findBlogListByPage(page, blog);
        //将数据列表保存到model
        model.addAttribute("blogList", iPage.getRecords());
        //添加分页工具到model中
        model.addAttribute("pageCode",PageUtil.genPagination("/index.html",
                                        iPage.getTotal(), current.intValue(), size, param.toString()));

        //在index.html页面中，动态插入代码块
        model.addAttribute("pageContent","foreign/main");
        return "index";
    }

}
