package com.blog.controller.admin;

import com.blog.service.BlogtypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Description :
 *
 * @author : Sam
 * @created : 2021/5/17
 */
@Controller
@RequestMapping("/admin/blogtype")
public class BlogTypeAdminController {

    @Autowired
    private BlogtypeService blogtypeService;

    /**
     * 查询博客类型（对应后台写博客页面加载博客类型下来列表数据的显示）
     * @return
     */
    @RequestMapping("/typeItem")
    @ResponseBody
    public String typeItem(){
        try {
            return blogtypeService.findBlogTypeNameAndBlogCount();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
