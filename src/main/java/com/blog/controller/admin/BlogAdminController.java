package com.blog.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Description :
 *
 * @author : Sam
 * @created : 2021/5/17
 */
@Controller
@RequestMapping("/admin/blog")
public class BlogAdminController {

    /**
     * 跳转到写博客页面
     * @return
     */
    @RequestMapping("/writeBlog")
    public String writeBlog(){
        return "admin/writeBlog";
    }

}
